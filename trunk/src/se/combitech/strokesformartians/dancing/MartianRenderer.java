package se.combitech.strokesformartians.dancing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import se.combitech.strokesformartians.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;

import java.util.*;

/**
 * Render a pair of tumbling cubes.
 */

class MartianRenderer implements GLSurfaceView.Renderer {
    private boolean mTranslucentBackground;
    private MartianModel m_martian;
    private float mAngle;
    private MartianAnimator m_animator;
    private int[] m_textureIds;
    private Context m_context;
	private float[] mVertexBuffer;
	private float[] mTexCoordBuffer;
	private byte[] mIndexBuffer;
	private long startTime;
	private Bitmap m_textureBitmap;
	private Cube m_cube;
    
	public MartianRenderer( Context context, boolean useTranslucentBackground, boolean debugFlag, Bitmap textureBitmap ) {
        mTranslucentBackground = useTranslucentBackground;
		m_animator = new MartianAnimator( );
		m_textureIds = new int[2];
		m_context = context;
		m_textureBitmap = textureBitmap;
		m_cube = new Cube();
		
		startTime = Calendar.getInstance().getTimeInMillis();
		
		mVertexBuffer = new float[ m_animator.getVertexBufferLength() ];
		mIndexBuffer = new byte[ m_animator.getIndexBufferLength() ];
		mTexCoordBuffer = new float[m_animator.getTexCoordBufferLength()];
    }
	
