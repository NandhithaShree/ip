import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Chitti {
    ArrayList<Task> arrayList = new ArrayList<>();
    public static int numberOfTasks = 0;
    Ui ui = new Ui();
    Parser parser = new Parser();

    public static void main(String[] args) {
        Chitti chitti = new Chitti(); // Create an instance of Chitti
        Storage storage = new Storage();

        try {
            storage.addToArrayList(chitti.arrayList, numberOfTasks);
        } catch (FileNotFoundException f) {
            System.out.println("file not found...");
        }

        chitti.ui.welcomeMessage();
        chitti.parser.parse(chitti.arrayList, chitti.ui, storage);
        chitti.ui.byeMessage();
    }
}
