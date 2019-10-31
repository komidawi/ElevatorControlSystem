package elevator.elevatorCalculator.singleElevatorTests.passengerIsOnCourse;

import elevator.Elevator;
import elevator.PickupRequest;
import org.junit.Before;
import org.junit.Test;

import static elevator.ElevatorCalculator.FLOOR_PASS_COST;
import static elevator.ElevatorCalculator.STOP_COST;
import static elevator.ElevatorCalculator.calculateTravelCost;
import static org.junit.Assert.assertEquals;

public class ElevatorMovingDownTests {
    private Elevator elevator;

    @Before
    public void prepareSingleElevatorMovingDown() {
        elevator = new Elevator(0);
        elevator.setCurrentFloor(4);
        elevator.addMultipleDestinationFloors(new int[] {2, 0});
    }

    @Test
    public void testTravelCost_PickupNotInDestinations_TargetNotInDestinations() {
        long cost = 3 * FLOOR_PASS_COST + 3 * STOP_COST;
        assertEquals(cost, calculateTravelCost(elevator, new PickupRequest(3, 1)));
    }

    @Test
    public void testTravelCost_PickupNotInDestinations_TargetInDestinations() {
        long cost = 4 * FLOOR_PASS_COST + 3 * STOP_COST;
        assertEquals(cost, calculateTravelCost(elevator, new PickupRequest(3, 0)));
    }

    @Test
    public void testTravelCost_PickupInDestinations_TargetNotInDestinations() {
        long cost = 3 * FLOOR_PASS_COST + 2 * STOP_COST;
        assertEquals(cost, calculateTravelCost(elevator, new PickupRequest(2, 1)));
    }

    @Test
    public void testTravelCost_PickupInDestinations_TargetInDestinations() {
        long cost = 4 * FLOOR_PASS_COST + 2 * STOP_COST;
        assertEquals(cost, calculateTravelCost(elevator, new PickupRequest(2, 0)));
    }
}