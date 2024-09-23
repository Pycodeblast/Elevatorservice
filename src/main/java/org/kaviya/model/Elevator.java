package org.kaviya.model;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Elevator {
    private int id;
    private AtomicInteger currentFloor;
    private AtomicInteger capacity;
    private AtomicInteger currentLoad;
    private ConcurrentLinkedQueue<UserRequest> requestQueue; // Thread-safe queue for user requests
    private ConcurrentLinkedQueue<String> handledRequests; // For logging handled requests

    public Elevator(int id, int capacity, int startingFloor) {
        this.id = id;
        this.currentFloor = new AtomicInteger(startingFloor);
        this.capacity = new AtomicInteger(capacity);
        this.currentLoad = new AtomicInteger(0);
        this.requestQueue = new ConcurrentLinkedQueue<>();
        this.handledRequests = new ConcurrentLinkedQueue<>();
    }

    // Getters and setters...
    // Existing methods...

    public boolean loadPassenger(int passengers) {
        if (currentLoad.get() + passengers <= capacity.get()) {
            this.currentLoad.addAndGet(passengers);
            return true; // Successfully loaded
        }
        System.out.println("Elevator " + id + ": Cannot load " + passengers + " passengers. Capacity exceeded.");
        return false; // Loading failed
    }

    public void handleRequest(UserRequest request) {
        // Load passengers and process request
        if (loadPassenger(1)) { // Assuming each request corresponds to 1 passenger for simplicity
            handledRequests.add("From floor " + request.getSourceFloor() + " to floor " + request.getDestinationFloor());
        }
    }

    public String getRequestsHandled() {
        if (handledRequests.isEmpty()) {
            return "No requests handled yet.";
        }
        return handledRequests.stream().collect(Collectors.joining(", "));
    }

    // Method to process requests (prioritization logic can be added here)
    public void processRequests() {
        while (!requestQueue.isEmpty()) {
            UserRequest request = requestQueue.poll();
            if (request != null) {
                System.out.println("Elevator " + id + ": Processing request from floor " + request.getSourceFloor() +
                        " to floor " + request.getDestinationFloor());
                handleRequest(request);
            }
        }
    }

    public int getCurrentFloor() {
        return currentFloor.get(); // Returns the current floor using the AtomicInteger
    }

    public int getId() {
        return id; // Returns the unique ID of the elevator
    }

    public int getCurrentLoad() {
        return currentLoad.get(); // Returns the current number of passengers in the elevator
    }

    public int getCapacity() {
        return capacity.get(); // Returns the maximum capacity of the elevator
    }

}
