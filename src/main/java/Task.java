public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void removeDone(){
        isDone = false;
    }

    public void addDone(){
        isDone = true;
    }

    public String toString(){
        return "[" +  getStatusIcon() + "] "+ description;
    }
}
