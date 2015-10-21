package ie.ait.touristapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ethomev on 10/21/15.
 */
public class RegisteredActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.registered);
        Intent intent = getIntent();
        String name = intent.getStringExtra(RegisterActivity.USER);

        TextView nameView = new TextView(this);

        nameView.setTextSize(40);
        nameView.setText(name);
        setContentView(nameView);
    }
}
