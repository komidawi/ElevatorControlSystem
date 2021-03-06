package elevator;

import elevator.utils.ElevatorUtils;

import java.util.*;

public class ElevatorCalculator {
    public static final int FLOOR_PASS_COST = 3;
    public static final int STOP_COST = 10;

    public static long calculateTravelCost(Elevator elevator, PickupRequest request) {
        long cost = 0;

        if (elevator.isElevatorIdle()) {
            cost += calculateCostWhenElevatorIdle(elevator, request);
        } else if (ElevatorUtils.isPassengerOnCourse(elevator, request)) {
            cost += calculateCostWhenPassengerOnCourse(elevator, request);
        } else {
            cost = Integer.MAX_VALUE;
        }

        return cost;
    }

    private static long calculateCostWhenElevatorIdle(Elevator elevator, PickupRequest request) {
        long cost = 0;

        long fromCurrentToRequestFloor = calculateFloorPassCost(elevator.getCurrentFloor(), request.getPickupFloor());
        long fromPickupToTargetFloor = calculateFloorPassCost(request.getPickupFloor(), request.getTargetFloor());
        long stops = determineStopsCount(elevator, request);
        cost += fromCurrentToRequestFloor;
        cost += fromPickupToTargetFloor;
        cost += stops * STOP_COST;

        return cost;
    }

    private static long calculateCostWhenPassengerOnCourse(Elevator elevator, PickupRequest request) {
        long cost = 0;

        long fromCurrentToTargetFloor = calculateFloorPassCost(elevator.getCurrentFloor(), request.getTargetFloor());
        cost += fromCurrentToTargetFloor;
        long stops = determineStopsCount(elevator, request);
        cost += stops * STOP_COST;

        return cost;
    }

    private static int calculateFloorPassCost(int startFloor, int finishFloor) {
        return FLOOR_PASS_COST * Math.abs(finishFloor - startFloor);
    }

    private static long determineStopsCount(Elevator elevator, PickupRequest request) {
        int targetFloor = request.getTargetFloor();
        List<Integer> destinationFloors = prepareNewDestinationFloors(elevator, request);

        return countStops(destinationFloors, targetFloor);
    }

    private static List<Integer> prepareNewDestinationFloors(Elevator elevator, PickupRequest request) {
        List<Integer> destinationFloors = new ArrayList<>(elevator.getDestinationFloors());

        destinationFloors.add(request.getPickupFloor());
        destinationFloors.add(request.getTargetFloor());
        Collections.sort(destinationFloors);

        if (elevator.getDirection() == Direction.DOWNWARDS) {
            Collections.reverse(destinationFloors);
        }

        return destinationFloors;
    }

    private static long countStops(List<Integer> floors, int targetFloor) {
        HashSet<Integer> uniqueFloors = new LinkedHashSet<>(floors);
        Iterator<Integer> iterator = uniqueFloors.iterator();

        long stops = 1;
        while (iterator.hasNext() && iterator.next() != targetFloor) {
            stops++;
        }

        return stops;
    }
}
