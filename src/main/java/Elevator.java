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
        if (currentFloor < getNextDestination()) {
            System.out.println("Elevator " + ID + " moving upwards from floor: " + currentFloor + " to floor: " + (currentFloor + 1));
            currentFloor++;
        }
    }

    private void moveDownwards() {
        if (currentFloor > getNextDestination()) {
            System.out.println("Elevator " + ID + " moving downwards from floor: " + currentFloor + " to floor: " + (currentFloor - 1));
            currentFloor--;
        }
    }

    private boolean isDestinationReached() {
        return this.isMoving() && currentFloor == getNextDestination();
    }

    private boolean isMoving() {
        return !isElevatorIdle();
    }

    private void handleCurrentDestination() {
        System.out.print("Elevator " + ID + " reached its destination floor: " + getNextDestination());

        destinationFloors.remove(0);

        System.out.println(", floors remaining: " + getDestinationFloors());

        if (destinationFloors.isEmpty()) {
            setElevatorIdle();
        }
    }

    public int calculateCost(PickupRequest request) {
        int cost = 0;
        Direction passengerDirection = determineDirection(request);
        List<Integer> newDestinationFloors = prepareNewDestinationFloors(request);

        if (isElevatorIdle()) {
            cost += calculateCostForIdleElevator(request);
        } else if (isPassengerOnCourse(passengerDirection, request.getPickupFloor())) {
            cost += calculateCostForPassengerOnCourse(newDestinationFloors, request.getTargetFloor());
        } else {
            cost = Integer.MAX_VALUE;
        }

        return cost;
    }

    private List<Integer> prepareNewDestinationFloors(PickupRequest request) {
        List<Integer> newDestinationFloors = new ArrayList<>(this.destinationFloors);
        newDestinationFloors.add(request.getPickupFloor());
        newDestinationFloors.add(request.getTargetFloor());
        Collections.sort(newDestinationFloors);
        return newDestinationFloors;
    }

    private int calculateCostForPassengerOnCourse(List<Integer> newDestinationFloors, int targetFloor) {
        int cost = 0;
        cost += calculateTravelCost(currentFloor, targetFloor);
        int stops = (int) determineStopsCount(newDestinationFloors, targetFloor);
        cost += ElevatorController.STOP_COST * stops;
        return cost;
    }

    private int calculateCostForIdleElevator(PickupRequest request) {
        int cost = 0;
        cost += calculateTravelCost(currentFloor, request.getPickupFloor());
        cost += calculateTravelCost(request.getPickupFloor(), request.getTargetFloor());
        return cost;
    }


    private Direction determineDirection(PickupRequest request) {
        if (request.getTargetFloor() > request.getPickupFloor()) {
            return Direction.UPWARDS;
        } else if (request.getTargetFloor() < request.getPickupFloor()) {
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

    private long determineStopsCount(List<Integer> floors, int targetFloor) {
        if (targetFloor > getLastDestination()) {
            return floors.stream().filter(floor ->
                    Collections.frequency(floors, floor) <= 1 && floor < targetFloor).count();
        } else {
            return floors.stream().filter(floor ->
                    Collections.frequency(floors, floor) <= 1 && floor <= targetFloor).count();
        }
    }

    @Override
    public String toString() {
        return String.format("Elevator: %d, currentFloor: %d, destinationFloors: %s, direction: %s",
                ID, currentFloor, destinationFloors, direction);
    }

    // TODO: refactor
    public void addDestinationFloor(int destinationFloor) {
        validateDestinationFloor(destinationFloor);

        if (direction == determineDirection(new PickupRequest(currentFloor, destinationFloor)) || isElevatorIdle()) {

            if (!destinationFloors.contains(destinationFloor)) {
                destinationFloors.add(destinationFloor);
                Collections.sort(destinationFloors);
            }

            if (determineDirection(new PickupRequest(getNextDestination(), destinationFloor)) == Direction.DOWNWARDS) {
                Collections.reverse(destinationFloors);
            }

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
        if (getNextDestination() > currentFloor) {
            direction = Direction.UPWARDS;
        } else if (getNextDestination() < currentFloor) {
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

    public int getNextDestination() {
        return destinationFloors.get(0);
    }

    public int getLastDestination() {
        return destinationFloors.get(destinationFloors.size() - 1);
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

    public List<Integer> getDestinationFloors() {
        return destinationFloors;
    }
}
