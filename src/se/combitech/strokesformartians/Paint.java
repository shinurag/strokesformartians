package se.combitech.strokesformartians;

import se.combitech.strokesformartians.drawing.MyDrawableView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class Paint extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        Button button = new Button(this);
        button.setText("Dance!");
        layout.addView(button);
        
        MyDrawableView mdv = new MyDrawableView(this);
        layout.addView(mdv);
        setContentView(layout);
    }
}