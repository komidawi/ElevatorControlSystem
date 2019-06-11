import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleElevatorTests {

    private Elevator elevator;

    @Before
    public void instantiateSingleElevatorWithIdZero() {
        elevator = new Elevator(0);
    }

    @Test
    public void testTravelCost_StartFloor_0_AddDestinations_1_4_Pickup_2_to_5() {
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(1);
        elevator.addDestinationFloor(4);
        int cost = 5 * ElevatorController.FLOOR_PASS_COST + 3 * ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(2, 5)));
    }

    @Test
    public void testTravelCost_StartFloor_0_AddDestinations_2_5_7_Pickup_3_to_9() {
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(2);
        elevator.addDestinationFloor(5);
        elevator.addDestinationFloor(7);
        int cost = 9 * ElevatorController.FLOOR_PASS_COST + 4 * ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 9)));
    }

    @Test
    public void testTravelCost_StartFloor_0_AddDestinations_2_Pickup_4_to_6() {
        elevator.setCurrentFloor(2);
        int cost = 4 * ElevatorController.FLOOR_PASS_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(4, 6)));
    }

    @Test
    public void testTravelCost_StartFloor_0_AddDestinations_3_4_6_8_Pickup_3_to_6() {
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(3);
        elevator.addDestinationFloor(4);
        elevator.addDestinationFloor(6);
        elevator.addDestinationFloor(8);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST + ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 6)));
    }

    @Test
    public void testTravelCost_StartFloor_0_AddDestinations_2_5_7_Pickup_4_to_6() {
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(2);
        elevator.addDestinationFloor(5);
        elevator.addDestinationFloor(7);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST + 4 * ElevatorController.STOP_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(4, 6)));
    }

    @Test
    public void testTravelCost_StartFloor_0_AddDestinations_3_6_7_Pickup_3_to_6() {
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(3);
        elevator.addDestinationFloor(6);
        elevator.addDestinationFloor(7);
        int cost = 6 * ElevatorController.FLOOR_PASS_COST;
        assertEquals(cost, elevator.calculateCost(new PickupRequest(3, 6)));
    }
}
