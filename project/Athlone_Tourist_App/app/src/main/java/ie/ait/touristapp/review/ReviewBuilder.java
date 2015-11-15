package ie.ait.touristapp.review;

/**
 * Created by ethomev on 10/24/15.
 */
public class ReviewBuilder {
    private String title;
    private String body;
    private String username;
    private int rating;
    private String experience;

    public ReviewBuilder setExperience(String experience) {
        this.experience = experience;
        return this;
    }

    public ReviewBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ReviewBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public ReviewBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public ReviewBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Review build(){
        return new Review(title,body,username, rating, experience);
    }
}
