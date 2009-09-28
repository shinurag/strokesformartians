package se.combitech.strokesformartians.dancing;

import se.combitech.strokesformartians.SFMIntentFactory;
import se.combitech.strokesformartians.StrokesForMartians;
import se.combitech.strokesformartians.credits.Credits;
import se.combitech.strokesformartians.drawing.FingerPaint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

/**
 * Wrapper activity demonstrating the use of {@link GLSurfaceView}, a view
 * that uses OpenGL drawing into a dedicated surface.
 */
public class Dancer extends Activity {

    private GLSurfaceView mGLSurfaceView;
    
	private Intent paintIntent = null;
    private Intent creditIntent = null;
    
    private MenuItem loadMenuItem;
	private MenuItem drawMenuItem;
	private MenuItem creditsMenuItem;
	private MenuItem exitMenuItem;
	
	private Bitmap mTextureBitmap = null;
	private MartianRenderer renderer;
	
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        if( this.getIntent().getParcelableExtra( "newBitmap" ) != null )
        {
        	mTextureBitmap = (Bitmap) this.getIntent().getParcelableExtra( "newBitmap" );
        }
        
        init();
        
        // Create our Preview view and set it as the content of our
        // Activity
        mGLSurfaceView = new GLSurfaceView(this);
        renderer = new MartianRenderer( this, false, true, mTextureBitmap );
        mGLSurfaceView.setRenderer( renderer );
//        mGLSurfaceView.setGLWrapper( 
//    		new GLWrapper()
//	        { 
//	    		public GL wrap( GL gl )
//	    		{
//	    			return GLDebugHelper.wrap( 
//    					gl, GLDebugHelper.CONFIG_CHECK_GL_ERROR, new StringWriter()
//		    			{
//    						public void write( String str )
//    						{
//    							Log.e("CUSTOMGLLogger", str );
//    						}
//		    			}
//	    			); 
//	    		}
//	        }
//		);
        
        // Removes the grey title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Removes Androids status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setContentView( mGLSurfaceView );
    }

    @Override
    protected void onResume() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
        mGLSurfaceView.onPause();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	if(requestCode == StrokesForMartians.DANCE_REQUEST) 
    	{
    		if (requestCode == StrokesForMartians.RESULT_OK)
    		{
    			/* TODO: Draw property */
    			if (data instanceof MartianProperty)
    			{    		
    				MartianProperty propery = (MartianProperty) data;
    				mTextureBitmap = propery.getBitmap();
    				renderer.setTextureBitmap(mTextureBitmap);    				
    			}
    		}
    	}
    }
    
    /* Creates the menu items */
    @Override
	public boolean onCreateOptionsMenu(Menu menu) 
    {    	
        loadMenuItem = menu.add(0, StrokesForMartians.MENU_LOAD_ID, 0, "Load");
        loadMenuItem.setEnabled(false);        
        drawMenuItem = menu.add(0, StrokesForMartians.MENU_DRAW_ID, 0, "Draw");        
        creditsMenuItem = menu.add(0, StrokesForMartians.MENU_CREDITS_ID, 0, "Credits");
        exitMenuItem = menu.add(0, StrokesForMartians.MENU_EXIT_ID, 0, "Exit");

        return true;
    }

    /* Handles item selections */
    @Override
	public boolean onOptionsItemSelected(MenuItem item) 
    {
    	boolean result;
    	switch (item.getItemId()) 
    	{
    	case StrokesForMartians.MENU_LOAD_ID:
    		result = true;
    		break;

    	case StrokesForMartians.MENU_DRAW_ID:
    		startActivityForResult(paintIntent, StrokesForMartians.PAINT_REQUEST);
    		result = true;
    		finish();
    		break;

    	case StrokesForMartians.MENU_CREDITS_ID:
    		startActivity( creditIntent );
    		result = true;
    		break;

    	case StrokesForMartians.MENU_EXIT_ID:
    		
    		/* 
    		 * End activity and return back to object that 
    		 * called onActivityResult() with a result code.
    		 */
    		setResult( StrokesForMartians.EXIT_RESULT_CODE );    		
    		finish();
    
    		result = true;
    		break;

    	default:
    		result = false;
    	}
    	return result;
    }
    
	private void init()
	{
		paintIntent = SFMIntentFactory.createPaintIntent(this);
		creditIntent = SFMIntentFactory.createCreditsIntent(this);
	}
    
}