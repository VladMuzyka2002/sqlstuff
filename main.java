import java.util.Scanner;

public class main {
    static SQLServer server;
    public static void main(String[] args){
        boolean appLoop = true;
        Scanner scanner = new Scanner(System.in);
        while (appLoop){
            server = new SQLServer();
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
