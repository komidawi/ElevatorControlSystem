package elevator;

import elevator.utils.ElevatorUtils;

import java.util.*;

public class Elevator {
    private int ID;
    private int currentFloor;
    private List<Integer> destinationFloors = new ArrayList<>();
    private Direction direction;

    public Elevator(int ID) {
        this.ID = ID;
        setElevatorIdle();
    }

    public void addDestinationFloor(int destinationFloor) {
        try {
            handleAddDestinationFloor(destinationFloor);
            System.out.println("Elevator #" + this.ID + ": added destination floor: " + destinationFloor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleAddDestinationFloor(int destinationFloor) {
        Direction destinationDirection = ElevatorUtils.determineDirection(currentFloor, destinationFloor);

        if (isElevatorIdle()) {
            destinationFloors.add(destinationFloor);
            direction = destinationDirection;
        } else if (direction == destinationDirection) {
            if (!destinationFloors.contains(destinationFloor)) {
                destinationFloors.add(destinationFloor);
                Collections.sort(destinationFloors);
            }
        } else {
            throw new RuntimeException("Currently system does not support this case. Please wait for a free elevator");
        }
    }

    public void addMultipleDestinationFloors(int[] destinationFloors) {
        for (int floor : destinationFloors) {
            addDestinationFloor(floor);
        }
    }

    public void performNextStep() {
        moveElevatorInCurrentDirection();
        handleCurrentFloor();
    }

    private void moveElevatorInCurrentDirection() {
        switch (direction) {
            case UPWARDS:
                moveUpwards();
                break;

            case DOWNWARDS:
                moveDownwards();
                break;
        }
    }

    private void handleCurrentFloor() {
        if (isDestinationReached()) {
            handleCurrentDestination();
        }
    }

    private void moveUpwards() {
        System.out.println("Elevator " + ID + " moving upwards from floor: " + currentFloor + " to floor: " + (currentFloor + 1));
        currentFloor++;
    }

    private void moveDownwards() {
        System.out.println("Elevator " + ID + " moving downwards from floor: " + currentFloor + " to floor: " + (currentFloor - 1));
        currentFloor--;
    }

    private void handleCurrentDestination() {
        destinationFloors.remove(0);

        System.out.print("Elevator " + ID + " reached its destination floor: " + getNextDestination());
        System.out.println(", floors remaining: " + getDestinationFloors());

        if (destinationFloors.isEmpty()) {
            setElevatorIdle();
        }
    }

    @Override
    public String toString() {
        return String.format("Elevator: %d, currentFloor: %d, destinationFloors: %s, direction: %s",
                ID, currentFloor, destinationFloors, direction);
    }

    private boolean isDestinationReached() {
        return this.isMoving() && currentFloor == getNextDestination();
    }

    public boolean isElevatorIdle() {
        return direction == Direction.NONE;
    }

    public void setElevatorIdle() {
        direction = Direction.NONE;
    }

    private boolean isMoving() {
        return !isElevatorIdle();
    }

    public int getNextDestination() {
        return destinationFloors.get(0);
    }

    public List<Integer> getDestinationFloors() {
        return destinationFloors;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getID() {
        return ID;
    }
}
