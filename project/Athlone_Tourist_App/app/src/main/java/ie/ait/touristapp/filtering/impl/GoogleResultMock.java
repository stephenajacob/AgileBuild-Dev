package ie.ait.touristapp.filtering.impl;

import ie.ait.touristapp.rating.ExperienceType;

/**
 * Created by ethomev on 11/15/15.
 */
public class GoogleResultMock {

    private ExperienceType experienceType;
    private String name;

    public GoogleResultMock(ExperienceType experienceType, String name) {
        this.experienceType = experienceType;
        this.name = name;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
