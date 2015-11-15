package ie.ait.touristapp.filtering.impl;

import java.util.Comparator;
import java.util.Map;

import ie.ait.touristapp.rating.ExperienceType;

/**
 * Created by ethomev on 11/15/15.
 */
public class RatingValueComparator implements Comparator<ExperienceType> {

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
