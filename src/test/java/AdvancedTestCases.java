import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdvancedTestCases {

    @Test
    public void testCase1() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(1);
        elevator.addDestinationFloor(4);
        int cost = 5 * ElevatorController.FLOOR_PASS_COST + 3 * ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testCase2() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(2);
        elevator.addDestinationFloor(5);
        elevator.addDestinationFloor(7);
        int cost = 9 * ElevatorController.FLOOR_PASS_COST + 4 * ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 9)));
    }

    @Test
    public void testCase3() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(2);
        int cost = 4 * ElevatorController.FLOOR_PASS_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(4, 6)));
    }

    @Test
    public void testCase4() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(3);
        elevator.addDestinationFloor(4);
        elevator.addDestinationFloor(6);
        elevator.addDestinationFloor(8);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 6)));
    }

    @Test
    public void testCase5() {

    }

    @Test
    public void testCase6() {
        // model test #1.1
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(2);
        elevator.addDestinationFloor(5);
        elevator.addDestinationFloor(7);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST + 4 * ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(4, 6)));
    }

    @Test
    public void testCase7() {
        // model test #1.2
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(3);
        elevator.addDestinationFloor(6);
        elevator.addDestinationFloor(7);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 6)));
    }
}
