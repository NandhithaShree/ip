import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MarkAsDone {
    Scanner scanner =  new Scanner(System.in);
    ArrayList<Task> arrayList = new ArrayList<>();
    String input;
    int numberOfTasks = 0;

    public MarkAsDone(){
        try{
            addToArrayList();
        }
        catch(FileNotFoundException f){
            System.out.println("file not found...");
        }
        System.out.println("Hey! Chitti's here!");
        System.out.println("What can I do for you?");
        input = scanner.nextLine();
        while(!(input.equals("bye"))) {
            if(input.equals("list")){
                printList(arrayList, numberOfTasks);
            } else if (input.startsWith("mark ")){
                String[] splittedInput = input.split(" ");
                arrayList.get(Integer.parseInt(splittedInput[1]) - 1).addDone();
                System.out.println("Hey, I marked this task as done. Nice job! Keep crushing it!");
            } else if (input.startsWith("unmark ")) {
                String[] splittedInput = input.split(" ");
                arrayList.get(Integer.parseInt(splittedInput[1]) - 1).removeDone();
                System.out.println("Oops, not done with this one yet? No worries, I’ve unmarked it for you!");
            } else if (input.startsWith("todo")) {
                try {
                    if (input.equals("todo")) {
                        throw new ChittiException();
                    }
                    String[] splittedInput = input.split(" ", 2);
                    ToDo todo = new ToDo(splittedInput[1]);
                    addToDoDeadlineEvent(todo);
                    try {
                        writeToFile("todo|0|" + splittedInput[1]);
                    }
                    catch(IOException ioException){
                        System.out.println("Something went wrong!");
                    }
                }
                catch(ChittiException a){
                    System.out.println("Oops! Please write something after todo!");
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
                    addToDoDeadlineEvent(deadline);
                    try {
                        writeToFile("deadline|0|" + splittedInputBy[0] + "|" + splittedInputBy[1]);
                    }
                    catch(IOException ioException){
                        System.out.println("Something went wrong!");
                    }
                }
                catch(ChittiException b){
                    System.out.println("Oops! Please write it in this format: deadline description /by date/time");
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
                    addToDoDeadlineEvent(event);
                    try {
                        writeToFile("event|0|"+ splittedInputFrom[0] + "|" + splittedInputTo[0] + "|" + splittedInputTo[1]);
                    }
                    catch(IOException ioException){
                        System.out.println("Something went wrong!");
                    }
                }
                catch(ChittiException c){
                    System.out.println("Oops! Please write it in this format: event description /from date/time /to date/time");
                }
            } else if (input.startsWith("delete")) {
                String[] splittedInputSpace = input.split(" ", 2);
                System.out.println("Okay! I've removed this task:" + "\n" + arrayList.get(Integer.parseInt(splittedInputSpace[1]) - 1));
                arrayList.remove(Integer.parseInt(splittedInputSpace[1]) - 1);
                numberOfTasks--;
                numberOfTasksPrinter(numberOfTasks);
            } else {
                System.out.println("Sorry, I'm unsure what that means. Try: todo, deadline, or event");
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Call me if you need help");
    }

    public void printList(ArrayList<Task> list, int numberOfTasks){
        int count = 0;
        while(count < numberOfTasks){
            System.out.println(count+1 + "." + list.get(count).toString());
            count++;
        }
    }

    public void numberOfTasksPrinter(int numberOfTasks){
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void addToDoDeadlineEvent(Task task){
        arrayList.add(task);
        System.out.println("Got it! Added this for you:");
        System.out.println(task);
        numberOfTasks++;
        numberOfTasksPrinter(numberOfTasks);
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("./src/main/java/chitti.txt", true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public void addToArrayList() throws FileNotFoundException{
        File f = new File("./src/main/java/chitti.txt");
        Scanner s = new Scanner(f);
        String line = s.nextLine();
        while (s.hasNextLine()) {
            if(line.startsWith("todo")){
                ToDo todo = new ToDo(line.split("\\|")[2]);
                if(line.startsWith("todo|1|")){
                    todo.addDone();
                }
                arrayList.add(todo);
                numberOfTasks++;
            } else if(line.startsWith("deadline")){
                Deadline deadline = new Deadline(line.split("\\|")[2],line.split("\\|")[3]);
                if(line.startsWith("deadline|1|")){
                    deadline.addDone();
                }
                arrayList.add(deadline);
                numberOfTasks++;
            } else if(line.startsWith("event")){
                Event event = new Event(line.split("\\|")[2],line.split("\\|")[3],line.split("\\|")[4]);
                if(line.startsWith("event|1|")){
                    event.addDone();
                }
                arrayList.add(event);
                numberOfTasks++;
            }
            line = s.nextLine();
        }
    }
}

