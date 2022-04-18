package so.tribe.blog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import so.tribe.blog.configuration.HatchWaysApi;
import so.tribe.blog.configuration.PropertyResolver;
import so.tribe.blog.model.Post;
import so.tribe.blog.model.PostComparableElement;
import so.tribe.blog.model.PostsList;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class PostService {
    @Autowired
    private RestConsumerService restConsumerService;
    @Autowired
    private PropertyResolver propertyResolver;

    private ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    /**
     *
     * @param tagsInString
     * @param sortBy
     * @param direction
     * We first retrieve Posts by Tag and in same time creating a hashMap that will be used to delete
     * doubles and map the Id and property (sortBy selected ) as a value
     * we later on sort the values of all posts  regrouped
     * and rebuild the posts for our element
     * @return sorted Posts by direction
     */
    @Cacheable(value="multipleTagsWithParams",key="#tagsInString.concat('-').concat(#sortBy).concat('-').concat(#direction)")
    public PostsList consumePostListFromApi(String tagsInString,String sortBy,String direction){
        String[] tags = Arrays.stream(tagsInString.trim().split(",")).map(String::trim).toArray(String[]::new);
        // for O(1) access to not have double;
        // in same time we can delete doubles and keep the second value as value to be sorted
        HashMap<Integer, PostComparableElement> mapOfPostId = new HashMap<>();
        PostsList results = new PostsList();
        for (String tag : tags){
            if(!tag.equals("")){
                PostsList postsOfTag = getPostListByTag(tag);
                for (Post post : postsOfTag.getPosts()) {
                    if (!mapOfPostId.containsKey(post.getId())){
                        PostComparableElement postComparableElement = new PostComparableElement(post.getId(),post.getByProperty(sortBy));
                        mapOfPostId.put(post.getId(),postComparableElement);
                        results.addPost(post);
                    }
                }
            }
        }
        results.sortPosts(direction,mapOfPostId);
        return results;
    }

    /**
     * @param tag
     * @return response Of HatchWays Api for Blog Posts
     */

    @Cacheable(value = "postsByTag",key = "#tag")
    private PostsList getPostListByTag(String tag){
        log.info("Calling Hatchways Api for Tag "+tag);
        HashMap<String,Object> params = new HashMap<>();
        HashMap<String,Object> pathVariables = new HashMap<>();
        params.put("tag",tag);
        String url = propertyResolver.getPropertyValue("hatchways.api") + HatchWaysApi.POSTS;
        Object object = restConsumerService.getSimpleObjectMethod(url,pathVariables,params);
        return mapper.convertValue(object,PostsList.class);
    }


}