    public void onDrawFrame(GL10 gl) 
    {
		long time = Calendar.getInstance().getTimeInMillis() - startTime;

        /*
         * Usually, the first thing one might want to do is to clear
         * the screen. The most efficient way of doing this is to use
         * glClear().
         */

//    	gl.glClearColor( (time % 200) / 200f, 0, 1, 1);
		gl.glClearColor( 1, 1, 1, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        /*
         * Now we're ready to draw some 3D objects
         */

        gl.glMatrixMode( GL10.GL_TEXTURE );
    	gl.glLoadIdentity();
    	
    	renderCubeMap( gl, time );
    	gl.glClear(GL10.GL_DEPTH_BUFFER_BIT);
    	
        gl.glMatrixMode( GL10.GL_MODELVIEW );
        gl.glLoadIdentity();
        //gl.glTranslatef( 0, 0, -5.0f );
        gl.glTranslatef( 0, -1f, -25.0f );
        //gl.glRotatef( -90, 1, 0, 0 );
//        gl.glRotatef(mAngle*0.25f,  1, 0, 0);

        renderMartianAnimator( gl , time);
        
        
        //mAngle++;
    }

	private void initTextures( GL10 gl )
	{
			Bitmap martianMap;
			Bitmap cubeMap;
			if( m_textureBitmap == null )
			{
				martianMap = BitmapFactory.decodeResource( m_context.getResources(), R.drawable.elvis );
			}
			else
			{
				martianMap = m_textureBitmap;
				m_textureBitmap = null;
			}
			
			cubeMap = BitmapFactory.decodeResource( m_context.getResources(), R.drawable.sky );
    				
    		gl.glGenTextures( 	2, 
    							m_textureIds,
								0 );
    		 
    		// Martian
    		gl.glBindTexture( 	GL10.GL_TEXTURE_2D, 
    							m_textureIds[0] );
    		
    		android.opengl.GLUtils.texImage2D( 	GL10.GL_TEXTURE_2D,
							    				0,
							    				martianMap,
							    				0 );
    		
    		// Cube map
    		gl.glBindTexture( 	GL10.GL_TEXTURE_2D, 
								m_textureIds[1] );

    		gl.glTexParameterx( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE );
    		gl.glTexParameterx( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE );
    		
    		android.opengl.GLUtils.texImage2D( 	GL10.GL_TEXTURE_2D,
												0,
												cubeMap,
												0 );
	}

	private void renderCubeMap( GL10 gl, float time )
	{
		gl.glPushMatrix();
//        	gl.glTranslatef(0, 0, 10);
			gl.glRotatef( 0.05f*time, 0, 1, 0 );
			gl.glColor4f( 1, 1, 1, 1 );
			gl.glEnable( GL10.GL_TEXTURE_2D );
//			gl.glEnable( GL10.GL_CULL_FACE );
//			gl.glCullFace( GL10.GL_BACK );
		
			gl.glBindTexture( GL10.GL_TEXTURE_2D, m_textureIds[ 1 ] );
			gl.glTexEnvf( 	GL10.GL_TEXTURE_ENV, 
							GL10.GL_TEXTURE_ENV_MODE, 
							GL10.GL_DECAL );
			
			m_cube.draw( gl );
		gl.glPopMatrix();
	}
    
    private void renderMartianAnimator( GL10 gl , long time)
    {
		gl.glColor4f( 1, 1, 1, 1 );
        gl.glEnable( GL10.GL_TEXTURE_2D );
        
    	if( m_textureIds[0] == 0 )
    	{
    		initTextures( gl );
    	}
        
    	gl.glBindTexture( 	GL10.GL_TEXTURE_2D, 
							m_textureIds[0] );
		
		gl.glTexEnvf( 	GL10.GL_TEXTURE_ENV, 
						GL10.GL_TEXTURE_ENV_MODE, 
						GL10.GL_DECAL );

		gl.glNormal3f( 	0,
						0,
						1 );
		
		// divide time by 33 to get a fps of 30
		m_animator.getFrame( time / 33f, mVertexBuffer, mTexCoordBuffer, mIndexBuffer );
	
		gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
		gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
		gl.glVertexPointer( 3, GL10.GL_FLOAT, 0, FloatBuffer.wrap(mVertexBuffer) );
		gl.glTexCoordPointer( 2, GL10.GL_FLOAT, 0, FloatBuffer.wrap(mTexCoordBuffer) );
		
		gl.glDrawElements( GL10.GL_TRIANGLES, mIndexBuffer.length, GL10.GL_UNSIGNED_BYTE, ByteBuffer.wrap(mIndexBuffer) );
		
    }    	
    
    public int[] getConfigSpec() {
        if (mTranslucentBackground) {
                // We want a depth buffer and an alpha buffer
                int[] configSpec = {
                        EGL10.EGL_RED_SIZE,      8,
                        EGL10.EGL_GREEN_SIZE,    8,
                        EGL10.EGL_BLUE_SIZE,     8,
                        EGL10.EGL_ALPHA_SIZE,    8,
                        EGL10.EGL_DEPTH_SIZE,   16,
                        EGL10.EGL_NONE
                };
                return configSpec;
            } else {
                // We want a depth buffer, don't care about the
                // details of the color buffer.
                int[] configSpec = {
                        EGL10.EGL_DEPTH_SIZE,   16,
                        EGL10.EGL_NONE
                };
                return configSpec;
            }
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
         gl.glViewport(0, 0, width, height);

         /*
          * Set our projection matrix. This doesn't have to be done
          * each time we draw, but usually a new projection needs to
          * be set when the viewport is resized.
          */

         float ratio = (float) width / height;
         gl.glMatrixMode(GL10.GL_PROJECTION);
         gl.glLoadIdentity();
         android.opengl.GLU.gluPerspective( gl, 50, ratio, 0.1f, 50 );
//         gl.glFrustumf(-ratio, ratio, -1, 1, 0.1f, 50 );
//         gl.glOrthof(-1, 1, -1, 1, 1, 10);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        /*
         * By default, OpenGL enables features that improve quality
         * but reduce performance. One might want to tweak that
         * especially on software renderer.
         */
        gl.glDisable(GL10.GL_DITHER);

        /*
         * Some one-time OpenGL initialization can be made here
         * probably based on features of this particular context
         */
         gl.glHint( GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                 	GL10.GL_NICEST);

         if (mTranslucentBackground) {
             gl.glClearColor(0,0,0,0);
         } else {
             gl.glClearColor(1,1,1,1);
         }
         // needs to disable culling since the guy is 2d
         gl.glDisable(GL10.GL_CULL_FACE);
         gl.glShadeModel(GL10.GL_SMOOTH);
         gl.glEnable(GL10.GL_DEPTH_TEST);
    }

	public void setTextureBitmap(Bitmap textureBitmap) 
	{
		m_textureBitmap = textureBitmap;
	}

}