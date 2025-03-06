import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The {@code Parser} class is responsible for handling user input and executing commands
 * that manipulate a list of tasks. It interacts with the {@code TaskList}, {@code Ui}, and {@code Storage}
 * classes to perform operations such as listing tasks, marking tasks as done, adding tasks,
 * and deleting tasks.
 */
public class Parser {
    TaskList taskList = new TaskList();
    /**
     * Parses user input and executes the corresponding command.
     *
     * @param arrayList The list of tasks to be manipulated based on user input.
     */
    public void parse(ArrayList<Task> arrayList, Ui ui, Storage storage){
        Scanner scanner =  new Scanner(System.in);
        String input = scanner.nextLine();
        while(!(input.equals("bye"))) {
            if(input.equals("list")){
                ui.printList(arrayList);
            } else if (input.startsWith("mark ")){
                String[] inputParts = input.split(" ");
                String taskToBeMarked = inputParts[1];
                int indexOfTaskToBeMarked = Integer.parseInt(taskToBeMarked) - 1;
                arrayList.get(indexOfTaskToBeMarked).addDone();
                storage.addArrayToFile(arrayList, Chitti.numberOfTasks);
                ui.marked();
            } else if (input.startsWith("unmark ")) {
                String[] inputParts = input.split(" ");
                String taskToBeUnmarked = inputParts[1];
                int indexOfTaskToBeMarked = Integer.parseInt(taskToBeUnmarked) - 1;
                arrayList.get(indexOfTaskToBeMarked).removeDone();
                storage.addArrayToFile(arrayList, Chitti.numberOfTasks);
                ui.unmarked();
            } else if (input.startsWith("todo")) {
                try {
                    if (input.equals("todo")) {
                        throw new ChittiException();
                    }
                    String[] inputParts = input.split(" ", 2);
                    ToDo todo = new ToDo(inputParts[1]);
                    taskList.addTasks(todo, arrayList, ui);
                    Chitti.numberOfTasks = arrayList.size();
                    try {
                        storage.writeToFile("todo|false|" + inputParts[1], true);
                    }
                    catch(IOException ioException){
                        ui.errorWritingToFile();
                    }
                }
                catch(ChittiException a){
                    ui.emptyToDo();
                }
            } else if(input.startsWith("deadline")) {
                try {
                    if(input.equals("deadline") | !input.contains("/by")){
                        throw new ChittiException();
                    }
                    //contains deadline, and the rest
                    String[] inputSplitBySpace = input.split(" ", 2);
                    // splits into description and the by
                    String[] inputSplitByBy = inputSplitBySpace[1].split("/by", 2);
                    Deadline deadline = new Deadline(inputSplitByBy[0], inputSplitByBy[1]);
                    taskList.addTasks(deadline, arrayList, ui);
                    try {
                        storage.writeToFile("deadline|false|" + inputSplitByBy[0] + "|" + inputSplitByBy[1], true);
                    }
                    catch(IOException ioException){
                        ui.errorWritingToFile();
                    }
                }
                catch(ChittiException b){
                    ui.wrongDeadlineFormat();
                }
            } else if(input.startsWith("event")){
                try {
                    if(input.equals("event") | !input.contains("/from") | !input.contains("/to")){
                        throw new ChittiException();
                    }
                    //splits to event, and rest
                    String[] inputSplitBySpace = input.split(" ", 2);
                    //splits to string description, and after
                    String[] inputSplitByFrom = inputSplitBySpace[1].split("/from", 2);
                    //splits to from and to
                    String[] inputSplitByTo = inputSplitByFrom[1].split("/to");
                    Event event = new Event(inputSplitByFrom[0], inputSplitByTo[0], inputSplitByTo[1]);
                    taskList.addTasks(event, arrayList, ui);
                    try {
                        storage.writeToFile("event|false|"+ inputSplitByFrom[0] + "|" + inputSplitByTo[0] + "|" + inputSplitByTo[1], true);
                    }
                    catch(IOException ioException){
                        ui.errorWritingToFile();
                    }
                }
                catch(ChittiException c){
                    ui.wrongEventFormat();
                }
            } else if (input.startsWith("delete")) {
                String[] inputSplitBySpace = input.split(" ", 2);
                Task toBeDeleted = arrayList.get(Integer.parseInt(inputSplitBySpace[1]) - 1);
                ui.deleteMessage(toBeDeleted);
                String taskToBeDeleted = inputSplitBySpace[1];
                int indexToBeDeleted = Integer.parseInt(taskToBeDeleted) - 1;

                taskList.deleteTasks(indexToBeDeleted, arrayList, ui);
            }else if (input.startsWith("find")){
                String[] inputSplitBySpace = input.split(" ", 2);
                TaskList.find(arrayList, inputSplitBySpace[1], ui);
            } else {
                ui.nonExistantcommand();
            }
            input = scanner.nextLine();
        }
    }

}
