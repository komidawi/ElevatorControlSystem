import elevator.elevatorCalculator.singleElevatorTests.passengerIsOnCourse.ElevatorMovingDownTests;
import elevator.elevatorCalculator.singleElevatorTests.passengerIsOnCourse.ElevatorMovingUpTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ElevatorMovingUpTests.class,
        ElevatorMovingDownTests.class
})

public class TestSuite {
}
