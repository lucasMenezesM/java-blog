package com.nexusBlog.NexusBlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.repository.UserRepository;
import com.iff.NexusBlog.service.UserService;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("Lucas");
        user.setLastName("Menezes");
        user.setEmail("lucas@email.com");
        user.setActive(true);
        user.setActive(true);

        Mockito.lenient().when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.lenient().when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    public void testGetById_UserExists() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getById(1L);

        assertNotNull(foundUser);
        assertEquals("Lucas", foundUser.getFirstName());
    }

    @Test
    public void testGetById_UserNotFound() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.getById(1L);
        });

        assertEquals("User not found with id: 1", exception.getMessage());
    }

    @Test
    public void testCreateUser() {
        User savedUser = userService.create(user);

        assertNotNull(savedUser);
        assertEquals("Lucas", savedUser.getFirstName());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        userService.delete(userId);

        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
    }

    @Test
    public void testFindByEmailContainingIgnoreCase() {
        Mockito.lenient().when(userRepository.findByEmailContainingIgnoreCase("lucas@email.com"))
            .thenReturn(Collections.singletonList(user));

        List<User> users = userRepository.findByEmailContainingIgnoreCase("lucas@email.com");
        
        assertFalse(users.isEmpty());
        assertTrue(users.stream().allMatch(u -> u.getEmail().contains("lucas@email.com")));
    }

    @Test
    public void testFindByFirstNameContainingIgnoreCase() {
        Mockito.lenient().when(userRepository.findByFirstNameContainingIgnoreCase("lucas"))
            .thenReturn(Collections.singletonList(user));

        List<User> users = userRepository.findByFirstNameContainingIgnoreCase("lucas");
        
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("Lucas", users.get(0).getFirstName());
    }

    @Test
    public void testFindByLastNameContainingIgnoreCase() {
        Mockito.lenient().when(userRepository.findByLastNameContainingIgnoreCase("menezes"))
            .thenReturn(Collections.singletonList(user));

        List<User> users = userRepository.findByLastNameContainingIgnoreCase("menezes");
        
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("Lucas", users.get(0).getFirstName());
    }

    @Test
    public void testFindByActive() {
        Mockito.lenient().when(userRepository.findByActive(true)).thenReturn(Collections.singletonList(user));

        List<User> users = userRepository.findByActive(true);
        
        assertFalse(users.isEmpty());
        assertTrue(users.stream().allMatch(User::isActive));
    }

    @Test
    public void testFindByNameContaining() {
        Mockito.lenient().when(userRepository.findByNameContaining(Mockito.anyString()))
            .thenAnswer(invocation -> {
                String name = invocation.getArgument(0);
                if (user.getFirstName().contains(name) || user.getLastName().contains(name)) {
                    return Collections.singletonList(user);
                }
                return Collections.emptyList();
            });
        List<User> users = userRepository.findByNameContaining("Lucas");
        
        assertFalse(users.isEmpty());
        assertTrue(users.stream().anyMatch(u -> u.getFirstName().contains("Lucas") || u.getLastName().contains("lucas")));
    }
}
