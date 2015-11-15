package ie.ait.touristapp.rating;

/**
 * Created by ethomev on 10/24/15.
 */
public class RatingBuilder {
    private String experience;
    private int rating;
    private String username;
    private ExperienceType experienceType;

    public RatingBuilder setExperience(String experience) {
        this.experience = experience;
        return this;
    }

    public RatingBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public RatingBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public RatingBuilder setExperienceType(ExperienceType experienceType){
        this.experienceType = experienceType;
        return this;
    }

    public Rating build(){
        return new Rating(experience,rating,username,experienceType);
    }
}
