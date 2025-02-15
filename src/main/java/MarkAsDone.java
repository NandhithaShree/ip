import java.util.Scanner;

public class MarkAsDone {
    Scanner scanner =  new Scanner(System.in);
    Task[] list = new Task[100];
    String input;
    int numberOfTasks = 0;

    public MarkAsDone(){
        System.out.println("Hey! Chitti's here!");
        System.out.println("What can I do for you?");
        input = scanner.nextLine();
        while(!(input.equals("bye"))) {
            if(input.equals("list")){
                printList(list, numberOfTasks);
            } else if (input.startsWith("mark ")){
                String[] splittedInput = input.split(" ");
                list[Integer.parseInt(splittedInput[1]) - 1].addDone();
                System.out.println("Hey, I marked this task as done. Nice job! Keep crushing it!");
            } else if (input.startsWith("unmark ")) {
                String[] splittedInput = input.split(" ");
                list[Integer.parseInt(splittedInput[1]) - 1].removeDone();
                System.out.println("Oops, not done with this one yet? No worries, I’ve unmarked it for you!");
            } else if (input.startsWith("todo")) {
                try {
                    String[] splittedInput = input.split(" ", 2);
                    ToDo todo = new ToDo(splittedInput[1]);
                    addToDoDeadlineEvent(todo);
                }
                catch(IndexOutOfBoundsException index){
                    System.out.println("Oops! Please write something after todo!");
                }
            } else if(input.startsWith("deadline") && input.contains("/by")) {
                //contains deadline, and the rest
                String[] splittedInputSpace = input.split(" ", 2);
                // splits into description and the by
                String[] splittedInputBy = splittedInputSpace[1].split("/by", 2);
                Deadline deadline = new Deadline(splittedInputBy[0], splittedInputBy[1]);
                addToDoDeadlineEvent(deadline);
            } else if(input.startsWith("event") && input.contains("/from") && input.contains("/to")){
                //splits to event, and rest
                String[] splittedInputSpace = input.split(" ", 2);
                //splits to string description, and after
                String[] splittedInputFrom = splittedInputSpace[1].split("/from", 2);
                //splits to from and to
                String[] splittedInputTo = splittedInputFrom[1].split("/to");
                Event event = new Event(splittedInputFrom[0], splittedInputTo[0], splittedInputTo[1]);
                addToDoDeadlineEvent(event);
            }
            else {
                System.out.println("Sorry, I'm unsure what that means. Try: todo, deadline, or event");
//                Task task = new Task(input);
//                list[numberOfTasks] = task;
//                System.out.println("added: " + input);
//                numberOfTasks++;
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Call me if you need help");
    }

    public void printList(Task[] list, int numberOfTasks){
        int count = 0;
        while(count < numberOfTasks){
            System.out.println(count+1 + "." + list[count].toString());
            count++;
        }
    }

    public void numberOfTasksPrinter(int numberOfTasks){
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void addToDoDeadlineEvent(Task task){
        list[numberOfTasks] = task;
        System.out.println("Got it! Added this for you:");
        System.out.println(task);
        numberOfTasks++;
        numberOfTasksPrinter(numberOfTasks);
    }
}

