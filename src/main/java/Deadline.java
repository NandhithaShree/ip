public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.isDone = false;
        this.by = by;
    }

    public String toString(){
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
