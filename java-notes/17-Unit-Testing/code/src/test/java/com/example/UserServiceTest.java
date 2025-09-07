package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserService class.
 * This class demonstrates the use of JUnit 5 and Mockito.
 */
@ExtendWith(MockitoExtension.class) // Integrates Mockito with the JUnit 5 test lifecycle.
class UserServiceTest {

    // Create a mock implementation of the UserRepository.
    // Mockito will create a "fake" object that we can control.
    @Mock
    private UserRepository mockUserRepository;

    // Create an instance of the class we want to test.
    // Mockito will inject the mock objects created above into this instance.
    @InjectMocks
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        // This method runs before each test. It's a good place for common setup.
        sampleUser = new User("1", "John Doe");
    }

    @Test
    @DisplayName("should return user when found by ID")
    void getUserById_whenUserExists_shouldReturnUser() {
        // Arrange: Tell the mock what to do when its methods are called.
        when(mockUserRepository.findById("1")).thenReturn(Optional.of(sampleUser));

        // Act: Call the method we are testing.
        User foundUser = userService.getUserById("1");

        // Assert: Verify the result and the interactions with the mock.
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());

        // Verify that the findById method was called on the repository exactly once.
        verify(mockUserRepository, times(1)).findById("1");
    }

    @Test
    @DisplayName("should throw exception when user not found by ID")
    void getUserById_whenUserDoesNotExist_shouldThrowException() {
        // Arrange: Configure the mock to return an empty Optional.
        when(mockUserRepository.findById("2")).thenReturn(Optional.empty());

        // Act & Assert: Verify that the correct exception is thrown.
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById("2");
        });

        assertEquals("User not found with ID: 2", exception.getMessage());
    }

    @Test
    @DisplayName("should register user successfully when ID is not taken")
    void registerUser_whenIdIsNew_shouldSaveAndReturnUser() {
        // Arrange
        when(mockUserRepository.findById("2")).thenReturn(Optional.empty());

        // Act
        User newUser = userService.registerUser("2", "Jane Smith");

        // Assert
        assertNotNull(newUser);
        assertEquals("Jane Smith", newUser.getName());

        // Verify that the save method was called on the repository with the new user object.
        verify(mockUserRepository, times(1)).save(newUser);
    }

    @Test
    @DisplayName("should throw exception when registering with an existing ID")
    void registerUser_whenIdExists_shouldThrowException() {
        // Arrange
        when(mockUserRepository.findById("1")).thenReturn(Optional.of(sampleUser));

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.registerUser("1", "Another John");
        });

        // Verify that the save method was NOT called.
        verify(mockUserRepository, never()).save(any(User.class));
    }


    // --- Your Mission ---
    // The 'updateUserName' method was added to the UserService.
    // Your mission is to write the unit tests for it.

    @Test
    @DisplayName("should update user name when user exists")
    void updateUserName_whenUserExists_shouldUpdateAndReturnUser() {
        // Your Mission (Part 1): Test the "happy path".
        // Arrange:
        // 1. Tell the mock repository that when findById("1") is called,
        //    it should return the Optional-wrapped sampleUser.
        // Act:
        // 2. Call the userService.updateUserName method with user ID "1" and a new name "Johnathan Doe".
        // Assert:
        // 3. Assert that the returned user's name is "Johnathan Doe".
        // 4. Verify that the repository's save method was called exactly once
        //    with an argument that has the new name. (Hint: use an ArgumentCaptor).

    }

    @Test
    @DisplayName("should throw exception when updating a non-existent user")
    void updateUserName_whenUserDoesNotExist_shouldThrowException() {
        // Your Mission (Part 2): Test the "sad path".
        // Arrange:
        // 1. Tell the mock repository that when findById is called with any string,
        //    it should return an empty Optional.
        // Act & Assert:
        // 2. Assert that calling updateUserName throws a UserNotFoundException.

    }
}
