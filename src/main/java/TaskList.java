import java.util.ArrayList;

public class TaskList {

    public void addTasks(Task task, ArrayList<Task> arrayList, Ui ui){
        arrayList.add(task);
        ui.addMessage(task);
        Chitti.numberOfTasks++;
        ui.numberOfTasksPrinter();
    }

    public void deleteTasks(int indexToBeDeleted, ArrayList<Task> arrayList, Ui ui){
        Storage storage = new Storage();
        arrayList.remove(indexToBeDeleted);
        Chitti.numberOfTasks--;
        ui.numberOfTasksPrinter();
        storage.addArrayToFile(arrayList, Chitti.numberOfTasks);
    }

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
