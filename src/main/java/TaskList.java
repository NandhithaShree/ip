import java.util.ArrayList;

public class TaskList {

    public void addTasks(Task task, ArrayList<Task> arrayList){
        Ui ui = new Ui();
        arrayList.add(task);
        ui.addMessage(task);
        MarkAsDone.numberOfTasks++;
        ui.numberOfTasksPrinter();
    }

    public void deleteTasks(int indexToBeDeleted, ArrayList<Task> arrayList){
        Ui ui = new Ui();
        Storage storage = new Storage();
        arrayList.remove(indexToBeDeleted);
        MarkAsDone.numberOfTasks--;
        ui.numberOfTasksPrinter();
        storage.addArrayToFile(arrayList, MarkAsDone.numberOfTasks);
    }

    public static void find(ArrayList<Task> arrayList, String toFind){
        Ui ui = new Ui();
        int number = 1;
        for(Task a : arrayList){
            if(a.description.contains(toFind)){
                ui.printTask(a, number);
                number++;
            }
        }
    }
}
