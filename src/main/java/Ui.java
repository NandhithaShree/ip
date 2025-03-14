import java.util.ArrayList;

/**
 * Represents the user interface of the task management system.
 * The Ui class handles displaying messages to the user, such as greetings,
 * task lists, task additions, and error messages.
 */
public class Ui {

    /**
     * Displays a welcome message when the application starts.
     */
    public void welcomeMessage(){
        System.out.println("Hey! Chitti's here!");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the current list of tasks.
     * If no tasks are present, it shows a message indicating the list is empty.
     *
     * @param list The list of tasks to be printed.
     */
    public void printList(ArrayList<Task> list){
        int count = 0;
        if(Chitti.numberOfTasks == 0){
            System.out.println("YEY! You have no tasks");
        }
        while(count < Chitti.numberOfTasks){
            System.out.println(count + 1 + "." + list.get(count).toString());
            count++;
        }
    }

    /**
     * Prints a specific task at a given position in the list.
     *
     * @param task The task to be printed.
     * @param number The position of the task in the list.
     */
    public void printTask(Task task, int number){
        System.out.println(number + "." + task);
    }

    /**
     * Displays the current number of tasks in the list.
     */
    public void numberOfTasksPrinter(){
        System.out.println("Now you have " + Chitti.numberOfTasks + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     */
    public void marked(){
        System.out.println("Hey, I marked this task as done. Nice job! Keep crushing it!");
    }

    /**
     * Displays a message indicating a task has been unmarked.
     */
    public void unmarked(){
        System.out.println("Oops, not done with this one yet? No worries, I've unmarked it for you!");
    }

    /**
     * Displays an error message when there is a problem writing to the file.
     */
    public void errorWritingToFile(){
        System.out.println("Something went wrong!");
    }

    /**
     * Displays a message when a ToDo task is created without a description.
     */
    public void emptyToDo(){
        System.out.println("Oops! Please write something after todo!");
    }

    /**
     * Displays an error message when the deadline task format is incorrect.
     */
    public void wrongDeadlineFormat(){
        System.out.println("Oops! Please write it in this format: deadline description /by date/time");
    }

    /**
     * Displays an error message when the event task format is incorrect.
     */
    public void wrongEventFormat(){
        System.out.println("Oops! Please write it in this format: event description /from date/time /to date/time");
    }

    /**
     * Displays an error message when the user inputs an unrecognized command.
     */
    public void nonExistantCommand(){
        System.out.println("Sorry, I'm unsure what that means. Try: todo, deadline, or event");
    }

    /**
     * Displays a message indicating that a task has been successfully added to the list.
     *
     * @param task The task that has been added.
     */
    public void addMessage(Task task){
        System.out.println("Got it! Added this for you:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been successfully removed from the list.
     *
     * @param toBeDeleted The task that has been removed.
     */
    public void deleteMessage(Task toBeDeleted){
        System.out.print("Okay! I've removed this task:");
        System.out.println("\n" + toBeDeleted);
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void byeMessage(){
        System.out.println("Bye. Call me if you need help");
    }

    /**
     * Displays an error message when attempting to delete a task that does not exist.
     */
    public void invalidIndexForDelete() {
        System.out.println("Please delete an existing task");
    }

    /**
     * Displays an error message when attempting to mark a task that does not exist.
     */
    public void invalidIndexForMark() {
        System.out.println("Please mark an existing task!");
    }

    /**
     * Displays an error message when attempting to unmark a task that does not exist.
     */
    public void invalidIndexForUnmark() {
        System.out.println("Please unmark an existing task!");
    }
}

