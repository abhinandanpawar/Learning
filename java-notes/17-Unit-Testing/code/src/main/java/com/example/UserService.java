package com.example;

import java.util.Optional;

/**
 * The service class containing business logic for users.
 * This is the "unit" we want to test.
 */
public class UserService {

    private final UserRepository userRepository;

    /**
     * The service takes its dependencies via the constructor.
     * This is called "constructor dependency injection" and makes the class easy to test.
     * @param userRepository The repository to use for data access.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets a user by their ID, throwing an exception if not found.
     * @param userId The ID of the user.
     * @return The User object.
     * @throws UserNotFoundException if no user with that ID exists.
     */
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    /**
     * Registers a new user, throwing an exception if a user with the same ID already exists.
     * @param userId The ID for the new user.
     * @param name The name for the new user.
     * @return The newly created User object.
     * @throws UserAlreadyExistsException if a user with that ID already exists.
     */
    public User registerUser(String userId, String name) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with ID " + userId + " already exists.");
        }
        User newUser = new User(userId, name);
        userRepository.save(newUser);
        return newUser;
    }
}

// Custom exception classes for our service
class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
