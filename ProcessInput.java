import java.util.Scanner;

public class ProcessInput {
    public static void processCommand(String command){
        Scanner scanner = new Scanner(System.in);
        String userCommand;
        try{
            switch(Integer.parseInt(command)){
                case 1:
                    ProcessShow.printShowCommands();
                    userCommand = scanner.nextLine();
                    ProcessShow.processShow(userCommand);
                    break;
                case 2:
                    ProcessAdd.printAddCommands();
                    userCommand = scanner.nextLine();
                    ProcessAdd.processAdd(userCommand);
                    break;
                default:
                    System.out.println("This command doesn't exist. Please try again");
            }
        }
        catch (NumberFormatException e){
            System.out.println("The value you have entered is not a number. Please try again");
        }
    }


}
