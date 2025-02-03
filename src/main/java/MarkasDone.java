import java.util.Scanner;

public class MarkasDone {
    Scanner scanner =  new Scanner(System.in);
    Task[] list = new Task[100];
    String input;
    int numberOfTasks = 0;
    public  MarkasDone(){
        System.out.println("Hey! Chitti's here!");
        System.out.println("What can I do for you?");
        input = scanner.nextLine();
        while(!(input.equals("bye"))) {
            if(input.equals("list")){
                PrintList(list, numberOfTasks);
            } else if (input.startsWith("mark ")){
                String[] splittedInput = input.split(" ");
                list[Integer.parseInt(splittedInput[1]) - 1].addDone();
                System.out.println("Hey, I marked this task as done. Nice job! Keep crushing it!");
            } else if (input.startsWith("unmark ")) {
                String[] splittedInput = input.split(" ");
                list[Integer.parseInt(splittedInput[1]) - 1].removeDone();
                System.out.println("Oops, not done with this one yet? No worries, I’ve unmarked it for you!");
            } else {
                Task task = new Task(input);
                list[numberOfTasks] = task;
                System.out.println("added: " + input);
                numberOfTasks++;
            }
            input = scanner.nextLine();

        }
        System.out.println("Bye. Call me if you need help");
    }
    public void PrintList(Task[] list, int numberOfTasks){
        int count = 0;
        while(count < numberOfTasks){
            System.out.println(count+1 + ". [" +  list[count].getStatusIcon() + "]"+ list[count].description);
            count++;
        }
    }
}

