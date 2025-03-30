package com.nexusBlog.NexusBlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.repository.PostRepository;
import com.iff.NexusBlog.repository.UserRepository;
import com.iff.NexusBlog.service.PostService;
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
        user.setName("Lucas");

        // Permite que os mocks sejam configurados sem gerar erros de "unnecessary stubbing"
        Mockito.lenient().when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.lenient().when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    public void testGetById_UserExists() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getById(1L);

        assertNotNull(foundUser);
        assertEquals("Lucas", foundUser.getName());
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
        assertEquals("Lucas", savedUser.getName());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        userService.delete(userId);

        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
    }
}
