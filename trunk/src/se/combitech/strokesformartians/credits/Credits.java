package se.combitech.strokesformartians.credits;

import android.app.Activity;
import android.os.Bundle;


public class Credits extends Activity {
	

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CreditsView creditsView = new CreditsView(this);
        setContentView(creditsView);
        
        Thread t = new Thread( creditsView );
        t.start();
	}
}
