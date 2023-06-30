import java.util.Scanner;

public class ProcessAdd {
    static Scanner scanner = new Scanner(System.in);
    public static void processAdd(String command){
        try{
            switch(Integer.parseInt(command)){
                case 1:
                    addUser();
                    break;
                default:
                    System.out.println("This command doesn't exist. Please try again");
            }
        }
        catch (NumberFormatException e){
            System.out.println("The value you have entered is not a number. Please try again");
        }
    }

    private static void addUser() {
        System.out.println("Name of the user:");
        String userName = scanner.nextLine();
        System.out.println("Email of the user:");
        String userEmail = scanner.nextLine();
        new User(userName, userEmail).save();
    }

    public static void printAddCommands(){
        System.out.println("Please enter a command");
        System.out.println("1: Add a new user");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
