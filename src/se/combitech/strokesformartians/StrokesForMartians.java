package se.combitech.strokesformartians;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class StrokesForMartians extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	final Intent paintIntent = new Intent(this, Paint.class);
        final Intent danceIntent = new Intent( this, DanceAnimator.class );
        
        LinearLayout layout = new LinearLayout(this);
        final Button button = new Button(this);
        button.setText( "Paint!" );
        button.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View view)
        	{
        		button.setText( "pooo!" );
        		try
        		{
        			startActivity( danceIntent );
        		}
        		catch( ActivityNotFoundException anfe )
        		{
        			System.out.println( anfe.getMessage() );
        		}
        	}
        });
        
        layout.addView(button);
        
        setContentView(layout);
    }
}