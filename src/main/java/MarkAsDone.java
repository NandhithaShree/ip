import java.util.ArrayList;
import java.io.FileNotFoundException;

public class MarkAsDone {
    ArrayList<Task> arrayList = new ArrayList<>();
    public static int numberOfTasks = 0;
    Ui ui = new Ui();
    Parser parser = new Parser();

    public MarkAsDone(){
        Storage storage = new Storage();
        try{
            storage.addToArrayList(arrayList, numberOfTasks);
            numberOfTasks = arrayList.size();
        }
        catch(FileNotFoundException f){
            System.out.println("file not found...");
        }
        ui.welcomeMessage();
        parser.parse(arrayList);
        ui.byeMessage();
    }







}


