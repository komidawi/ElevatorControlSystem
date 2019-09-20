package elevator;

public class PickupRequest {
    private int pickupFloor;
    private int targetFloor;

    public PickupRequest(int pickupFloor, int targetFloor) {
        this.pickupFloor = pickupFloor;
        this.targetFloor = targetFloor;
    }

    public int getPickupFloor() {
        return pickupFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}
