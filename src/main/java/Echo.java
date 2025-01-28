import java.util.Scanner;
public class Echo {
    Scanner scanner = new Scanner(System.in);
    String input;
    public Echo() {
        System.out.println("Yo! Chitti's here!");
        System.out.println("What can I do for you?");
        input = scanner.nextLine();
        while(!(input.equals("bye"))){
            System.out.println(input);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hit me up if you need help");
    }
}
