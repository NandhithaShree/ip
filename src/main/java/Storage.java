import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    public Storage(){}
    public void writeToFile(String textToAdd, boolean append) throws IOException {
        FileWriter fw = new FileWriter("./src/main/java/chitti.txt", append);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public void addToArrayList(ArrayList<Task> arrayList, int numberOfTasks ) throws FileNotFoundException {
        File f = new File("./src/main/java/chitti.txt");
        Scanner s = new Scanner(f);
        String line;
        while (s.hasNextLine()) {
            line = s.nextLine();
            if(line.startsWith("todo")){
                String description = line.split("\\|")[2];
                ToDo todo = new ToDo(description);
                if(line.startsWith("todo|true|")){
                    todo.addDone();
                }
                arrayList.add(todo);
                Chitti.numberOfTasks++;
            } else if(line.startsWith("deadline")){
                String[] inputSplitByPipe = line.split("\\|",3);
                String description = inputSplitByPipe[1];
                String deadline = inputSplitByPipe[2];
                Deadline taskDeadline = new Deadline(description, deadline);
                if(line.startsWith("deadline|true|")){
                    taskDeadline.addDone();
                }
                arrayList.add(taskDeadline);
                Chitti.numberOfTasks++;
            } else if(line.startsWith("event")){
                String[] inputSplitByPipe = line.split("\\|",4);
                String description = inputSplitByPipe[1];
                String from = inputSplitByPipe[2];
                String to = inputSplitByPipe[3];
                Event event = new Event(description, from, to);
                if(line.startsWith("event|true|")){
                    event.addDone();
                }
                arrayList.add(event);
                Chitti.numberOfTasks++;
            }
        }
    }

    public void addArrayToFile(ArrayList<Task> arrayList, int numberOfTasks){
        int i = 0;
        boolean append = false;
        while(i<Chitti.numberOfTasks){
            if(i != 0){
                append = true;
            }
            Task currentTask = arrayList.get(i);
            boolean isDone = currentTask.isDone;
            if(currentTask.getClass() == ToDo.class){
                try {
                    writeToFile("todo|" + isDone + "|" + currentTask.description, append);
                }
                catch(IOException ioException){
                    System.out.println("Something went wrong!");
                }
            }
            else if(currentTask.getClass() == Deadline.class){
                try {
                    writeToFile("deadline|" + isDone + "|" + currentTask.description + "|" + ((Deadline) currentTask).by, append);
                }
                catch(IOException ioException){
                    System.out.println("Something went wrong!");
                }
            }
            else if(currentTask.getClass() == Event.class){
                try {
                    writeToFile("event|" + isDone + "|" + currentTask.description + "|" + ((Event) currentTask).from + "|" + ((Event) currentTask).to, append);
                }
                catch(IOException ioException){
                    System.out.println("Something went wrong!");
                }
            }
            i++;
        }
    }
}
