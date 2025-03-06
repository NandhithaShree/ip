import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    TaskList taskList = new TaskList();
    public void parse(ArrayList<Task> arrayList){
        Scanner scanner =  new Scanner(System.in);
        String input = scanner.nextLine();
        Ui ui = new Ui();
        Storage storage = new Storage();
        while(!(input.equals("bye"))) {
            if(input.equals("list")){
                ui.printList(arrayList);
            } else if (input.startsWith("mark ")){
                String[] splittedInput = input.split(" ");
                arrayList.get(Integer.parseInt(splittedInput[1]) - 1).addDone();
                storage.addArrayToFile(arrayList, MarkAsDone.numberOfTasks);
                ui.marked();
            } else if (input.startsWith("unmark ")) {
                String[] splittedInput = input.split(" ");
                arrayList.get(Integer.parseInt(splittedInput[1]) - 1).removeDone();
                storage.addArrayToFile(arrayList, MarkAsDone.numberOfTasks);
                ui.unmarked();
            } else if (input.startsWith("todo")) {
                try {
                    if (input.equals("todo")) {
                        throw new ChittiException();
                    }
                    String[] splittedInput = input.split(" ", 2);
                    ToDo todo = new ToDo(splittedInput[1]);
                    taskList.addTasks(todo, arrayList);
                    MarkAsDone.numberOfTasks = arrayList.size();
                    try {
                        storage.writeToFile("todo|false|" + splittedInput[1], true);
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
                    String[] splittedInputSpace = input.split(" ", 2);
                    // splits into description and the by
                    String[] splittedInputBy = splittedInputSpace[1].split("/by", 2);
                    Deadline deadline = new Deadline(splittedInputBy[0], splittedInputBy[1]);
                    taskList.addTasks(deadline, arrayList);
                    try {
                        storage.writeToFile("deadline|false|" + splittedInputBy[0] + "|" + splittedInputBy[1], true);
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
                    String[] splittedInputSpace = input.split(" ", 2);
                    //splits to string description, and after
                    String[] splittedInputFrom = splittedInputSpace[1].split("/from", 2);
                    //splits to from and to
                    String[] splittedInputTo = splittedInputFrom[1].split("/to");
                    Event event = new Event(splittedInputFrom[0], splittedInputTo[0], splittedInputTo[1]);
                    taskList.addTasks(event, arrayList);
                    try {
                        storage.writeToFile("event|false|"+ splittedInputFrom[0] + "|" + splittedInputTo[0] + "|" + splittedInputTo[1], true);
                    }
                    catch(IOException ioException){
                        ui.errorWritingToFile();
                    }
                }
                catch(ChittiException c){
                    ui.wrongEventFormat();
                }
            } else if (input.startsWith("delete")) {
                String[] splittedInputSpace = input.split(" ", 2);
                Task toBeDeleted = arrayList.get(Integer.parseInt(splittedInputSpace[1]) - 1);
                ui.deleteMessage(toBeDeleted);
                taskList.deleteTasks(Integer.parseInt(splittedInputSpace[1]) - 1, arrayList);
            }else if (input.startsWith("find")){
                String[] splittedInputSpace = input.split(" ", 2);
                TaskList.find(arrayList, splittedInputSpace[1]);
            } else {
                ui.nonExistantcommand();
            }
            input = scanner.nextLine();
        }
    }

}
