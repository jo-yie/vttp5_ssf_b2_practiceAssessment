package vttp5_ssf_b2_practiceAssessment.model;

public class Article {

    private String id; 
    private Integer published_on; 
    private String title; 
    private String url; 
    private String imageurl; 
    private String body; 
    private String tags; 
    private String categories;

    private Boolean save;
    // TODO remove save attribute

    // constructors
    public Article() {
        this.save = false;
    } 

    public Article(String id, Integer published_on, String title, String url, String imageurl, String body, String tags,
            String categories) {
        this.id = id;
        this.published_on = published_on;
        this.title = title;
        this.url = url;
        this.imageurl = imageurl;
        this.body = body;
        this.tags = tags;
        this.categories = categories;

        this.save = false;
    }

    // getters and setters 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPublished_on() {
        return published_on;
    }

    public void setPublished_on(Integer published_on) {
        this.published_on = published_on;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Boolean getSave() {
        return save;
    }

    public void setSave(Boolean save) {
        this.save = save;
    }

    // toString() method
    @Override
    public String toString() {
        return id + "," + published_on + "," + title + "," + url
                + "," + imageurl + "," + body + "," + tags + "," + categories;
    }
    
}
