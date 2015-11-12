package ie.ait.touristapp.location;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import ie.ait.touristapp.HelloAndroidActivity;
import ie.ait.touristapp.R;
import ie.ait.touristapp.user.AppHomeActivity;

/**
 * Created by Collins on 12/11/2015.
 */
public class RefineSearchActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button next = (Button) findViewById(R.id.refine);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AppHomeActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }

}


