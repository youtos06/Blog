package so.tribe.blog.model;

public class PostComparableElement implements Comparable<PostComparableElement> {
    private Integer id;
    private Double property;

    public PostComparableElement(Integer id, Double property) {
        this.id = id;
        this.property = property;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getProperty() {
        return property;
    }

    public void setProperty(Double property) {
        this.property = property;
    }

    @Override
    public int compareTo(PostComparableElement postElement) {
        return this.property.compareTo(postElement.getProperty());
    }
}
