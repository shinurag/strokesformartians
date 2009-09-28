package se.combitech.strokesformartians;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;
import android.view.Window;
import android.view.WindowManager;


public class StrokesForMartians extends Activity {
	
	private Intent danceIntent = null;
	
    public static final int MENU_LOAD_ID = 0;
    public static final int MENU_DRAW_ID = 1;
    public static final int MENU_CREDITS_ID = 2;
    public static final int MENU_EXIT_ID = 3;
    
    
    public static final int DANCE_REQUEST = 0;
    public static final int PAINT_REQUEST = 1;
    
    
    public static final int EXIT_RESULT_CODE = 0;
	
	long startTime;
	
	FrameLayout frame;
	ImageView image;
	
	private void init()
	{
		danceIntent = SFMIntentFactory.createDancerIntent(this);
	}
	
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	if( requestCode == DANCE_REQUEST ) {
    		if ( resultCode == EXIT_RESULT_CODE ) {
    			
    			finish();
    		}
    	}
    }
    
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        
        init();

        startTime = Calendar.getInstance().getTimeInMillis();
        
        
        frame = new FrameLayout( this );
        image = new ImageView( this );
        image.setImageResource( R.drawable.splash01 );
        image.setAdjustViewBounds( true ); // set the ImageView bounds to match the Drawable's dimensions
//        image.setLayoutParams( new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        frame.addView( image, 0 );
        
        setContentView( frame );
        
     
    	final long timeStep = 1000;
    	final long flashStep = 200;

		new Handler().postDelayed( 
			new Runnable()
			{
        		public void run() {
					image.setImageResource( R.drawable.splash02 );
				}
			}, 
			timeStep * 2 );
			
		new Handler().postDelayed( 
			new Runnable()
			{
        		public void run() {
					image.setImageResource( R.drawable.splash03 );
				}
			}, 
			timeStep * 3 ); 
			
		new Handler().postDelayed( 
			new Runnable()
			{
        		public void run() {
					image.setImageResource( R.drawable.splash04 );
				}
			}, 
			timeStep * 4 ); 
			
		new Handler().postDelayed( 
			new Runnable()
			{
        		public void run() {
					image.setImageResource( R.drawable.splash03 );
				}
			}, 
			timeStep * 5 + flashStep );

		new Handler().postDelayed( 
			new Runnable()
			{
        		public void run() {
					image.setImageResource( R.drawable.splash04 );
				}
			}, 
			timeStep * 5 + flashStep * 2 ); 			
      
//		new Handler().postDelayed( 
//			new Runnable()
//			{
//        		public void run() {
//					image.setImageResource( R.drawable.splash03 );
//				}
//			}, 
//			timeStep * 5 + flashStep * 3 );       
//      
//		new Handler().postDelayed( 
//			new Runnable()
//			{
//        		public void run() {
//					image.setImageResource( R.drawable.splash04 );
//				}
//			}, 
//			timeStep * 5 + flashStep * 4 );         
      
		new Handler().postDelayed( 
			new Runnable()
			{
        		public void run() {
					startActivityForResult( danceIntent, DANCE_REQUEST );
					finish();
				}
			}, 
			timeStep * 7 );       
      
        // Removes the grey title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // Removes Androids status bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); 
//        startActivityForResult( danceIntent, DANCE_REQUEST );
    }
}