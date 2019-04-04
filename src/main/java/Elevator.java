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

        if (isDestinationReached()) {
            handleCurrentDestination();
        }
    }

    private void moveUpwards() {
        if (currentFloor < getClosestDestination()) {
            System.out.println("Elevator " + ID + " moving upwards");
            currentFloor++;
        }
    }

    private void moveDownwards() {
        if (currentFloor > getClosestDestination()) {
            System.out.println("Elevator " + ID + " moving downwards");
            currentFloor--;
        }
    }

    private boolean isDestinationReached() {
        return this.isMoving() && currentFloor == getClosestDestination();
    }

    private boolean isMoving() {
        return !isElevatorIdle();
    }

    private void handleCurrentDestination() {
        System.out.println("Elevator " + ID + " reached its destination");

        destinationFloors.remove(0);

        if (destinationFloors.isEmpty()) {
            setElevatorIdle();
        }
    }

    public int calculateCost(int pickupFloor, int targetFloor) {
        int cost = 0;
        Direction passengerDirection = determinePassengerDirection(pickupFloor, targetFloor);
        Set<Integer> uniqueNewDestinationFloors = prepareUniqueDestinationFloors(pickupFloor, targetFloor);

        if (isElevatorIdle()) {
            cost += calculateCostForIdleElevator(pickupFloor, targetFloor);
        } else if (isPassengerOnCourse(passengerDirection, pickupFloor)) {
            cost += calculateCostForPassengerOnCourse(uniqueNewDestinationFloors, targetFloor);
        } else {
            System.out.println("Cannot take passenger. Please wait.");
        }

        return cost;
    }

    private Set<Integer> prepareUniqueDestinationFloors(int pickupFloor, int targetFloor) {
        List<Integer> newDestinationFloors = new ArrayList<>(this.destinationFloors);
        newDestinationFloors.add(pickupFloor);
        newDestinationFloors.add(targetFloor);
        Collections.sort(newDestinationFloors);
        return new HashSet<>(newDestinationFloors);
    }

    private int calculateCostForPassengerOnCourse(Set<Integer> uniqueNewDestinationFloors, int targetFloor) {
        int cost = 0;
        cost += calculateTravelCost(currentFloor, targetFloor);
        int stops = (int) determineStopsCount(uniqueNewDestinationFloors, targetFloor);
        cost += ElevatorController.STOP_COST * stops;
        return cost;
    }

    private int calculateCostForIdleElevator(int pickupFloor, int targetFloor) {
        int cost = 0;
        cost += calculateTravelCost(currentFloor, pickupFloor);
        cost += calculateTravelCost(pickupFloor, targetFloor);
        return cost;
    }


    private Direction determinePassengerDirection(int pickupFloor, int targetFloor) {
        if (targetFloor > pickupFloor) {
            return Direction.UPWARDS;
        } else if (targetFloor < pickupFloor) {
            return Direction.DOWNWARDS;
        } else {
            return Direction.NONE;
        }
    }

    private int calculateTravelCost(int startFloor, int finishFloor) {
        return ElevatorController.FLOOR_PASS_COST * Math.abs(finishFloor - startFloor);
    }

    private boolean isPassengerOnCourse(Direction passengerDirection, int pickupFloor) {
        if (this.direction == passengerDirection) {
            switch (direction) {
                case UPWARDS:
                    return pickupFloor > currentFloor;

                case DOWNWARDS:
                    return pickupFloor < currentFloor;
            }
        }

        return false;
    }

    private long determineStopsCount(Set<Integer> floors, int targetFloor) {
        return floors.stream().filter(floor -> floor <= targetFloor).count();
    }

    @Override
    public String toString() {
        return String.format("Elevator: %d, currentFloor: %d, destinationFloors: %s , direction: %s",
                ID, currentFloor, destinationFloors, direction);
    }

    public void addDestinationFloor(int destinationFloor) {
        validateDestinationFloor(destinationFloor);

        if (direction == determinePassengerDirection(currentFloor, destinationFloor) || isElevatorIdle()) {
            destinationFloors.add(destinationFloor);
            Collections.sort(destinationFloors);

            if (direction == Direction.DOWNWARDS) {
                Collections.reverse(destinationFloors);
            }

            System.out.println("Elevator " + ID + " added destination to floor: " + destinationFloor
                    + ", current destinationFloors: " + destinationFloors);

            determineAndSetDirection();
        } else {
            System.out.println("Currently system does not support this case. Please wait for a free elevator");
        }
    }

    private void validateDestinationFloor(int destinationFloor) {
        if (destinationFloor > ElevatorController.MAXIMUM_FLOOR
                || destinationFloor < ElevatorController.MINIMUM_FLOOR) {
            throw new RuntimeException("Floor nr " + destinationFloor + " does not exist!");
        }
    }

    private void determineAndSetDirection() {
        if (getClosestDestination() > currentFloor) {
            direction = Direction.UPWARDS;
        } else if (getClosestDestination() < currentFloor) {
            direction = Direction.DOWNWARDS;
        } else {
            System.out.println("You are on this floor :)");
            setElevatorIdle();
        }
    }

    public boolean isElevatorIdle() {
        return direction == Direction.NONE;
    }

    public void setElevatorIdle() {
        setDirection(Direction.NONE);
    }

    public int getClosestDestination() {
        return destinationFloors.get(0);
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
}
