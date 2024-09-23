package org.kaviya.services;

import org.kaviya.model.UserRequest;
import org.kaviya.repository.UserRequestRepository;


import java.util.List;


public class UserRequestService {
    private final UserRequestRepository userRequestRepository;

    public UserRequestService(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }


    public void addRequest(UserRequest request) {
        userRequestRepository.addRequest(request);
    }


    public List<UserRequest> getAllRequests() {
        return userRequestRepository.getAllRequests();
    }


    public void removeRequest(UserRequest request) {
        userRequestRepository.removeRequest(request);
    }
}




