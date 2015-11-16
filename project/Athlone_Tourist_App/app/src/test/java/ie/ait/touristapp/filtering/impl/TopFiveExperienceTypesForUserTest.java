package ie.ait.touristapp.filtering.impl;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import ie.ait.touristapp.db.DatabaseHelper;
import ie.ait.touristapp.rating.ExperienceType;
import ie.ait.touristapp.rating.Rating;
import ie.ait.touristapp.rating.RatingBuilder;
import ie.ait.touristapp.user.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TopFiveExperienceTypesForUserTest {

    private TopFiveExperienceTypesForUser filtering;
    private RatingBuilder builder;
    private DatabaseHelper dbHelper;
    private User user;

    @Before
    public void setup(){
        dbHelper = mock(DatabaseHelper.class);
        user = mock(User.class);
        filtering = new TopFiveExperienceTypesForUser(dbHelper, user);
        builder = new RatingBuilder();
    }

    private List<Rating> generateRatingsList(ExperienceType experienceType) {
        List<Rating> ratings = new ArrayList<>();
        for(int i=1;i<=5;i++){
            Rating rating = builder.setRating(i).setExperience("Blah").setExperienceType(experienceType).setUsername("Tom").build();
            ratings.add(rating);
        }
        return ratings;
    }

    @Test
    public void testComputeAverageRating(){
        assertEquals(3, filtering.computeAverageRating(generateRatingsList(ExperienceType.B_AND_B)));
    }

    @Test
    public void testComputeAverRatingEmptyList(){
        assertEquals(0, filtering.computeAverageRating(Collections.<Rating>emptyList()));
    }

    @Test
    public void testGetSetOfExperienceTypes(){
        List<GoogleResultMock> experiences = buildGoogleResultMocks();
        assertEquals(1, filtering.getSetOfExperienceTypes(experiences).size());
    }

    @NonNull
    private List<GoogleResultMock> buildGoogleResultMocks() {
        List<GoogleResultMock> experiences = new ArrayList<>();
        for(int i =0; i<5;i++){
            GoogleResultMock mock = new GoogleResultMock(ExperienceType.B_AND_B, "blah");
            experiences.add(mock);;
        }
        return experiences;
    }

    @Test
    public void testGetExperienceTypesAverageRatingForUser(){
        Set<ExperienceType> types = new TreeSet<>();
        types.add(ExperienceType.B_AND_B);
        types.add(ExperienceType.BAR);
        when(user.getUsername()).thenReturn("tom");
        when(dbHelper.getExperienceTypeRatingsForThisUser(ExperienceType.B_AND_B, "tom")).thenReturn(generateRatingsList(ExperienceType.B_AND_B));
        when(dbHelper.getExperienceTypeRatingsForThisUser(ExperienceType.BAR, "tom")).thenReturn(generateRatingsList(ExperienceType.BAR));
        Map<ExperienceType, Integer> results = filtering.getExperienceTypesAverageRatingForUser(types);
        assertEquals(3, results.get(ExperienceType.B_AND_B).intValue());
        assertEquals(3, results.get(ExperienceType.BAR).intValue());
    }

    @Test
    public void testGetTopFiveExperienceTypes(){
        Map<ExperienceType, Integer> averageRatings = new HashMap<>();
        averageRatings.put(ExperienceType.B_AND_B, 3);
        averageRatings.put(ExperienceType.BAR, 10);
        averageRatings.put(ExperienceType.CHINESE, 7);
        averageRatings.put(ExperienceType.HOSTEL, 35);
        averageRatings.put(ExperienceType.HOTEL, 3);
        averageRatings.put(ExperienceType.INDIAN, 90);
        averageRatings.put(ExperienceType.IRISH, 56);
        averageRatings.put(ExperienceType.ITALIAN, 3);
        averageRatings.put(ExperienceType.LEBANESE, 7);
        averageRatings.put(ExperienceType.SELF_CATERING, 6);
        Set<ExperienceType> top5 = filtering.getTopFiveExperienceTypes(averageRatings);
        assertEquals(5, top5.size());
        assertTrue(top5.contains(ExperienceType.INDIAN));
        assertFalse(top5.contains(ExperienceType.B_AND_B));

    }
}