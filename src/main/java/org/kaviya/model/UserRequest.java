package org.kaviya.model;

public class UserRequest implements Comparable<UserRequest> {
    private int sourceFloor;
    private int destinationFloor;
    private boolean goingUp;

    public UserRequest(int sourceFloor, int destinationFloor) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.goingUp = destinationFloor > sourceFloor;
    }

    // Getters and setters
    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    // Compare requests based on source floor for prioritization
    @Override
    public int compareTo(UserRequest other) {
        return Integer.compare(this.sourceFloor, other.sourceFloor);
    }
}

