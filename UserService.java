package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.UserRequest;
import com.smarthospital.platform.dto.response.UserResponse;

import java.util.List;

/**
 * Service contract for User account management.
 */
public interface UserService {

    /** Create a new user account. Throws {@code DuplicateResourceException} if username or email already exists. */
    UserResponse createUser(UserRequest request);

    /** Retrieve a user by ID. Throws {@code ResourceNotFoundException} if not found. */
    UserResponse getUserById(Long id);

    /** Retrieve a user by username. Throws {@code ResourceNotFoundException} if not found. */
    UserResponse getUserByUsername(String username);

    /** List all users. */
    List<UserResponse> getAllUsers();

    /** Update an existing user. Supports partial (PATCH) semantics — null fields are ignored. */
    UserResponse updateUser(Long id, UserRequest request);

    /** Permanently delete a user by ID. Throws {@code ResourceNotFoundException} if not found. */
    void deleteUser(Long id);
}
