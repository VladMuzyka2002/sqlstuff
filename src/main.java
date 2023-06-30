import java.util.List;
import java.util.Scanner;

public class main {
    static SQLServer server;
    public static void main(String[] args){
        //List<User> users = User.all();


        User u1 = User.find(29);
        u1.delete();

        boolean appLoop = true;
        Scanner scanner = new Scanner(System.in);
        while (appLoop){
            printCommands();
            String userCommand = scanner.nextLine();
            ProcessInput.processCommand(userCommand);
        }
    }

    public static void printCommands(){
        System.out.println("Please enter a command");
        System.out.println("1: Show contents of a table");
        System.out.println("2: Add contents to a table");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
