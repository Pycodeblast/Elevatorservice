package org.kaviya.services;


import org.kaviya.model.Elevator;
import org.kaviya.model.UserRequest;
import org.kaviya.repository.ElevatorRepository;
import org.kaviya.repository.UserRequestRepository;


import java.util.List;


public class ElevatorService {
    private final ElevatorRepository elevatorRepository;
    private final UserRequestRepository userRequestRepository;


    public ElevatorService(ElevatorRepository elevatorRepository, UserRequestRepository userRequestRepository) {
        this.elevatorRepository = elevatorRepository;
        this.userRequestRepository = userRequestRepository;
    }


    public void addElevator(Elevator elevator) {
        elevatorRepository.addElevator(elevator);
    }


    public List<Elevator> getAllElevators() {
        return elevatorRepository.getAllElevators();
    }


    public void processRequests() {
        List<UserRequest> requests = userRequestRepository.getAllRequests();
        // Implement logic to process requests here (e.g., assign to elevators)
        for (UserRequest request : requests) {
            // Placeholder for request processing logic
            System.out.println("Processing request from floor " + request.getSourceFloor() +
                    " to floor " + request.getDestinationFloor());
        }
    }

    public Elevator findClosestElevator(int sourceFloor) {
        Elevator closestElevator = null;
        int closestDistance = Integer.MAX_VALUE; // Initialize to maximum value

        for (Elevator elevator : elevatorRepository.getAllElevators()) {
            // Calculate the distance from the elevator's current floor to the source floor
            int distance = Math.abs(elevator.getCurrentFloor() - sourceFloor);

            // Check if this elevator is closer than the previously found closest elevator
            if (distance < closestDistance) {
                closestDistance = distance;
                closestElevator = elevator;
            }
        }

        return closestElevator; // Return the closest elevator found
    }

}

