import java.util.ArrayList;
import java.util.List;

public class ElevatorController implements ElevatorSystem {

    public static final int MAX_ELEVATORS = 16;
    public static final int MINIMUM_FLOOR = 0;
    public static final int MAXIMUM_FLOOR = 10;
    private static int stepNumber;
    private List<Elevator> elevators;

    public ElevatorController(int elevatorCount) {
        validateElevatorCount(elevatorCount);
        initializeElevators(elevatorCount);
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
    public void createPickupRequest(int currentFloor, int destinationFloor) {

    }


    @Override
    public void nextStep() {
        System.out.printf("\n[STEP #%d]\n", stepNumber);
        stepNumber++;
        elevators.forEach(Elevator::nextStep);
    }
}
