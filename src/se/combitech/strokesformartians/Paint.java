package se.combitech.strokesformartians;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;

public class Paint extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*LinearLayout layout = new LinearLayout(this);*/
        Button button = new Button(this);
        button.setText("Dance!");
      
        /*layout.addView(button);
                       
        setContentView(layout);*/
        
        FrameLayout frame = new FrameLayout(this);
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.flowers);
        i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
        i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        button.setLayoutParams(new FrameLayout.LayoutParams(75, 50));
        frame.addView(i ,0);
        
        frame.addView(button, 1);
        
        setContentView(frame);
        
    }
}