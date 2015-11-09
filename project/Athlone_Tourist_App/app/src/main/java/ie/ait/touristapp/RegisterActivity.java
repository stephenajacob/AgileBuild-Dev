package ie.ait.touristapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import ie.ait.touristapp.db.DatabaseHelper;
import ie.ait.touristapp.user.Gender;
import ie.ait.touristapp.user.User;
import ie.ait.touristapp.user.UserBuilder;

public class RegisterActivity extends Activity {

    public static final String PASSWORDS_MUST_MATCH = "passwords must match";
    public static final String ALREADY_EXISTS_MESSAGE_TEMPLATE = "%s %s already exists";
    public static final String FIELD_CANNOT_BE_EMPTY = "field cannot be empty";

    private TextView name, username, password, reenterPassword, emailAddress, age;
    private transient List<TextView> fields;
    private RadioGroup gender;
    private Button register;
    private DatabaseHelper dbHelper;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setViewFields();
        register.setOnClickListener(new CheckRegisterFieldsListener());
        dbHelper = new DatabaseHelper(this);
    }

    private void setViewFields() {
        fields = new ArrayList<>();
        name = (TextView) findViewById(R.id.name);
        fields.add(name);
        username = (TextView) findViewById(R.id.username);
        fields.add(username);
        password = (TextView) findViewById(R.id.password);
        fields.add(password);
        reenterPassword = (TextView) findViewById(R.id.reenterPassword);
        fields.add(reenterPassword);
        emailAddress = (TextView) findViewById(R.id.emailAddress);
        fields.add(emailAddress);
        age = (TextView)findViewById(R.id.age);
        fields.add(age);
        gender = (RadioGroup)findViewById(R.id.gender);
        register = (Button)findViewById(R.id.register);


    }

    public void registerUser(){
        final User user = createUser();
        dbHelper.insertUser(user);
        final Intent intent = new Intent(this, HelloAndroidActivity.class);
        startActivity(intent);
    }

    private User createUser() {
        final UserBuilder userBuilder= new UserBuilder();
        final User user = userBuilder.setName(name.getText().toString())
                    .setUsername( username.getText().toString())
                    .setPassword(password.getText().toString())
                    .setEmailAddress(emailAddress.getText().toString())
                .setAge(Integer.valueOf(age.getText().toString())).build();
        if(gender.getCheckedRadioButtonId()==Gender.MALE.getValue()){
            user.setGender(Gender.MALE);
        } else {
            user.setGender(Gender.FEMALE);
        }
        return user;
    }

    class CheckRegisterFieldsListener implements View.OnClickListener{

        @Override
        public void onClick(final View view) {
            final String usernameString = username.getText().toString();
            final String passwordString = password.getText().toString();
            final String reenterPasswordString = reenterPassword.getText().toString();
            final String emailAddressString = emailAddress.getText().toString();
            boolean fieldsFilled = true;
            for(final TextView field: fields){
                if(field.getText().toString().isEmpty()){
                    field.setError(FIELD_CANNOT_BE_EMPTY);
                    fieldsFilled = false;
                }
            }
            if(gender.getCheckedRadioButtonId()==-1){
                register.setError(FIELD_CANNOT_BE_EMPTY);
                fieldsFilled = false;
            }
            if (!passwordString.equals(reenterPasswordString)){
                reenterPassword.setError(PASSWORDS_MUST_MATCH);
                fieldsFilled = false;
            }
            if(dbHelper.valueExistsForColumnInTable(usernameString, DatabaseHelper.USERNAME_COLUMN, DatabaseHelper.USER_TABLE_NAME)){
                username.setError(String.format(ALREADY_EXISTS_MESSAGE_TEMPLATE, DatabaseHelper.USERNAME_COLUMN, usernameString));
                fieldsFilled = false;
            }
            if(dbHelper.valueExistsForColumnInTable(emailAddressString, DatabaseHelper.EMAIL_COLUMN, DatabaseHelper.USER_TABLE_NAME)){
                emailAddress.setError(String.format(ALREADY_EXISTS_MESSAGE_TEMPLATE, DatabaseHelper.EMAIL_COLUMN,emailAddressString));
                fieldsFilled = false;
            }
            if(fieldsFilled) {
                registerUser();
            }
        }
    }
}