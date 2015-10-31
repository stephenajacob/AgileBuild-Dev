package ie.ait.touristapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class HelloAndroidActivity extends Activity {

    private static String TAG = "AthloneTouristApp";
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";


    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.app_home);
    }

    public void openRegisterScreen(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}

