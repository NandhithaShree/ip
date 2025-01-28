import java.util.Scanner;

public class MarkasDone {
    Scanner scanner =  new Scanner(System.in);
    Task[] list = new Task[100];
    String input;
    int numberofTasks = 0;
    public  MarkasDone(){
        System.out.println("Yo! Chitti's here!");
        System.out.println("What can I do for you?");
        input = scanner.nextLine();
        while(!(input.equals("bye"))) {
            if(input.equals("list")){
                PrintList(list, numberofTasks);
            } else if (input.startsWith("mark ")){
                String[] splittedInput = input.split(" ");
                list[Integer.parseInt(splittedInput[1]) - 1].addDone();
                System.out.println("Yo, I marked this task as done. Sick job, bro! Keep crushing it!");
            } else if (input.startsWith("unmark ")) {
                String[] splittedInput = input.split(" ");
                list[Integer.parseInt(splittedInput[1]) - 1].removeDone();
                System.out.println("Oops, not done with this one yet? No sweat, I’ve unmarked it for ya!");
            } else {
                Task task = new Task(input);
                list[numberofTasks] = task;
                System.out.println("added: " + input);
                numberofTasks++;
            }
            input = scanner.nextLine();

        }
        System.out.println("Bye. Hit me up if you need help");
    }
    public void PrintList(Task[] list, int numberofTasks){
        int count = 0;
        while(count < numberofTasks){
            System.out.println(count+1 + ". [" +  list[count].getStatusIcon() + "]"+ list[count].description);
            count++;
        }
    }
}

