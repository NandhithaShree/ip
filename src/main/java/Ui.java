import java.util.ArrayList;

public class Ui {
    public void welcomeMessage(){
        System.out.println("Hey! Chitti's here!");
        System.out.println("What can I do for you?");
    }

    public void printList(ArrayList<Task> list){
        int count = 0;
        if(Chitti.numberOfTasks == 0){
            System.out.println("YEY!You have no tasks");
        }
        while(count < Chitti.numberOfTasks){
            System.out.println(count+1 + "." + list.get(count).toString());
            count++;
        }
    }

    public void printTask(Task task, int number){
        System.out.println(number + "." + task);
    }

    public void numberOfTasksPrinter(){
        System.out.println("Now you have " + Chitti.numberOfTasks + " tasks in the list.");
    }

    public void marked(){
        System.out.println("Hey, I marked this task as done. Nice job! Keep crushing it!");
    }

    public void unmarked(){
        System.out.println("Oops, not done with this one yet? No worries, I’ve unmarked it for you!");
    }

    public void errorWritingToFile(){
        System.out.println("Something went wrong!");
    }

    public void emptyToDo(){
        System.out.println("Oops! Please write something after todo!");
    }

    public void wrongDeadlineFormat(){
        System.out.println("Oops! Please write it in this format: deadline description /by date/time");
    }

    public void wrongEventFormat(){
        System.out.println("Oops! Please write it in this format: event description /from date/time /to date/time");
    }

    public void nonExistantcommand(){
        System.out.println("Sorry, I'm unsure what that means. Try: todo, deadline, or event");
    }

    public void addMessage(Task task){
        System.out.println("Got it! Added this for you:");
        System.out.println(task);
    }

    public void deleteMessage(Task toBeDeleted){
        System.out.println("Okay! I've removed this task:");
        System.out.println("\n" + toBeDeleted);
    }

    public void byeMessage(){
        System.out.println("Bye. Call me if you need help");
    }
}
