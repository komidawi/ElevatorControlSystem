public interface ElevatorSystem {
    void getStatus();
    void update(int elevatorID, int currentFloor, int destinationFloor);
    void createPickupRequest(int floor, Direction direction);
    void nextStep();
}
