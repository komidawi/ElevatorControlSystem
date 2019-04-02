public class ElevatorApp {
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(1);
        controller.getStatus();
        controller.setDestinationFloor(0, 1);
        controller.getStatus();
        controller.calculateArrivalCosts(2, 5);
    }
}
