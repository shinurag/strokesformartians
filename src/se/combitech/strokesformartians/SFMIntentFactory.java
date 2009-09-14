package se.combitech.strokesformartians;

import se.combitech.strokesformartians.credits.Credits;
import se.combitech.strokesformartians.dancing.Dancer;
import se.combitech.strokesformartians.drawing.FingerPaint;
import android.content.Context;
import android.content.Intent;

public class SFMIntentFactory 
{

	/**
	 * Creates a new intent for the Dancer activity.
	 * 
	 * @param c		context requesting the intent
	 * @return		an intent for the Dancer activity
	 */
	public static Intent createDancerIntent(Context c) 
	{	
		return new Intent( c, Dancer.class );
	}
	
	/**
	 * Creates a new intent for the FingerPaint activity.
	 * 
	 * @param c		context requesting the intent
	 * @return		an intent for the FingerPaint activity
	 */
	public static Intent createPaintIntent(Context c) 
	{	
		return new Intent( c, FingerPaint.class );
	}
	
	/**
	 * Creates a new intent for the Credits activity.
	 * 
	 * @param c		context requesting the intent
	 * @return		an intent for the Credits activity
	 */
	public static Intent createCreditsIntent(Context c) 
	{	
		return new Intent( c, Credits.class );
	}	
}
