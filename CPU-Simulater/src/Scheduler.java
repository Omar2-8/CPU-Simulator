import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler implements Runnable{

    private final Clock clock;
    private final List<Processor> processors;
    private final Queue<Task> highPriorityTasks = new PriorityQueue<>();
    private final Queue<Task> lowPriorityTasks= new PriorityQueue<>();
    @Override
    public synchronized void run() {
        while (Simulator.simulation){
            try {
               // wait();
                wait();
                getTasksOnCycles();

                assignTask();

                for (Processor processor : processors) {
                    processor.check();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public Scheduler(List<Processor> processors, Clock clock) {
                this.clock=clock;
                this.processors=processors;
    }



    public void getTasksOnCycles(){
        for(Task task: TasksHandler.tasks){
            if(task.getCreationTime()==clock.getCycle()){
                Messages.taskCreation(task.getId());
               if(task.getPriority()==1)
                   highPriorityTasks.add(task);
               else lowPriorityTasks.add(task);
            }
        }
    }

    public void assignTask(){
        for(Processor processor:processors){
            if(!processor.isBusy()){
                Messages.emptyProcessor(processor.getId());

                if(!highPriorityTasks.isEmpty()){
                    processor.handleTask(highPriorityTasks.poll());
                }else if(!lowPriorityTasks.isEmpty()){
                    processor.handleTask(lowPriorityTasks.poll());
                }
            }else{
                synchronized (clock){
                    clock.notify();
                }
            }
        }
    }
}
