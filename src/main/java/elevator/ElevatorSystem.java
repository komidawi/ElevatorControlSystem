package elevator;

public interface ElevatorSystem {
    void showStatus();
    void addDestinationFloor(int elevatorID, int destinationFloor);
    void createPickupRequest(int pickupFloor, int destinationFloor);
    void nextStep();
}
