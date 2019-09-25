package elevator.elevatorCalculator.singleElevatorTests.passengerIsOnCourse;

import elevator.Elevator;
import elevator.PickupRequest;
import org.junit.Before;
import org.junit.Test;

import static elevator.ElevatorCalculator.FLOOR_PASS_COST;
import static elevator.ElevatorCalculator.STOP_COST;
import static elevator.ElevatorCalculator.calculateTravelCost;
import static org.junit.Assert.assertEquals;

public class ElevatorMovingUpTests {
    private Elevator elevator;

    @Before
    public void instantiateSingleElevator() {
        elevator = new Elevator(0);
    }

    @Test
    public void testTravelCost_PickupNotInDestinations_TargetNotInDestinations() {
        elevator.setCurrentFloor(0);
        elevator.addDestinationFloor(5);
        long cost = 4 * FLOOR_PASS_COST + 2 * STOP_COST;
        assertEquals(cost, calculateTravelCost(elevator, new PickupRequest(2, 4)));
    }

    @Test
    public void testTravelCost_PickupNotInDestinations_TargetInDestinations() {
        elevator.setCurrentFloor(1);
        elevator.addDestinationFloor(5);
        long cost = 4 * FLOOR_PASS_COST + 2 * STOP_COST;
        assertEquals(cost, calculateTravelCost(elevator, new PickupRequest(2, 5)));
    }

    @Test
    public void testTravelCost_PickupInDestinations_TargetNotInDestinations() {
        elevator.setCurrentFloor(2);
        elevator.addDestinationFloor(4);
        long cost = 4 * FLOOR_PASS_COST + 2 * STOP_COST;
        assertEquals(cost, calculateTravelCost(elevator, new PickupRequest(4, 6)));
    }

    @Test
    public void testTravelCost_PickupInDestinations_TargetInDestinations() {
        elevator.setCurrentFloor(0);
        elevator.addMultipleDestinationFloors(new int[]{2, 4, 6});
        long cost = 6 * FLOOR_PASS_COST + 3 * STOP_COST;
        assertEquals(cost, calculateTravelCost(elevator, new PickupRequest(2, 6)));
    }
}
