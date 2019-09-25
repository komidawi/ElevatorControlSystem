package elevator;

import elevator.utils.MapUtils;

import java.util.*;

public class ElevatorController implements ElevatorSystem {

    public static final int MAX_ELEVATORS = 16;
    public static final int MINIMUM_FLOOR = 0;
    public static final int MAXIMUM_FLOOR = 10;
    private static int stepNumber;
    private List<Elevator> elevators = new ArrayList<>();

    public ElevatorController(int elevatorCount) {
        validateElevatorCount(elevatorCount);
        initializeElevators(elevatorCount);
    }

    private void validateElevatorCount(int elevatorCount) {
        if (elevatorCount > MAX_ELEVATORS || elevatorCount <= 0) {
            throw new RuntimeException("System is supposed to handle from 1 up to maximum " + MAX_ELEVATORS + " elevators");
        }
    }

    private void initializeElevators(int elevatorCount) {
        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(i));
        }
    }

    @Override
    public void createPickupRequest(int pickupFloor, int destinationFloor) {
        PickupRequest pickupRequest = new PickupRequest(pickupFloor, destinationFloor);

        int cheapestElevatorID = findCheapestElevator(pickupRequest);

        if (cheapestElevatorID != -1) {
            System.out.printf("Sending elevator #%d to floors [%d, %d]\n", cheapestElevatorID, pickupFloor, destinationFloor);
            addDestinationFloor(cheapestElevatorID, pickupFloor);
            addDestinationFloor(cheapestElevatorID, destinationFloor);
        } else {
            System.out.println("System does not support this case. Please wait for an elevator to be available for you.");
        }
    }

    public Integer findCheapestElevator(PickupRequest request) {
        Map<Integer, Long> costs = calculateCosts(request);

        Long minimumCost = Collections.min(costs.values());

        if (minimumCost != Integer.MAX_VALUE) {
            Integer cheapestElevatorID = MapUtils.getKey(costs, minimumCost);
            System.out.printf("Cheapest Elevator is: %d, cost: %d\n", cheapestElevatorID, minimumCost);

            return cheapestElevatorID;
        } else {
            return -1;
        }
    }

    private Map<Integer, Long> calculateCosts(PickupRequest request) {
        Map<Integer, Long> costs = new HashMap<>();

        for (Elevator elevator : elevators) {
            long cost = ElevatorCalculator.calculateTravelCost(elevator, request);
            costs.put(elevator.getID(), cost);
            System.out.printf("Elevator #%d, cost: %d\n", elevator.getID(), cost);
        }

        return costs;
    }

    @Override
    public void addDestinationFloor(int elevatorID, int destinationFloor) {
        validateDestinationFloor(destinationFloor);
        Elevator elevator = elevators.get(elevatorID);
        elevator.addDestinationFloor(destinationFloor);
    }

    @Override
    public void showStatus() {
        System.out.println("[STATUS]");
        elevators.forEach(System.out::println);
    }

    @Override
    public void nextStep() {
        System.out.printf("\n[STEP #%d]\n", stepNumber);
        stepNumber++;
        elevators.forEach(Elevator::performNextStep);
    }

    public static void validateDestinationFloor(int destinationFloor) {
        if (destinationFloor > MAXIMUM_FLOOR || destinationFloor < MINIMUM_FLOOR) {
            throw new IllegalArgumentException("Floor nr " + destinationFloor + " does not exist!");
        }
    }
}
