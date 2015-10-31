package ie.ait.touristapp.user;

/**
 * Created by ethomev on 10/21/15.
 */
public enum Gender {
    MALE(0), FEMALE(1);

    private int value;

    Gender(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
