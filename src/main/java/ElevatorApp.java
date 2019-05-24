import java.util.Scanner;

public class ElevatorApp {

    private static ElevatorController controller;
    private static Scanner scanner;
    private static final int ELEVATOR_COUNT = 3;

    public static void main(String[] args) {
        initialize();
        run();
    }

    private static void initialize() {
        controller = new ElevatorController(ELEVATOR_COUNT);
        scanner = new Scanner(System.in);
    }

    private static void run() {
        showWelcomeMenu();
        showHelp();
        provideCommunicationWithUser();
    }

    private static void showWelcomeMenu() {
        System.out.println("Welcome to Elevator Control System");
        System.out.println("You can always show help menu by choosing option 0.");
    }

    private static void showHelp() {
        System.out.println("What would you like to do?");
        System.out.println("1. Show status");
        System.out.println("2. Create Pickup Request");
        System.out.println("3. Add destination floor");
        System.out.println("4. Next step");
        System.out.println("9. Exit app");
    }

    private static void provideCommunicationWithUser() {
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

    private static void showStatus() {
        controller.showStatus();
    }

    private static void showCreatePickupRequestMenu() {
        System.out.println("Specify pickupFloor and targetFloor separated by spaces");
        int pickupFloor = scanner.nextInt();
        int destinationFloor = scanner.nextInt();
        controller.createPickupRequest(pickupFloor, destinationFloor);
    }

    private static void showAddDestinationFloorMenu() {
        System.out.println("Specify elevatorID and targetFloor separated by spaces");
        int elevatorID = scanner.nextInt();
        int destinationFloor = scanner.nextInt();
        controller.addDestinationFloor(elevatorID, destinationFloor);
    }

    private static void nextStep() {
        controller.nextStep();
    }
}
