package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.example.user.User;
import com.example.user.UserController;
import com.example.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    private List<User> userList;

    @BeforeEach
    void setUp() throws Exception {
        userList = new ArrayList<User>();

        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    void testGetAllUser() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userController.getAllUser();
        assertEquals(2, result.size());
    }

    @Test
    void testGetUserDetails() {
        String email = "john.doe@example.com";
        when(userRepository.findByEmail(email)).thenReturn(userList.get(0));
        User result = userController.getUserDetails(email);
        assertEquals("John Doe", result.getName());
        assertEquals(email, result.getEmail());
    }

    @Test
    void testAddNewUser() {
        User newUser = new User();
        newUser.setName("Bob Smith");
        newUser.setEmail("bob.smith@example.com");

        when(userRepository.save(any(User.class))).thenReturn(newUser);
        User result = userController.addNewUser(newUser);
        assertEquals("Bob Smith", result.getName());
        assertEquals("bob.smith@example.com", result.getEmail());
    }
}
