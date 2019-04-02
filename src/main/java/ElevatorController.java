import java.util.ArrayList;
import java.util.List;

public class ElevatorController implements ElevatorSystem {

    public static final int MAX_ELEVATORS = 16;
    public static final int MINIMUM_FLOOR = 0;
    public static final int MAXIMUM_FLOOR = 10;
    public static final int FLOOR_PASS_COST = 3;
    public static final int STOP_COST = 10;
    private static int stepNumber;
    private List<Elevator> elevators;

    public ElevatorController(int elevatorCount) {
        validateElevatorCount(elevatorCount);
        initializeElevators(elevatorCount);
    }

    public void calculateArrivalCosts(int pickupFloor, int destinationFloor) {
        for (Elevator elevator : elevators) {
            int cost = elevator.calculateCost(pickupFloor, destinationFloor);
            System.out.printf("Elevator #%d, arrivalCost: %d\n", elevator.getID(), cost);
        }
    }

    private void validateElevatorCount(int elevatorCount) {
        if (elevatorCount > MAX_ELEVATORS || elevatorCount < 0) {
            throw new RuntimeException("System is supposed to handle from 0 up to maximum 16 elevators");
        }
    }

    private void initializeElevators(int elevatorCount) {
        elevators = new ArrayList<>();
        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(i));
        }
    }

    @Override
    public void getStatus() {
        System.out.println("[STATUS]");
        elevators.forEach(System.out::println);
    }

    @Override
    public void setDestinationFloor(int elevatorID, int destinationFloor) {
        Elevator elevator = elevators.get(elevatorID);
        elevator.setDestinationFloor(destinationFloor);
    }

    @Override
    public void createPickupRequest(int pickupFloor, int destinationFloor) {

    }

    public Elevator getElevator(int ID) {
        return elevators.get(ID);
    }

    @Override
    public void nextStep() {
        System.out.printf("\n[STEP #%d]\n", stepNumber);
        stepNumber++;
        elevators.forEach(Elevator::nextStep);
    }
}
