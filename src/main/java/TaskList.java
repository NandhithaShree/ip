import java.util.ArrayList;
/**
 * The {@code TaskList} class provides methods for managing a list of tasks.
 * It allows adding, deleting, and searching for tasks in the task list.
 */
public class TaskList {
    /**
     * Adds a task to the task list and updates the UI and number of tasks.
     *
     * @param task The task to be added to the list.
     * @param arrayList The list to which the task will be added.
     * @param ui The UI object that handles displaying messages and printing of added task
     */
    public void addTasks(Task task, ArrayList<Task> arrayList, Ui ui){
        arrayList.add(task);
        ui.addMessage(task);
        Chitti.numberOfTasks++;
        ui.numberOfTasksPrinter();
    }
    /**
     * Deletes a task from the task list based on its index and updates the UI and number of tasks.
     * The list is saved after deletion.
     *
     * @param indexToBeDeleted The index of the task to be deleted from the list.
     * @param arrayList The list from which the task will be removed.
     * @param ui The UI object that prints the deleted task
     */
    public void deleteTasks(int indexToBeDeleted, ArrayList<Task> arrayList, Ui ui){
        Storage storage = new Storage();
        arrayList.remove(indexToBeDeleted);
        Chitti.numberOfTasks--;
        ui.numberOfTasksPrinter();
        storage.addArrayToFile(arrayList, Chitti.numberOfTasks);
    }
    /**
     * Finds and prints tasks from the list whose description contains the given search term.
     *
     * @param arrayList The list of tasks to search through.
     * @param toFind The term to search for in the task descriptions.
     * @param ui The UI object that handles printing the tasks that match the search term.
     */
    public static void find(ArrayList<Task> arrayList, String toFind, Ui ui){
        int number = 1;
        for(Task a : arrayList){
            if(a.description.contains(toFind)){
                ui.printTask(a, number);
                number++;
            }
        }
    }
}
