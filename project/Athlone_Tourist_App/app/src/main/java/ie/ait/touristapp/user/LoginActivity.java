package ie.ait.touristapp.user;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import ie.ait.touristapp.HelloAndroidActivity;
import ie.ait.touristapp.R;
import ie.ait.touristapp.RegisterActivity;
import ie.ait.touristapp.db.DatabaseHelper;
import ie.ait.touristapp.location.CurrentLocation;


/**
 * Created by brendan on 03/11/2015.
 */
public class LoginActivity extends Activity{

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onButtonClick(View v)
    {
        if(v.getId()==R.id.Blogin)
        {
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);

            if(pass.equals(password))
            {
                Intent i = new Intent(LoginActivity.this, CurrentLocation.class);
                i.putExtra("Username", str);
                startActivity(i);
            }
            else
            {
                Toast temp = Toast.makeText(LoginActivity.this, "Username and Password don't match", Toast.LENGTH_SHORT);
                temp.show();
            }

        }
        if(v.getId()==R.id.Bregister)
        {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);

            startActivity(i);

        }

        if(v.getId()==R.id.Bcancel)
        {
            finish();

        }
    }

    }









