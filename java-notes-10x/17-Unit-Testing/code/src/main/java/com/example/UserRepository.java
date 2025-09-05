package com.example;

import java.util.Optional;

/**
 * An interface representing the contract for a user repository.
 * In a real application, the implementation of this interface might
 * talk to a database. In our tests, we will mock it.
 */
public interface UserRepository {

    /**
     * Finds a user by their ID.
     * @param userId The ID of the user to find.
     * @return An Optional containing the user if found, otherwise an empty Optional.
     */
    Optional<User> findById(String userId);

    /**
     * Saves a user.
     * @param user The user to save.
     */
    void save(User user);
}
