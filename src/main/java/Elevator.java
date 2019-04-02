public class Elevator {
    private int ID;
    private int currentFloor;
    private int destinationFloor;
    private Direction direction;


    public Elevator(int ID) {
        this.ID = ID;
        direction = Direction.NONE;
    }

    public void nextStep() {
        switch (direction) {
            case UPWARDS:
                moveUpwards();
                break;

            case DOWNWARDS:
                moveDownwards();
                break;

            case NONE:
                break;
        }

        checkIfReachedDestination();
    }

    private void checkIfReachedDestination() {
        if (this.isMoving() && currentFloor == destinationFloor) {
            System.out.println("Elevator " + ID + " reached its destination");
            direction = Direction.NONE;
        }
    }

    private void moveUpwards() {
        if (currentFloor < destinationFloor) {
            System.out.println("Elevator " + ID + " moving upwards");
            currentFloor++;
        }
    }

    private void moveDownwards() {
        if (currentFloor > destinationFloor) {
            System.out.println("Elevator " + ID + " moving downwards");
            currentFloor--;
        }
    }

    @Override
    public String toString() {
        return String.format("Elevator: %d, currentFloor: %d, destinationFloor: %d , direction: %s",
                ID, currentFloor, destinationFloor, direction);
    }

    public void setDestinationFloor(int destinationFloor) {
        validateDestinationFloor(destinationFloor);

        this.destinationFloor = destinationFloor;
        System.out.println("Elevator " + ID + " changed destination to floor: " + destinationFloor);

        determineDirection();
    }

    private void determineDirection() {
        if (destinationFloor > currentFloor) {
            direction = Direction.UPWARDS;
        } else if (destinationFloor < currentFloor) {
            direction = Direction.DOWNWARDS;
        } else {
            System.out.println("You are on this floor :)");
            direction = Direction.NONE;
        }
    }

    private void validateDestinationFloor(int destinationFloor) {
        if (destinationFloor > ElevatorController.MAXIMUM_FLOOR
                || destinationFloor < ElevatorController.MINIMUM_FLOOR) {
            throw new RuntimeException("Floor nr " + destinationFloor + " does not exist!");
        }
    }

    private boolean isMoving() {
        return direction != Direction.NONE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }
}
