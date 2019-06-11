import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleSingleElevatorTests {

    @Test
    public void testTravelCost_StartFloor_2_AddDestination_1_Pickup_3_to_1() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(2);
        elevator.addDestinationFloor(1);
        int cost = 5 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 1)));
    }

    @Test
    public void testTravelCost_StartFloor__AddDestination_5_Pickup_3_to_1() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(3);
        elevator.addDestinationFloor(5);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 1)));
    }

    @Test
    public void testTravelCost_StartFloor_1_AddDestination_7_Pickup_3_to_1() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(1);
        elevator.addDestinationFloor(7);
        int cost = 12 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 1)));
    }

    @Test
    public void testTravelCost_StartFloor_0_AddDestination_1_Pickup_2_to_5() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(1);
        int cost = 5 * ElevatorController.FLOOR_PASS_COST + 2 * ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testTravelCost_StartFloor_5_AddDestination_7_Pickup_2_to_5() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(5);
        elevator.addDestinationFloor(7);
        int cost = 10 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testTravelCost_StartFloor_3_AddDestination_4_Pickup_2_to_5() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(3);
        elevator.addDestinationFloor(4);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testTravelCost_StartFloor_7_AddDestination_6_Pickup_2_to_5() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(7);
        elevator.addDestinationFloor(6);
        int cost = 8 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testTravelCost_StartFloor_7_AddDestination_2_Pickup_2_to_5() {
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(7);
        elevator.addDestinationFloor(2);
        int cost = 8 * ElevatorController.FLOOR_PASS_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }
}
