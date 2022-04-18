package so.tribe.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostsList {
    List<Post> posts;

    public PostsList() {
        posts = new ArrayList<>();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post){
        posts.add(post);
    }

    public Post getPostById(int id){
        for (Post post : posts){
            if (post.getId() == id){
                return post;
            }
        }
        return null;
    }

    /**
     *
     * @param direction
     * @param sortByMap
     *
     * sort Our List Post according to Elements of HashMap value ( which is a couple id and property
     * can be "id","likes"... of Post
     */
    public void sortPosts(String direction, HashMap<Integer,PostComparableElement> sortByMap){
        List<Post> postsSorted = new ArrayList<>();
        List<PostComparableElement> postsElements = new ArrayList<>(sortByMap.values());
        if(direction.equals("desc")){
            postsElements.sort(Collections.reverseOrder());
        }else{
            Collections.sort(postsElements);
        }

        for (PostComparableElement postElement: postsElements){
            postsSorted.add(this.getPostById(postElement.getId()));
        }
        this.posts = postsSorted;
    }
}
