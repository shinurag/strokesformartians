package se.combitech.strokesformartians.dancing;

import java.io.StringWriter;

import javax.microedition.khronos.opengles.GL;

import android.app.Activity;
import android.opengl.GLDebugHelper;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.GLWrapper;
import android.os.Bundle;
import android.util.Log;

/**
 * Wrapper activity demonstrating the use of {@link GLSurfaceView}, a view
 * that uses OpenGL drawing into a dedicated surface.
 */
public class Dancer extends Activity {

    private GLSurfaceView mGLSurfaceView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create our Preview view and set it as the content of our
        // Activity
        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setRenderer( new MartianRenderer( this, false, true ) );
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
}