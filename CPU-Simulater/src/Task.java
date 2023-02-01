public class Task implements Comparable<Task>{


    private static int tasksCount=0;
    private final int id;
    private final int creationTime;
    private final int executionTime;
    private final int priority;

    public Task(int creationTime,int executionTime,int priority) {
        tasksCount++;
        id=tasksCount;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }
    public int getCreationTime() {
        return creationTime;
    }
    public int getExecutionTime() {
        return executionTime;
    }
    public int getPriority() {
        return priority;
    }


    @Override
    public int compareTo(Task o) {
        return 0;
    }
}
