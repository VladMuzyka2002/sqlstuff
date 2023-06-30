public class ProcessShow {
    public static void processShow(String command){
        try{
            switch(Integer.parseInt(command)){
                case 1:
                    for (User u : User.findall()) System.out.println("Name: " + u.name + " ~~~~~ Email: " + u.email);
                    break;
                default:
                    System.out.println("This command doesn't exist. Please try again");
            }
        }
        catch (NumberFormatException e){
            System.out.println("The value you have entered is not a number. Please try again");
        }
    }

    public static void printShowCommands(){
        System.out.println("Please enter a command");
        System.out.println("1: Show all users");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
