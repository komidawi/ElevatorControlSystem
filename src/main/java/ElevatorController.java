import java.util.ArrayList;
import java.util.List;

public class ElevatorController implements ElevatorSystem {

    public static final int MINIMUM_FLOOR = 0;
    public static final int MAXIMUM_FLOOR = 10;
    private List<Elevator> elevators;

    public ElevatorController(int elevatorCount) {
        initializeElevators(elevatorCount);
    }

    private void initializeElevators(int elevatorCount) {
        elevators = new ArrayList<>();
        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(i));
        }
    }

    @Override
    public void getStatus() {
        elevators.forEach(System.out::println);
    }

    @Override
    public void update(int elevatorID, int currentFloor, int destinationFloor) {

    }

    @Override
    public void createPickupRequest(int floor, Direction direction) {

    }

    @Override
    public void nextStep() {
        elevators.forEach(Elevator::nextStep);
    }
}
