package ie.ait.touristapp.user;

/**
 * Created by ethomev on 10/21/15.
 */
public class User {
    private final String username;
    private final String address;
    private final int age;
    private final String emailAddress;
    private final Gender gender;
    private final String name;
    private final String phoneNumber;

    public User(String username, String address, int age, String emailAddress, Gender gender, String name, String phoneNumber) {
        this.address = address;
        this.age = age;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
