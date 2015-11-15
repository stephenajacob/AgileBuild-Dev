package ie.ait.touristapp.review;

/**
 * Created by ethomev on 10/24/15.
 */
public class Review {
    private String title;
    private String body;
    private String username;
    private int rating;
    private String experience;

    public Review(String title, String body, String username, int rating, String experience) {
        this.title = title;
        this.body = body;
        this.username = username;
        this.rating = rating;
        this.experience = experience;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
