package se.combitech.strokesformartians;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RemoteViews.ActionException;

public class StrokesForMartians extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final Intent myIntent = new Intent("se.combitech.strokesformartians.Paint");
        
        LinearLayout layout = new LinearLayout(this);
        final Button button = new Button(this);
        button.setText("Paint!");
        button.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View view)
        	{
        		//view.getContext().startActivity(intent)
        		button.setText("pooo!");
        		startActivity(myIntent);
        	}
        });
        
        layout.addView(button);
        
        setContentView(layout);
    }
}