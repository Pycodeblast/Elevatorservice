package org.kaviya.repository;
import org.kaviya.model.Elevator;

import java.util.List;

public interface ElevatorRepository {
    Elevator getElevatorById(int id);
    List<Elevator> getAllElevators();
    void addElevator(Elevator elevator);
    void updateElevator(Elevator elevator);
}

