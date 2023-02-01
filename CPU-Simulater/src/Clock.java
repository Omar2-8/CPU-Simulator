import java.util.List;

public class Clock implements Runnable{
    static int cycle;
    private int totalCycles;
    private Simulator simulator;


    public Clock(int simulationTime, Simulator simulator ) {
        totalCycles=simulationTime;
        this.simulator=simulator;

    }
    public int getCycle() {
        return cycle;
    }
    public int getTotalCycles() {
        return totalCycles;
    }

    @Override
    public  void run() {
        cycle=0;
        while (cycle<totalCycles){
            try{
                Thread.sleep(1000);
                cycle++;

                    synchronized (simulator){
                  simulator.notifyAll();
                    }


            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


}
