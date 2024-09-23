package org.kaviya.repository;

import org.kaviya.model.UserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryUserRequestRepository implements UserRequestRepository {
    private final ConcurrentLinkedQueue<UserRequest> requestQueue;

    public InMemoryUserRequestRepository() {
        this.requestQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void addRequest(UserRequest request) {
        requestQueue.offer(request); // Add a user request to the queue
    }

    @Override
    public List<UserRequest> getAllRequests() {
        return new ArrayList<>(requestQueue); // Return all requests as a list
    }

    @Override
    public void removeRequest(UserRequest request) {
        requestQueue.remove(request); // Remove a specific user request
    }
}


