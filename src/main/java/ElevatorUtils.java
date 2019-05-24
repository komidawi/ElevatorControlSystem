public class ElevatorUtils {
    public static int calculateTravelCost(int startFloor, int finishFloor) {
        return ElevatorController.FLOOR_PASS_COST * Math.abs(finishFloor - startFloor);
    }

    public static Direction determineDirection(int pickupFloor, int targetFloor) {
        if (targetFloor > pickupFloor) {
            return Direction.UPWARDS;
        } else if (targetFloor < pickupFloor) {
            return Direction.DOWNWARDS;
        } else {
            return Direction.NONE;
        }
    }

    public static Direction determineDirection(PickupRequest pickupRequest) {
        return determineDirection(pickupRequest.getPickupFloor(), pickupRequest.getTargetFloor());
    }
}
