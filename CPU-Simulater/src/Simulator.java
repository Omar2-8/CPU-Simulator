import java.io.FileNotFoundException;
import java.util.*;

public class Simulator {



   public static boolean simulation=true;
    private final Clock clock ;
    private final List<Processor> processors=new ArrayList<>();
    private final Scheduler scheduler;


    public Simulator(int processorsNumber, int simulationTime, String taskFile)
            throws FileNotFoundException {

        for(int i=0;i<processorsNumber;i++){
            processors.add(new Processor());
        }
        clock=new Clock(simulationTime,this);

        TasksHandler.storeTasks(taskFile);

        scheduler=new Scheduler(processors,clock);



    }



    public synchronized void start() throws InterruptedException {
        new Thread(clock).start();
        for(Processor processor:processors){
            new Thread(processor).start();
        }
        new Thread(scheduler).start();

        while(clock.getCycle()!=clock.getTotalCycles()){
            wait();
            Messages.currentCycle();
            synchronized (scheduler){
            scheduler.notify();
            }
        }
        simulation=false;
    }
}
