import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCases {

    @Test
    public void testCase1() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(2);
        elevator.setDestinationFloor(1);
        int cost = 5 * ElevatorController.FLOOR_PASS_TIME + ElevatorController.STOP_TIME;
        assertEquals(cost, elevator.calculateCost(3, 1));
    }

    @Test
    public void testCase2() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(3);
        elevator.setDestinationFloor(5);
        int cost = 6 * ElevatorController.FLOOR_PASS_TIME + ElevatorController.STOP_TIME;
        assertEquals(cost, elevator.calculateCost(3, 1));
    }

    @Test
    public void testCase3() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(1);
        elevator.setDestinationFloor(7);
        int cost = 12 * ElevatorController.FLOOR_PASS_TIME + ElevatorController.STOP_TIME;
        assertEquals(cost, elevator.calculateCost(3, 1));
    }

    @Test
    public void testCase4() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.setDestinationFloor(1);
        int cost = 5 * ElevatorController.FLOOR_PASS_TIME + ElevatorController.STOP_TIME;
        assertEquals(cost, elevator.calculateCost(2, 5));
    }

    @Test
    public void testCase5() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(5);
        elevator.setDestinationFloor(7);
        int cost = 10 * ElevatorController.FLOOR_PASS_TIME + ElevatorController.STOP_TIME;
        assertEquals(cost, elevator.calculateCost(2, 5));
    }

    @Test
    public void testCase6() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(3);
        elevator.setDestinationFloor(4);
        int cost = 6 * ElevatorController.FLOOR_PASS_TIME + ElevatorController.STOP_TIME;
        assertEquals(cost, elevator.calculateCost(2, 5));
    }
}
