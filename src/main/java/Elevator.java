public class Elevator {
    private int ID;
    private int floor;
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
    }

    private void moveUpwards() {
        if (floor < ElevatorController.MAXIMUM_FLOOR) {
            System.out.println("Moving upwards");
            floor++;
        }

        if (floor == ElevatorController.MAXIMUM_FLOOR) {
            direction = Direction.NONE;
        }
    }

    private void moveDownwards() {
        if (floor > ElevatorController.MINIMUM_FLOOR) {
            System.out.println("Moving downwards");
            floor--;
        }

        if (floor == ElevatorController.MINIMUM_FLOOR) {
            direction = Direction.NONE;
        }
    }

    @Override
    public String toString() {
        return String.format("Elevator: %d, floor: %d, direction: %s", ID, floor, direction);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
