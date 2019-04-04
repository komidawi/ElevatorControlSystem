# Elevator Control System

Elevator Control System provides functionality to handle elevator movement efficiently. It calculates a minimum cost of travel from pickup to destination floor and then sends an appropriate elevator. Main advantage of this destination dispatch system over trivial FCFS scheduling is an ability to take passenger on-the-fly without unnecessary waiting untill all remaining destinations are reached. It also cares of cases when current and coming passengers have common pickup/destination floors and groups them.


Note: Project is still in developement phase.

## Usage

### Installation

Just simply download/clone the repository.

### Running

Make sure you have JRE (Java Runtime Environment) installed. You can download it e.g. from [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html). Then open `src\main\java\` directory in terminal and type

```
javac ElevatorApp.java
```

to compile required classes. Then run application by typing
```
java ElevatorApp
```

You can also just simply open project in any IDE e.g. IntelliJ IDEA and then run ElevatorApp.java.

### Function calls

Interface provides four functions:
* `showStatus` shows state of all elevators: ID, current floor, destinations and direction
* `createPickupRequest` allows to simulate a passenger calling for an elevator
* `addDestinationFloor` adds another destination point to the specified elevator
* `nextStep` simulates time flow and elevators movement. Elevators move only when this function is called.

## Areas of improvement

As project is in early phase there are some things to be done:

* **[crucial]** handle a case when passenger is not on elevator's course
* improve `nextStep` to simulate time-flow realistically, because now every activity lasts the same duration no matter of real time consumption
* when two elevators have the same cost prefer the idle one
* tidy `ElevatorApp.java` 
* some testing things:
	* write more sophisticated test cases 
	* write a fuzzer
	* provide high code coverage
* probably some other minor improvements
* improve calculation to count amount of passengers into travel cost
* GUI (in the future)