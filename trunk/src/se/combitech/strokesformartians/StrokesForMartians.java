package se.combitech.strokesformartians;

import se.combitech.strokesformartians.credits.Credits;
import se.combitech.strokesformartians.dancing.Dancer;
import se.combitech.strokesformartians.drawing.FingerPaint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class StrokesForMartians extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	//final Intent paintIntent = new Intent( this, Paint.class );
        final Intent paintIntent = new Intent( this, FingerPaint.class );
        final Intent danceIntent = new Intent( this, Dancer.class );
        final Intent creditIntent = new Intent( this, Credits.class );
        
        LinearLayout layout = new LinearLayout(this);
        final Button paintButton = new Button(this);
        final Button danceButton = new Button(this);
        final Button creditButton = new Button(this);
        paintButton.setText( "Paint!" );
        paintButton.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View view)
        	{
        		paintButton.setText( "pooo!" );
        		try
        		{
        			startActivity( paintIntent );
        		}
        		catch( ActivityNotFoundException anfe )
        		{
        			System.out.println( anfe.getMessage() );
        		}
        	}
        });
        
        danceButton.setText( "Dance mofo!" );
        danceButton.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View view)
        	{
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

        creditButton.setText( "Credits" );
        creditButton.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View view)
        	{
        		try
        		{
        			startActivity( creditIntent );
        		}
        		catch( ActivityNotFoundException anfe )
        		{
        			System.out.println( anfe.getMessage() );
        		}
        	}
        });
        
        

        
        
        layout.addView( paintButton );
        layout.addView( danceButton );
        layout.addView( creditButton );
        
        setContentView(layout);
    }
}