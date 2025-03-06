/**
 * Represents a ToDo task in the task management system.
 * A ToDo task extends the {@link Task} class and tracks whether the task is done or not.
 */
public class ToDo extends Task{
    /**
     * Creates a new ToDo task with the specified description.
     * The task is initially not done.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description){
        super(description);
        this.isDone = false;
    }
    /**
     * Returns a string representation of the ToDo task, including its status.
     * The format is "[T] {Task description}" where "T" indicates a ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    public String toString(){
        return "[T]" + super.toString();
    }
}
