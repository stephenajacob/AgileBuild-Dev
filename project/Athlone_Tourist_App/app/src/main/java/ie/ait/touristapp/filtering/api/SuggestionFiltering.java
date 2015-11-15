package ie.ait.touristapp.filtering.api;

import java.util.List;

import ie.ait.touristapp.filtering.impl.GoogleResultMock;

/**
 * Created by ethomev on 11/15/15.
 */
public interface SuggestionFiltering {
    List<GoogleResultMock> filterSuggestions(List<GoogleResultMock> googleResults);
}
