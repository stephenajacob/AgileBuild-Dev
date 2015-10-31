package ie.ait.touristapp.user;

/**
 * Created by ethomev on 10/21/15.
 */
public class UserBuilder {

    private String name;
    private String username;
    private String password;
    private int age;
    private String emailAddress;
    private Gender gender;
    private String phoneNumber;
    private String address;

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setName(String name){
        this.name = name;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User build(){
        return new User(username, password, address, age, emailAddress, gender, name, phoneNumber);
    }
}
