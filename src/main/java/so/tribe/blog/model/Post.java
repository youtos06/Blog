package so.tribe.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private int id;
    private String author;
    private int authorId;
    private int likes;
    private double popularity;
    private int reads;
    private List<String> tags;

    public Post() {
    }

    public double getByProperty(String property){
        if (property.equals("id")){
            return getId();
        }
        if (property.equals("reads")){
            return getReads();
        }
        if (property.equals("popularity")){
            return getPopularity();
        }
        if (property.equals("likes")){
            return getLikes();
        }
        return getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getReads() {
        return reads;
    }

    public void setReads(int reads) {
        this.reads = reads;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
