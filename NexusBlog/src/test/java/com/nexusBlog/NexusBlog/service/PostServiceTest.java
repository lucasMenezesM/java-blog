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
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @InjectMocks
    private UserService userService;

    private User user;
    private Post post;

    @BeforeEach
    void setUp() {
      user = new User();
      user.setId(1L);
      user.setName("Lucas");

      post = new Post(user, null, "First Post", "This is the first post for test");
      post.setId(1L);

      // Permite que os mocks sejam configurados sem gerar erros de "unnecessary stubbing"
      Mockito.lenient().when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
      Mockito.lenient().when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
      Mockito.lenient().when(postRepository.findById(1L)).thenReturn(Optional.of(post));
    }

    @Test
    public void testGetById_PostExists() {
      Mockito.when(postRepository.findById(1L)).thenReturn(Optional.of(post));

      Post foundPost = postService.getById(1L);

      assertNotNull(foundPost);
      assertEquals("First Post", foundPost.getTitle());
      assertEquals("This is the first post for test", foundPost.getBody());
    }

    @Test
    public void testGetById_PostNotFound() {
      Mockito.when(postRepository.findById(1L)).thenReturn(Optional.empty());

      Exception exception = assertThrows(RuntimeException.class, () -> {
          postService.getById(1L);
      });

      assertEquals("Post not found with id: 1", exception.getMessage());
    }

    @Test
    public void testCreatePost() {
      Post savedPost = postService.create(post);

      assertNotNull(savedPost);
      assertEquals("First Post", savedPost.getTitle());
    }

    @Test
    public void testDeletePost() {
      Long postId = 1L;
      postService.delete(postId);

      // 🔍 Verificando se o método correto foi chamado
      Mockito.verify(postRepository, Mockito.times(1)).delete(post);
    }
}
