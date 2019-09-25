package elevator.utils;

import elevator.Direction;
import elevator.Elevator;
import elevator.PickupRequest;

import static elevator.Direction.DOWNWARDS;
import static elevator.Direction.UPWARDS;

public class ElevatorUtils {
    public static Direction determineDirection(int pickupFloor, int targetFloor) {
        if (targetFloor > pickupFloor) {
            return UPWARDS;
        } else if (targetFloor < pickupFloor) {
            return DOWNWARDS;
        } else {
            return Direction.NONE;
        }
    }

    public static Direction determineDirection(PickupRequest pickupRequest) {
        return determineDirection(pickupRequest.getPickupFloor(), pickupRequest.getTargetFloor());
    }

    public static boolean isPassengerOnCourse(Elevator elevator, PickupRequest request) {
        Direction passengerDirection = determineDirection(request);
        Direction elevatorDirection = elevator.getDirection();

        if (passengerDirection == elevatorDirection) {
            switch (passengerDirection) {
                case UPWARDS:
                    return request.getPickupFloor() > elevator.getCurrentFloor();

                case DOWNWARDS:
                    return request.getPickupFloor() < elevator.getCurrentFloor();
            }
        }

        return false;
    }
}
