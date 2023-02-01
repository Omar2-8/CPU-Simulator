public abstract class Messages {

    public static void emptyProcessor(int id){
        System.out.println("P"+id+" is Empty ");
    }

    public static void assignTaskToProcessor(int id,int taskId,int time){
        System.out.println("P"+id
                +" is working on T"+taskId+" Estimated time is "+time);
    }

    public static void currentCycle(){
        System.out.println("Cycle "+ Clock.cycle+" -----------------------------------------------------------");
    }

    public static void finishedTask(int id,int taskId){
        System.out.println("P"+id+" has completed T"+taskId);
        //emptyProcessor(id);
    }

    public static void taskCreation(int id) {
        System.out.println("T"+id+" is Created");
    }
}
