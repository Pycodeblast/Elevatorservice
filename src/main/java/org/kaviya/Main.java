package org.kaviya;

import org.kaviya.model.Elevator;
import org.kaviya.model.UserRequest;
import org.kaviya.repository.InMemoryElevatorRepository;
import org.kaviya.repository.InMemoryUserRequestRepository;
import org.kaviya.services.ElevatorService;
import org.kaviya.services.UserRequestService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories for elevators and user requests
        InMemoryElevatorRepository elevatorRepository = new InMemoryElevatorRepository();
        InMemoryUserRequestRepository userRequestRepository = new InMemoryUserRequestRepository();

        // Initialize services with the repositories
        ElevatorService elevatorService = new ElevatorService(elevatorRepository, userRequestRepository);
        UserRequestService userRequestService = new UserRequestService(userRequestRepository);

        // Add elevators to the system
        Elevator elevator1 = new Elevator(1, 0, 5);  // Elevator 1, starting at floor 0, capacity 5
        Elevator elevator2 = new Elevator(2, 10, 4); // Elevator 2, starting at floor 10, capacity 4
        Elevator elevator3 = new Elevator(3, 5, 6);  // Elevator 3, starting at floor 5, capacity 6

        elevatorService.addElevator(elevator1);
        elevatorService.addElevator(elevator2);
        elevatorService.addElevator(elevator3);

        // Simulate user requests for elevators from different floors
        UserRequest request1 = new UserRequest(3, 7);  // User at floor 3 requests to go to floor 7
        UserRequest request2 = new UserRequest(8, 2);  // User at floor 8 requests to go to floor 2
        UserRequest request3 = new UserRequest(1, 10); // User at floor 1 requests to go to floor 10
        UserRequest request4 = new UserRequest(4, 6);  // User at floor 4 requests to go to floor 6

        // Add user requests to the system
        userRequestService.addRequest(request1);
        userRequestService.addRequest(request2);
        userRequestService.addRequest(request3);
        userRequestService.addRequest(request4);

        // Process requests with multi-threading to simulate concurrency
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Each request is processed by a separate thread
        executor.execute(() -> {
            elevatorService.processRequests();
            System.out.println("Elevator 1 finished processing requests.");
        });

        executor.execute(() -> {
            elevatorService.processRequests();
            System.out.println("Elevator 2 finished processing requests.");
        });

        executor.execute(() -> {
            elevatorService.processRequests();
            System.out.println("Elevator 3 finished processing requests.");
        });

        // Shutdown the executor
        executor.shutdown();

        // Display the final status of the elevators after processing the requests
        System.out.println("\nFinal status of all elevators:");
        for (Elevator elevator : elevatorService.getAllElevators()) {
            System.out.println("Elevator ID: " + elevator.getId() +
                    ", Current Floor: " + elevator.getCurrentFloor() +
                    ", Requests Served: " + elevator.getRequestsHandled());
        }

        // Check capacity and log
        for (Elevator elevator : elevatorService.getAllElevators()) {
            if (elevator.getCurrentLoad() > elevator.getCapacity()) {
                System.out.println("Elevator " + elevator.getId() + " exceeded capacity!");
            } else {
                System.out.println("Elevator " + elevator.getId() + " respected capacity limits.");
            }
        }

        // Log and optimize based on proximity and direction (newly added)
        for (UserRequest request : userRequestService.getAllRequests()) {
            Elevator closestElevator = elevatorService.findClosestElevator(request.getSourceFloor());
            if (closestElevator != null) {
                System.out.println("Assigning Elevator " + closestElevator.getId() +
                        " to handle request from floor " + request.getSourceFloor() +
                        " to floor " + request.getDestinationFloor());
            } else {
                System.out.println("No suitable elevator available for request from floor " +
                        request.getSourceFloor() + " to floor " + request.getDestinationFloor());
            }
        }

        // Optionally remove requests after they are processed
        userRequestService.removeRequest(request1);
        userRequestService.removeRequest(request2);
        userRequestService.removeRequest(request3);
        userRequestService.removeRequest(request4);

        System.out.println("All requests processed.");
    }
}
