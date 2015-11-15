package ie.ait.touristapp.rating;

/**
 * Created by ethomev on 10/24/15.
 */
public class Rating {
    private String experience;
    private ExperienceType experienceType;
    private int rating;
    private String username;

    public Rating(String experience, int rating, String username, ExperienceType experienceType) {
        this.experience = experience;
        this.rating = rating;
        this.username = username;
        this.experienceType = experienceType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }
}
