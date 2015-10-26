package ie.ait.touristapp.user;

/**
 * Created by ethomev on 10/21/15.
 */
public class User {
    private String username;
    private String address;
    private int age;
    private String emailAddress;
    private Gender gender;
    private String name;
    private String phoneNumber;

    public User(String username, String address, int age, String emailAddress, Gender gender, String name, String phoneNumber) {
        this.address = address;
        this.age = age;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public User() {

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
