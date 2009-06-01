package se.combitech.strokesformartians;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class StrokesForMartians extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        Button button = new Button(this);
        button.setText("Paint!");
        layout.addView(button);
        
        setContentView(layout);
    }
}