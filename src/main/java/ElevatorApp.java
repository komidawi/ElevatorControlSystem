public class ElevatorApp {
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(3);
        controller.getStatus();
        controller.setDestinationFloor(0, 5);
        controller.setDestinationFloor(1, 1);
        controller.nextStep();
        controller.getStatus();
        controller.nextStep();
        controller.getStatus();
        controller.setDestinationFloor(0, 0);
        controller.setDestinationFloor(1, 3);
        controller.setDestinationFloor(2, 6);
        controller.getStatus();
        controller.nextStep();
        controller.getStatus();
        controller.nextStep();
        controller.getStatus();
    }
}
