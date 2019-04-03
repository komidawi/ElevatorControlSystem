public interface ElevatorSystem {
    void getStatus();
    void addDestinationFloor(int elevatorID, int destinationFloor);
    void createPickupRequest(int pickupFloor, int destinationFloor);
    void nextStep();
}
