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

    private TextView name;
    private TextView username;
    private TextView password;
    private TextView reenterPassword;
    private TextView emailAddress;
    private TextView age;
    private RadioGroup gender;
    private Button register;


    private DatabaseHelper dbHelper = new DatabaseHelper(this);

    public final static String NAME = "ie.ait.touristapp.NAME";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setViewFields();
        register.setOnClickListener(new CheckRegisterFieldsListener());
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
        //TODO Check users existence in DB.

        //TODO Create user
        dbHelper.insertUser(user);

        //TODO move to login activity
    }

    private User createUser() {
        UserBuilder userBuilder= new UserBuilder();
        User user = userBuilder.setName((String) name.getText().toString())
                    .setUsername((String) username.getText().toString())
                    .setPassword((String) password.getText().toString())
                    .setEmailAddress((String) emailAddress.getText().toString())
                    .setAge(new Integer(age.getText().toString())).build();
        if(gender.getCheckedRadioButtonId()==0){
            user.setGender(Gender.MALE);
        } else {
            user.setGender(Gender.FEMALE);
        }
        return user;
    }

    class CheckRegisterFieldsListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String passwordString = password.getText().toString();
            String reenterPasswordString = reenterPassword.getText().toString();
            if(name.getText().toString().equals("")){
                name.setError("Name cannot be empty");
            }
            else if(username.getText().toString().equals("")){
                username.setError("Username cannot be empty");
            }
            else if(passwordString.equals("")){
                password.setError("Password cannot be empty");
            }
            else if(reenterPasswordString.equals("")){
                reenterPassword.setError("Re-enter Password cannot be empty");
            }
            else if(emailAddress.getText().toString().equals("")){
                emailAddress.setError("emailAddress cannot be empty");
            }
            else if(age.getText().toString().equals("")){
                age.setError("age cannot be empty");;
            }
            else if(gender.getCheckedRadioButtonId()==-1){
                register.setError("Gender cannot be empty");
            } else if (!passwordString.equals(reenterPasswordString)){
                reenterPassword.setError("Passwords must match");
            }else {
                registerUser();
            }
        }
    }
}