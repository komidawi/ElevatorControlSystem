import java.util.Scanner;

public class ElevatorApp {

    private static ElevatorController controller;
    private static Scanner scanner;

    public static void main(String[] args) {
        controller = new ElevatorController(3);

        showWelcomeMenu();
        showHelp();

        scanner = new Scanner(System.in);
        boolean ifContinue = true;
        while (ifContinue) {

            System.out.print("> ");
            int chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1:
                    showStatus();
                    break;
                case 2:
                    showCreatePickupRequestMenu();
                    break;
                case 3:
                    showAddDestinationFloorMenu();
                    break;
                case 4:
                    nextStep();
                    break;
                case 9:
                    ifContinue = false;
                    break;
                default:
                    showHelp();
                    break;
            }
        }
    }

    private static void nextStep() {
        controller.nextStep();
    }

    private static void showAddDestinationFloorMenu() {
        System.out.println("Specify elevatorID and destinationFloor separated by spaces");
        int elevatorID = scanner.nextInt();
        int destinationFloor = scanner.nextInt();
        controller.addDestinationFloor(elevatorID, destinationFloor);
    }

    private static void showWelcomeMenu() {
        System.out.println("Welcome to Elevator Control System");
        System.out.println("You can always show help menu by choosing option 0.");
    }

    private static void showCreatePickupRequestMenu() {
        System.out.println("Specify pickupFloor and destinationFloor separated by spaces");
        int pickupFloor = scanner.nextInt();
        int destinationFloor = scanner.nextInt();
        controller.createPickupRequest(pickupFloor, destinationFloor);
    }

    private static void showStatus() {
        controller.showStatus();
    }

    private static void showHelp() {
        System.out.println("What would you like to do?");
        System.out.println("1. Show status");
        System.out.println("2. Create Pickup Request");
        System.out.println("3. Add destination floor");
        System.out.println("4. Next step");
        System.out.println("9. Exit app");
    }
}
