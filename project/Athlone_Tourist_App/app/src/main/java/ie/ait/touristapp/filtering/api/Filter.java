package ie.ait.touristapp.filtering.api;

import java.util.List;

import ie.ait.touristapp.filtering.impl.GoogleResultMock;

/**
 * Created by ethomev on 11/16/15.
 */
public interface Filter {
    List<GoogleResultMock> filter(List<GoogleResultMock> suggestionsToFilter);
}
