import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The {@code Storage} class is responsible for reading and writing task data to a file.
 * It provides methods for writing task data to a file, adding tasks to an ArrayList from a file, and saving the updated list of tasks back to the file.
 */
public class Storage {
    public Storage(){}
    /**
     * Writes a specified text to Chitti.txt. The method allows the option to append the text to the file.
     *
     * @param textToAdd The text to be written to the file.
     * @param append A boolean flag that indicates whether to append to the file (true) or overwrite it (false).
     * @throws IOException If there is an error while writing to the file.
     */
    public void writeToFile(String textToAdd, boolean append) throws IOException {
        FileWriter fw = new FileWriter("./src/main/java/chitti.txt", append);
        fw.write(textToAdd + "\n");
        fw.close();
    }
    /**
     * Adds tasks from a file into the provided ArrayList of tasks. It reads the file line by line,
     * parses each task type (ToDo, Deadline, Event), and adds them to the ArrayList.
     *
     * @param arrayList The ArrayList of tasks where parsed tasks will be added.
     * @throws FileNotFoundException If the file is not found or does not exist.
     */
    public void addToArrayList(ArrayList<Task> arrayList ) throws FileNotFoundException {
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
                String[] inputSplitByPipe = line.split("\\|",4);
                String description = inputSplitByPipe[2];
                String deadline = inputSplitByPipe[3];
                Deadline taskDeadline = new Deadline(description, deadline);
                if(line.startsWith("deadline|true|")){
                    taskDeadline.addDone();
                }
                arrayList.add(taskDeadline);
                Chitti.numberOfTasks++;
            } else if(line.startsWith("event")){
                String[] inputSplitByPipe = line.split("\\|",5);
                String description = inputSplitByPipe[2];
                String from = inputSplitByPipe[3];
                String to = inputSplitByPipe[4];
                Event event = new Event(description, from, to);
                if(line.startsWith("event|true|")){
                    event.addDone();
                }
                arrayList.add(event);
                Chitti.numberOfTasks++;
            }
        }
    }
    /**
     * Saves the current ArrayList of tasks to a file. The method serializes each task's properties
     * (including its completion status) and writes them in a specific format for each task type (ToDo, Deadline, Event).
     * The data is appended to the file each time the method is called.
     *
     * @param arrayList The ArrayList of tasks to be saved to the file.
     * @param numberOfTasks The total number of tasks to be saved.
     */
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
