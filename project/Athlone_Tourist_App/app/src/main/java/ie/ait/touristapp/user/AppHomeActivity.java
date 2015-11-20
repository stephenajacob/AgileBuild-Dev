package ie.ait.touristapp.user;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;

import ie.ait.touristapp.HelloAndroidActivity;
import ie.ait.touristapp.Music_Activity;
import ie.ait.touristapp.R;

/**
 * Created by Collins on 12/11/2015.
 * Modified by brendan on 19/11/2015 - added methods for buttons.
 */
public class AppHomeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void onButtonClick(View v)//opens the Accomodation screen
    {
        if(v.getId()==R.id.Baccom)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

    public void onButtonClick2(View v)//opens the Music screen
    {
        if(v.getId()==R.id.Bmusic)
        {
            Intent i = new Intent(this, Music_Activity.class);
            startActivity(i);
        }
    }

    public void onButtonClick3(View v)//opens the Nightlife screen
    {
        if(v.getId()==R.id.Bnight)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClick4(View v)//opens the History Screen
    {
        if(v.getId()==R.id.Bhist)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClick5(View v)// opens the Activities Screen
    {
        if(v.getId()==R.id.Bactiv)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

    public void onButtonClick6(View v)//Opens the Food Screen
    {
        if(v.getId()==R.id.Bfood)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClick7(View v)//Opens the Login Screen, logs out the user
    {
        if(v.getId()==R.id.Blogout)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

}


