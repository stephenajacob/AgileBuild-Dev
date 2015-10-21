package ie.ait.touristapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;


import ie.ait.touristapp.user.User;
import ie.ait.touristapp.user.UserBuilder;

/**
 * Created by ethomev on 10/21/15.
 */
public class RegisterActivity extends Activity {

    public final static String NAME = "ie.ait.touristapp.NAME";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void registerUser(View view){

        TextView name = (TextView) findViewById(R.id.name);
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        TextView emailAddress = (TextView) findViewById(R.id.emailAddress);
        TextView address = (TextView) findViewById(R.id.address);
        TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        TextView age = (TextView)findViewById(R.id.age);
        RadioButton male = (RadioButton)findViewById(R.id.male);
        RadioButton female = (RadioButton)findViewById(R.id.female);

        UserBuilder userBuilder= new UserBuilder();
        User user = userBuilder.setName((String) name.getText().toString())
                        .setUsername((String) username.getText().toString())
                        .setPassword((String) password.getText().toString())
                        .setEmailAddress((String) emailAddress.getText().toString())
                        .setAddress((String) address.getText().toString())
                        .setPhoneNumber((String) phoneNumber.getText().toString())
                        .setAge(new Integer(age.getText().toString())).build();

        Intent intent = new Intent(this, RegisteredActivity.class);
        intent.putExtra(NAME, (String)name.getText().toString());
        startActivity(intent);
    }

}