package so.tribe.blog.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import so.tribe.blog.configuration.ApiPath;
import so.tribe.blog.model.Post;
import so.tribe.blog.model.PostsList;
import so.tribe.blog.service.PostService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void getBadRequestForPostsNoTag() throws Exception{
        mockMvc.perform(get(ApiPath.LEVEL+ApiPath.POSTS))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getRequestForPostsWithTag() throws Exception{
        when(postService.consumePostListFromApi("science", "id","asc")).thenReturn(getPostList("science"));
        mockMvc.perform(get(ApiPath.LEVEL+ApiPath.POSTS)
                .param("tags", "science"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['posts'][0]['id']").value(4));
    }

    @Test
    public void getBadRequestForPostsWithWrongSortBy() throws Exception{
        mockMvc.perform(get(ApiPath.LEVEL+ApiPath.POSTS)
                .param("tags", "science")
                .param("sortBy", "asc"))
                .andExpect(status().isBadRequest());
    }


    public PostsList getPostList(String tag){
        Post post1 = new Post();
        post1.setAuthor("Elisha Friedman");
        post1.setAuthorId(8);
        post1.setId(4);
        post1.setLikes(728);
        post1.setPopularity(0.88);
        post1.setReads(19645);
        String[] tags = new String[]{"science","tech"};
        post1.setTags(Arrays.asList(tags));

        Post post2 = new Post();
        post2.setAuthor("Elisha Friedman");
        post2.setAuthorId(8);
        post2.setId(4);
        post2.setLikes(728);
        post2.setPopularity(0.88);
        post2.setReads(19645);
        String[] tags2 = new String[]{"science"};
        post2.setTags(Arrays.asList(tags));
        PostsList postsList = new PostsList();

        if (tag.equals("science")){
            postsList.addPost(post1);
            postsList.addPost(post2);
        }else{
            postsList.addPost(post1);
        }

        return postsList;
    }
}
