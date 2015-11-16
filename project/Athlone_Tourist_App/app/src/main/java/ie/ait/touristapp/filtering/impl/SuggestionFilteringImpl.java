package ie.ait.touristapp.filtering.impl;

import java.util.Arrays;
import java.util.List;

import ie.ait.touristapp.filtering.api.Filter;
import ie.ait.touristapp.filtering.api.SuggestionFiltering;

public class SuggestionFilteringImpl implements SuggestionFiltering {

    private List<Filter> filters;

    public SuggestionFilteringImpl(Filter... filters){
        this.filters = Arrays.asList(filters);
    }

    @Override
    public List<GoogleResultMock> filterSuggestions(List<GoogleResultMock> googleResults) {
        for(Filter filter: filters){
            googleResults = filter.filter(googleResults);
        }
        return googleResults;
    }
}