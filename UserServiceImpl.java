package com.smarthospital.platform.service.impl;

import com.smarthospital.platform.dto.request.UserRequest;
import com.smarthospital.platform.dto.response.UserResponse;
import com.smarthospital.platform.entity.User;
import com.smarthospital.platform.exception.DuplicateResourceException;
import com.smarthospital.platform.exception.ResourceNotFoundException;
import com.smarthospital.platform.mapper.UserMapper;
import com.smarthospital.platform.repository.UserRepository;
import com.smarthospital.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UserService}.
 *
 * <p>Business rules enforced:
 * <ul>
 *   <li>Username and email must be globally unique across all user accounts.</li>
 *   <li>On update, uniqueness is checked only when the field value actually changes.</li>
 *   <li>Password encoding is a responsibility of this layer
 *       (placeholder — integrate Spring Security PasswordEncoder when adding auth).</li>
 * </ul>
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper     userMapper;

    // -------------------------------------------------------------------------
    // Create
    // -------------------------------------------------------------------------
    @Override
    public UserResponse createUser(UserRequest request) {
        log.debug("Creating user with username='{}', email='{}'", request.getUsername(), request.getEmail());

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("User", "username", request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }

        User user = userMapper.toEntity(request);
        // TODO: encode password → user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = userRepository.save(user);
        log.info("User created with id={}", saved.getId());
        return userMapper.toResponse(saved);
    }

    // -------------------------------------------------------------------------
    // Read
    // -------------------------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        return userMapper.toResponse(findUserOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------------------------
    // Update
    // -------------------------------------------------------------------------
    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = findUserOrThrow(id);

        // Uniqueness checks — only validate if the field value is changing
        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())
                && userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("User", "username", request.getUsername());
        }
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }

        userMapper.updateEntity(request, user);
        // TODO: re-encode password if changed
        return userMapper.toResponse(userRepository.save(user));
    }

    // -------------------------------------------------------------------------
    // Delete
    // -------------------------------------------------------------------------
    @Override
    public void deleteUser(Long id) {
        findUserOrThrow(id);
        userRepository.deleteById(id);
        log.info("User deleted with id={}", id);
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------
    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
