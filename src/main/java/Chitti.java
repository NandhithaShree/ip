import java.util.ArrayList;

public class Chitti {
    ArrayList<Task> arrayList = new ArrayList<>();
    public static int numberOfTasks = 0;
    Ui ui = new Ui();
    Parser parser = new Parser();

    public static void main(String[] args) {
        Chitti chitti = new Chitti(); // Create an instance of Chitti
        Storage storage = new Storage();
        storage.addToArrayList(chitti.arrayList);

        chitti.ui.welcomeMessage();
        chitti.parser.parse(chitti.arrayList, chitti.ui, storage);
        chitti.ui.byeMessage();
    }
}
