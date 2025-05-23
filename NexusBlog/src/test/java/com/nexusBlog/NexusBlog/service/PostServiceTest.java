package com.nexusBlog.NexusBlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Mock
    private UserService userService;

    private User user;
    private Post post;

    @BeforeEach
    void setUp() {
      user = new User();
      user.setId(1L);
      user.setFirstName("Lucas");

      post = new Post(user, null, "First Post", "This is the first post for test");
      post.setId(1L);

      Mockito.lenient().when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
      Mockito.lenient().when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
      Mockito.lenient().when(postRepository.findById(1L)).thenReturn(Optional.of(post));
      Mockito.lenient().when(postRepository.findByTitleContainingIgnoreCase(Mockito.anyString()))
      .thenReturn(List.of(post));
      // Mockito.lenient().when(postRepository.findByUser_NameContainingIgnoreCase(Mockito.anyString()))
      // .thenReturn(List.of(post));
      
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
      Post savedPost = postService.create(post, 1L);

      assertNotNull(savedPost);
      assertEquals("First Post", savedPost.getTitle());
    }

    @Test
    public void testDeletePost() {
      Long postId = 1L;
      postService.delete(postId);

      // Verificando se o método correto foi chamado
      Mockito.verify(postRepository, Mockito.times(1)).delete(post);
    }

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase("first");

        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        assertTrue(posts.get(0).getTitle().toLowerCase().contains("first"));
    }

    // @Test
    // public void testFindByUser_NameContainingIgnoreCase() {
    //   List<Post> posts = postRepository.findByUser_NameContainingIgnoreCase("Lucas");

    //   assertNotNull(posts);
    //   assertFalse(posts.isEmpty());
    //   assertTrue(posts.get(0).getUser().getFirstName().toLowerCase().contains("lucas"));
    // }

    @Test
    public void testFindByContentContainingIgnoreCase() {
      Mockito.lenient().when(postRepository.findByBodyContainingIgnoreCase(Mockito.anyString()))
      .thenReturn(List.of(post));

      List<Post> posts = postRepository.findByBodyContainingIgnoreCase("first post");

      assertNotNull(posts);
      assertFalse(posts.isEmpty());
      assertTrue(posts.get(0).getBody().toLowerCase().contains("first post"));
    }

    @Test
    public void testSearchByKeyword() {
      Mockito.lenient().when(postRepository.searchByKeyword(Mockito.anyString()))
      .thenReturn(List.of(post));

      List<Post> posts = postRepository.searchByKeyword("first");

      assertNotNull(posts);
      assertFalse(posts.isEmpty());
      assertTrue(posts.get(0).getTitle().toLowerCase().contains("first") || posts.get(0).getBody().toLowerCase().contains("first"));
    }
}
