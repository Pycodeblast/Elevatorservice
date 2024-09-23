package org.kaviya.repository;
import org.kaviya.model.UserRequest;

import java.util.List;

public interface UserRequestRepository {
    void addRequest(UserRequest request);
    List<UserRequest> getAllRequests();
    void removeRequest(UserRequest request);
}

