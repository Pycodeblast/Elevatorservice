package org.kaviya.repository;

import org.kaviya.model.Elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryElevatorRepository implements ElevatorRepository {
    private final ConcurrentHashMap<Integer, Elevator> elevators;

    public InMemoryElevatorRepository() {
        this.elevators = new ConcurrentHashMap<>();
    }

    @Override
    public Elevator getElevatorById(int id) {
        return elevators.get(id); // Get elevator by ID
    }

    @Override
    public List<Elevator> getAllElevators() {
        return new ArrayList<>(elevators.values()); // Return all elevators as a list
    }

    @Override
    public void addElevator(Elevator elevator) {
        elevators.put(elevator.getId(), elevator); // Add a new elevator
    }

    @Override
    public void updateElevator(Elevator elevator) {
        elevators.replace(elevator.getId(), elevator); // Update an existing elevator
    }
}

