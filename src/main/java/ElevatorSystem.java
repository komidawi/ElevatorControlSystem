public interface ElevatorSystem {
    void getStatus();
    void setDestinationFloor(int elevatorID, int destinationFloor);
    void createPickupRequest(int currentFloor, int destinationFloor);
    void nextStep();
}
