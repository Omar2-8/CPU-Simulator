public class Main {

public static void main(String[]args){
        String tasks="D:\\study\\Companies\\Atypon\\Threading\\CPU-Simulater\\src\\tasks2.txt";
        try {
            Simulator simulator = new Simulator(4,12,tasks);
            simulator.start();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

            }
}