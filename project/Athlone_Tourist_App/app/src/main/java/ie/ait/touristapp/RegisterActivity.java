package ie.ait.touristapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;


import ie.ait.touristapp.user.Gender;
import ie.ait.touristapp.user.User;
import ie.ait.touristapp.user.UserBuilder;

/**
 * Created by ethomev on 10/21/15.
 */
public class RegisterActivity extends Activity {

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String NAME_CANNOT_BE_EMPTY = "name cannot be empty";
    public static final String USERNAME_CANNOT_BE_EMPTY = "username cannot be empty";
    public static final String PASSWORD_CANNOT_BE_EMPTY = "password cannot be empty";
    public static final String EMAIL_ADDRESS_CANNOT_BE_EMPTY = "emailAddress cannot be empty";
    public static final String AGE_CANNOT_BE_EMPTY = "age cannot be empty";
    public static final String GENDER_CANNOT_BE_EMPTY = "gender cannot be empty";
    public static final String PASSWORDS_MUST_MATCH = "passwords must match";
    public static final String ALREADY_EXISTS = "%s %s already exists";
    private TextView name, username, password, reenterPassword, emailAddress, age;
    private RadioGroup gender;
    private Button register;
    private DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setViewFields();
        register.setOnClickListener(new CheckRegisterFieldsListener());
        dbHelper = new DatabaseHelper(this);
    }

    private void setViewFields() {
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        reenterPassword = (TextView) findViewById(R.id.reenterPassword);
        emailAddress = (TextView) findViewById(R.id.emailAddress);
        age = (TextView)findViewById(R.id.age);
        gender = (RadioGroup)findViewById(R.id.gender);
        register = (Button)findViewById(R.id.register);
    }

    public void registerUser(){
        User user = createUser();
        dbHelper.insertUser(user);
        Intent intent = new Intent(this, HelloAndroidActivity.class);
        startActivity(intent);
    }

    private User createUser() {
        UserBuilder userBuilder= new UserBuilder();
        User user = userBuilder.setName((String) name.getText().toString())
                    .setUsername((String) username.getText().toString())
                    .setPassword((String) password.getText().toString())
                    .setEmailAddress((String) emailAddress.getText().toString())
                    .setAge(new Integer(age.getText().toString())).build();
        if(gender.getCheckedRadioButtonId()==Gender.MALE.getValue()){
            user.setGender(Gender.MALE);
        } else {
            user.setGender(Gender.FEMALE);
        }
        return user;
    }

    class CheckRegisterFieldsListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();
            String reenterPasswordString = reenterPassword.getText().toString();
            String emailAddressString = emailAddress.getText().toString();
            if(name.getText().toString().isEmpty()){
                name.setError(NAME_CANNOT_BE_EMPTY);
            }
            else if(usernameString.isEmpty()){
                username.setError(USERNAME_CANNOT_BE_EMPTY);
            }
            else if(passwordString.isEmpty()){
                password.setError(PASSWORD_CANNOT_BE_EMPTY);
            }
            else if(reenterPasswordString.isEmpty()){
                reenterPassword.setError(PASSWORD_CANNOT_BE_EMPTY);
            }
            else if(emailAddressString.isEmpty()){
                emailAddress.setError(EMAIL_ADDRESS_CANNOT_BE_EMPTY);
            }
            else if(age.getText().toString().isEmpty()){
                age.setError(AGE_CANNOT_BE_EMPTY);
            }
            else if(gender.getCheckedRadioButtonId()==-1){
                register.setError(GENDER_CANNOT_BE_EMPTY);
            }
            else if (!passwordString.equals(reenterPasswordString)){
                reenterPassword.setError(PASSWORDS_MUST_MATCH);
            }
            else if(dbHelper.valueExistsForColumnInTable(DatabaseHelper.USERS_TABLE_NAME, usernameString, USERNAME)){
                username.setError(String.format(ALREADY_EXISTS, USERNAME, usernameString));
            }
            else if(dbHelper.valueExistsForColumnInTable(DatabaseHelper.USERS_TABLE_NAME, emailAddressString, EMAIL)){
                emailAddress.setError(String.format(ALREADY_EXISTS, EMAIL,emailAddressString));
            }
            else {
                registerUser();
            }
        }
    }
}