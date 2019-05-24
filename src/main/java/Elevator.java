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

    public void setElevatorIdle() {
        setDirection(Direction.NONE);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int calculateCost(PickupRequest request) {
        int cost = 0;
        Direction passengerDirection = ElevatorUtils.determineDirection(request);
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

    private int calculateCostForIdleElevator(PickupRequest request) {
        int cost = 0;
        cost += ElevatorUtils.calculateTravelCost(currentFloor, request.getPickupFloor());
        cost += ElevatorUtils.calculateTravelCost(request.getPickupFloor(), request.getTargetFloor());
        return cost;
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

    private int calculateCostForPassengerOnCourse(List<Integer> newDestinationFloors, int targetFloor) {
        int cost = 0;
        cost += ElevatorUtils.calculateTravelCost(currentFloor, targetFloor);
        int stops = (int) determineStopsCount(newDestinationFloors, targetFloor);
        cost += ElevatorController.STOP_COST * stops;
        return cost;
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

    public int getLastDestination() {
        return destinationFloors.get(destinationFloors.size() - 1);
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

    public boolean isElevatorIdle() {
        return direction == Direction.NONE;
    }

    public int getNextDestination() {
        return destinationFloors.get(0);
    }

    private void handleCurrentDestination() {
        System.out.print("Elevator " + ID + " reached its destination floor: " + getNextDestination());

        destinationFloors.remove(0);

        System.out.println(", floors remaining: " + getDestinationFloors());

        if (destinationFloors.isEmpty()) {
            setElevatorIdle();
        }
    }

    public List<Integer> getDestinationFloors() {
        return destinationFloors;
    }

    @Override
    public String toString() {
        return String.format("Elevator: %d, currentFloor: %d, destinationFloors: %s, direction: %s",
                ID, currentFloor, destinationFloors, direction);
    }

    public boolean addDestinationFloor(int destinationFloor) {
        try {
            ElevatorController.validateDestinationFloor(destinationFloor);

            Direction destinationDirection = ElevatorUtils.determineDirection(currentFloor, destinationFloor);

            if (isElevatorIdle()) {
                destinationFloors.add(destinationFloor);
                setDirection(destinationDirection);
                return true;
            } else if (direction == destinationDirection) {
                if (!destinationFloors.contains(destinationFloor)) {
                    destinationFloors.add(destinationFloor);
                    Collections.sort(destinationFloors);
                    return true;
                }
            } else {
                System.out.println("Currently system does not support this case. Please wait for a free elevator");
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public int getID() {
        return ID;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
