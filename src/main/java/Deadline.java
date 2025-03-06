/**
 * Represents a Deadline task that extends the {@link Task} class.
 * This class stores a description of the task and the deadline by which the task needs to be completed.
 * It includes methods for creating a Deadline task, marking it as done, and generating a string representation
 * of the task.
 *
 * @see Task
 */
public class Deadline extends Task{
    protected String by;
    /**
     * Constructs a new Deadline task with a given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by){
        super(description);
        this.isDone = false;
        this.by = by;
    }
    /**
     * Returns a string representation of the Deadline task.
     * The string includes the task type ("[D]"), the task description, the completion status,
     * and the deadline.
     *
     * @return A string representation of the Deadline task, including the description and the deadline.
     */
    public String toString(){
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
