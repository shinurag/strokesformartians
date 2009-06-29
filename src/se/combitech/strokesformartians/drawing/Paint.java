package se.combitech.strokesformartians.drawing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class Paint extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //LinearLayout layout = new LinearLayout(this);
        MyDrawableView mdv = new MyDrawableView(this);
        Button button = new Button(this);
        button.setText("Dance!");
        //layout.addView(button);
        
        
//        MyDrawableView mdv = new MyDrawableView(this);
//        layout.addView(mdv);
        
        /*
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(200, 250, Config.ARGB_4444);
		canvas.setBitmap(bitmap);
		*/
		
		
        //setContentView(layout);
		setContentView(mdv);
    }
}