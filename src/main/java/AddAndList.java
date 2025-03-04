import java.util.Scanner;

public class AddAndList {
    Scanner scanner =  new Scanner(System.in);
    String[] list = new String[100];
    String input;
    int index = 0;
    int numberOfTasks = 0;

    public AddAndList(){
        System.out.println("Hey! Chitti's here!");
        System.out.println("What can I do for you?");
        input = scanner.nextLine();
        while(!(input.equals("bye"))) {
            if(!(input.equals("list"))) {
                list[index] = input;
                System.out.println("added: " + input);
                index++;
                numberOfTasks++;
                input = scanner.nextLine();
            }
            else{
                printList(list, numberOfTasks);
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye. Call me if you need help");
    }

    public void printList(String[] list, int numberofTasks){
        int count = 0;
        while(count < numberofTasks){
            System.out.println(count+1 + ". " + list[count]);
            count++;
        }
    }
}
