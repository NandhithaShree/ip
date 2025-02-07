public class ToDo extends Task{
    public ToDo(String description){
        super(description);
        this.isDone = false;
    }
    public String toString(){
        return "[T]" + super.toString();
    }
}
