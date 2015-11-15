package ie.ait.touristapp.rating;

/**
 * Created by ethomev on 11/15/15.
 */
public enum ExperienceType {
    HOTEL("hotel"),
    B_AND_B("b&b"),
    HOSTEL("hostel"),
    SELF_CATERING("selfCatering"),
    BAR("bar"),
    IRISH("irish"),
    INDIAN("indian"),
    CHINESE("chinese"),
    ITALIAN("italian"),
    LEBANESE("lebanese");

    private final String value;
    ExperienceType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
