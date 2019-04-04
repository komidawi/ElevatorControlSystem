import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCases {

    @Test
    public void testCase1() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(2);
        elevator.addDestinationFloor(1);
        int cost = 5 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 1)));
    }

    @Test
    public void testCase2() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(3);
        elevator.addDestinationFloor(5);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 1)));
    }

    @Test
    public void testCase3() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(1);
        elevator.addDestinationFloor(7);
        int cost = 12 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 1)));
    }

    @Test
    public void testCase4() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(1);
        int cost = 5 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testCase5() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(5);
        elevator.addDestinationFloor(7);
        int cost = 10 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testCase6() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(3);
        elevator.addDestinationFloor(4);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testCase7() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(7);
        elevator.addDestinationFloor(6);
        int cost = 8 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testCase8() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(7);
        elevator.addDestinationFloor(2);
        int cost = 8 * ElevatorController.FLOOR_PASS_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }
}
