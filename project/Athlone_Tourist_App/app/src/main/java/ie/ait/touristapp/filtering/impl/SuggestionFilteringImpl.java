package ie.ait.touristapp.filtering.impl;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import ie.ait.touristapp.db.DatabaseHelper;
import ie.ait.touristapp.filtering.api.SuggestionFiltering;
import ie.ait.touristapp.rating.ExperienceType;
import ie.ait.touristapp.rating.Rating;
import ie.ait.touristapp.user.User;

public class SuggestionFilteringImpl implements SuggestionFiltering {

    public static final int TOP_FIVE_EXPERIENCE_TYPE_LIMIT = 5;
    private DatabaseHelper dbHelper;
    private User user;

    public SuggestionFilteringImpl(DatabaseHelper dbHelper, User user){
        this.dbHelper = dbHelper;
        this.user = user;
    }

    public SuggestionFilteringImpl(){

    }

    @Override
    public List<GoogleResultMock> filterSuggestions(final List<GoogleResultMock> googleResults) {
        final Set<ExperienceType> experienceTypes = getSetOfExperienceTypes(googleResults);
        final Map<ExperienceType, Integer> experienceTypesAverageRating = getExperienceTypesAverageRatingForUser(experienceTypes);
        final Set<ExperienceType> topFiveExperienceTypes = getTopFiveExperienceTypes(experienceTypesAverageRating);
        List<GoogleResultMock> filteredResults = new ArrayList<>();
        for(final GoogleResultMock result: googleResults){
            if(topFiveExperienceTypes.contains(result.getExperienceType())) {
                filteredResults.add(result);
            }
        }
        return filteredResults;
    }

    @VisibleForTesting
    protected Set<ExperienceType> getTopFiveExperienceTypes(final Map<ExperienceType, Integer> experienceTypeAverageRatings) {
        final RatingValueComparator comparator = new RatingValueComparator(experienceTypeAverageRatings);
        final TreeMap<ExperienceType, Integer> sortedExperienceTypeAverageRatings = new TreeMap(comparator);
        sortedExperienceTypeAverageRatings.putAll(experienceTypeAverageRatings);
        final Set<ExperienceType> sortedExperiences = sortedExperienceTypeAverageRatings.keySet();
        Set<ExperienceType> topFiveExperienceTypes = new TreeSet<>();
        int count=0;
        for(ExperienceType experienceType: sortedExperiences){
            if(count== TOP_FIVE_EXPERIENCE_TYPE_LIMIT) {
                break;
            }
            topFiveExperienceTypes.add(experienceType);
            count++;
        }
        return topFiveExperienceTypes;
    }

    @NonNull
    @VisibleForTesting
    protected Map<ExperienceType, Integer> getExperienceTypesAverageRatingForUser(final Set<ExperienceType> experienceTypes) {
        Map<ExperienceType, Integer> experienceTypeAverageRatings = new HashMap<>();
        for(ExperienceType experienceType: experienceTypes){
            List<Rating> ratings = dbHelper.getExperienceTypeRatingsForThisUser(experienceType, user.getUsername());
            int averageRating = computeAverageRating(ratings);
            experienceTypeAverageRatings.put(experienceType, averageRating);
        }
        return experienceTypeAverageRatings;
    }

    @NonNull
    @VisibleForTesting
    protected Set<ExperienceType> getSetOfExperienceTypes(List<GoogleResultMock> googleResults) {
        Set<ExperienceType> experienceTypes = new TreeSet<>();
        for(GoogleResultMock result: googleResults){
            experienceTypes.add(result.getExperienceType());
        }
        return experienceTypes;
    }

    @VisibleForTesting
    protected int computeAverageRating(List<Rating> ratings) {
        int totalRating = 0;
        for(Rating rating: ratings){
            totalRating+=rating.getRating();
        }
        return ratings.size()==0 ? 0 : totalRating/ratings.size();
    }
}