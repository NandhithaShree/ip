/**
 * Represents an Event task that extends the {@link Task} class.
 * This class stores a description of the task and the start and end time (or date) for the event.
 * It includes methods for creating an Event task and generating a string representation of the task.
 *
 * @see Task
 */
public class Event extends Task {
    protected String from;
    protected String to;
    /**
     * Constructs a new Event task with a given description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from The start time or date of the event.
     * @param to The end time or date of the event.
     */
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns a string representation of the Event task.
     * The string includes the task type ("[E]"), the task description, the completion status,
     * the start time ("from"), and the end time ("to").
     *
     * @return A string representation of the Event task, including the description, start time, and end time.
     */
    public String toString(){
        return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
    }
}
