package ie.ait.touristapp.filtering.impl;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ie.ait.touristapp.db.DatabaseHelper;
import ie.ait.touristapp.filtering.api.SuggestionFiltering;
import ie.ait.touristapp.rating.ExperienceType;
import ie.ait.touristapp.rating.Rating;
import ie.ait.touristapp.user.User;

public class SuggestionFilteringImpl implements SuggestionFiltering {

    public static final int TOP_FIVE_EXPERIENCE_TYPE_LIMIT = 5;
    private final DatabaseHelper dbHelper;
    private final User user;

    public SuggestionFilteringImpl(DatabaseHelper dbHelper, User user){
        this.dbHelper = dbHelper;
        this.user = user;
    }

    @Override
    public List<GoogleResultMock> filterSuggestions(final List<GoogleResultMock> googleResults) {
        final Set<ExperienceType> experienceTypes = getSetOfExperienceTypes(googleResults);
        final Map<ExperienceType, Integer> experienceTypesAverageRating = getExperienceTypesAverageRatingForUser(experienceTypes);
        final Set<ExperienceType> topFiveExperienceTypes = getTopFiveExperienceTypes(experienceTypesAverageRating);
        List<GoogleResultMock> filteredResults = Collections.emptyList();
        for(final GoogleResultMock result: googleResults){
            if(topFiveExperienceTypes.contains(result.getExperienceType())) {
                filteredResults.add(result);
            }
        }
        return filteredResults;
    }

    private Set<ExperienceType> getTopFiveExperienceTypes(final Map<ExperienceType, Integer> experienceTypeAverageRatings) {
        final RatingValueComparator comparator = new RatingValueComparator(experienceTypeAverageRatings);
        final TreeMap<ExperienceType, Integer> sortedExperienceTypeAverageRatings = new TreeMap(comparator);
        final Set<ExperienceType> sortedExperiences = sortedExperienceTypeAverageRatings.keySet();
        Set<ExperienceType> topFiveExperienceTypes = Collections.emptySet();
        int count=0;
        for(ExperienceType experienceType: sortedExperiences){
            if(count== TOP_FIVE_EXPERIENCE_TYPE_LIMIT) {
                break;
            }
            topFiveExperienceTypes.add(experienceType);
        }
        return topFiveExperienceTypes;
    }

    @NonNull
    private Map<ExperienceType, Integer> getExperienceTypesAverageRatingForUser(final Set<ExperienceType> experienceTypes) {
        Map<ExperienceType, Integer> experienceTypeAverageRatings = Collections.emptyMap();
        for(ExperienceType experienceType: experienceTypes){
            List<Rating> ratings = dbHelper.getExperienceTypeRatingsForThisUser(experienceType, user.getUsername());
            int averageRating = computeAverageRating(ratings);
            experienceTypeAverageRatings.put(experienceType, averageRating);
        }
        return experienceTypeAverageRatings;
    }

    @NonNull
    private Set<ExperienceType> getSetOfExperienceTypes(List<GoogleResultMock> googleResults) {
        Set<ExperienceType> experienceTypes = Collections.emptySet();
        for(GoogleResultMock result: googleResults){
            experienceTypes.add(result.getExperienceType());
        }
        return experienceTypes;
    }

    private int computeAverageRating(List<Rating> ratings) {
        int totalRating = 0;
        for(Rating rating: ratings){
            totalRating+=rating.getRating();
        }
        return totalRating/ratings.size();
    }

    class RatingValueComparator implements Comparator<ExperienceType> {

        private final Map<ExperienceType, Integer> base;

        public RatingValueComparator(Map base){
            this.base = base;
        }

        @Override
        public int compare(ExperienceType lhs, ExperienceType rhs) {
            int result = 0;
            if(base.get(lhs)>=base.get(rhs)) {
                result = -1;
            }else {
                result = 1;
            }
            return result;
        }
    }
}