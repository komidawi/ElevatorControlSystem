import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorCalculator {

    public static int calculateTravelCost(Elevator elevator, PickupRequest request) {
        int cost = 0;
        Direction passengerDirection = ElevatorUtils.determineDirection(request);

        if (elevator.isElevatorIdle()) {
            cost += calculateCostForIdleElevator(elevator, request);
        } else if (elevator.isPassengerOnCourse(passengerDirection, request.getPickupFloor())) {
            cost += calculateCostForPassengerOnCourse(elevator, request);
        } else {
            cost = Integer.MAX_VALUE;
        }

        return cost;
    }

    private static int calculateCostForIdleElevator(Elevator elevator, PickupRequest request) {
        int cost = 0;

        int fromCurrentToRequestFloor = calculateFloorPassCost(elevator.getCurrentFloor(), request.getPickupFloor());
        int fromPickupToTargetFloor = calculateFloorPassCost(request.getPickupFloor(), request.getTargetFloor());
        cost += fromCurrentToRequestFloor;
        cost += fromPickupToTargetFloor;

        return cost;
    }

    public static int calculateFloorPassCost(int startFloor, int finishFloor) {
        return ElevatorController.FLOOR_PASS_COST * Math.abs(finishFloor - startFloor);
    }

    private static int calculateCostForPassengerOnCourse(Elevator elevator, PickupRequest request) {
        int cost = 0;

        int fromCurrentToTargetFloor = calculateFloorPassCost(elevator.getCurrentFloor(), request.getTargetFloor());
        int stops = (int) determineStopsCount(elevator, request);
        cost += fromCurrentToTargetFloor;
        cost += ElevatorController.STOP_COST * stops;

        return cost;
    }

    private static List<Integer> prepareNewDestinationFloors(Elevator elevator, PickupRequest request) {
        List<Integer> newDestinationFloors = new ArrayList<>(elevator.getDestinationFloors());

        newDestinationFloors.add(request.getPickupFloor());
        newDestinationFloors.add(request.getTargetFloor());
        Collections.sort(newDestinationFloors);

        return newDestinationFloors;
    }

    private static long determineStopsCount(Elevator elevator, PickupRequest request) {
        int targetFloor = request.getTargetFloor();
        List<Integer> newDestinationFloors = prepareNewDestinationFloors(elevator, request);

        if (targetFloor > elevator.getLastDestination()) {
            return countStopsWhenTargetBeforeDestination(newDestinationFloors, targetFloor);
        } else {
            return countStopsWhenTargetNotBeforeDestination(newDestinationFloors, targetFloor);
        }
    }

    private static long countStopsWhenTargetBeforeDestination(List<Integer> floors, int targetFloor) {
        return floors.stream().filter(floor ->
                Collections.frequency(floors, floor) <= 1   // don't count the same floor twice
                        && floor < targetFloor).count();
    }

    private static long countStopsWhenTargetNotBeforeDestination(List<Integer> floors, int targetFloor) {
        return floors.stream().filter(floor ->
                Collections.frequency(floors, floor) <= 1   // don't count the same floor twice
                        && floor <= targetFloor).count();
    }
}
