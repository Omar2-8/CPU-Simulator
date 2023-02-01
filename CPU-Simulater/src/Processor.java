public class Processor implements Runnable{

    private final int id;
    private int currentTaskExTime;
    private int currentTaskId;
    private static int processorsCount=0;
    private boolean isBusy=false;

    public Processor() {
        processorsCount++;
        id=processorsCount;
    }
    public synchronized void handleTask(Task task){

        isBusy=true;

        currentTaskExTime=task.getExecutionTime();
        currentTaskId=task.getId();
        Messages.assignTaskToProcessor(id,currentTaskId, currentTaskExTime);
    }

    public int getId() {
        return id;
    }



    public boolean isBusy() {
        return isBusy;
    }

    public synchronized void check(){
        if(isBusy){
            currentTaskExTime--;
            if(currentTaskExTime==0){
                Messages.finishedTask(id,currentTaskId);
                isBusy=false;
            }
    }
        this.notifyAll();
    }
    @Override
    public synchronized void run() {
        while(Simulator.simulation){
        try {
            wait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }
    }
}
