package se.combitech.strokesformartians.dancing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLUtils.*;

import se.combitech.strokesformartians.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Matrix;

/**
 * A vertex shaded cube.
 */
class MartianModel
{
	private MartianMesh m_mesh;
	private MartianBone m_rootBone;
	private MartianAnimator m_animator;
	private boolean m_debug;
	private MartianProperty property;
	private Context m_context = null;
	private float[] skeletonVertexBuffer;
	private byte[] skeletonIndexBuffer; 	
	
	
	public MartianModel( Context context )
	{
		this( context, false );
	}
	
	public MartianModel( Context context, boolean debugFlag )
    {
		skeletonVertexBuffer = new float[ /*m_animator.leroy.bones.size() * 3*/45 ];
		skeletonIndexBuffer = new byte[30];
		m_context = context;
		m_debug = debugFlag;
		
		property = new MartianProperty();
		property.addTexture(0);

    }

	public void setDebug( boolean flag )
	{
		m_debug = flag;
	}
	
    public void draw( GL10 gl )
    {
    	gl.glColor4f( 0, 1, 1, 1 );
        gl.glEnable( GL10.GL_TEXTURE_2D );
        
    	if( property.getTexture(0) == 0 )
    	{
    		Bitmap bitmap = BitmapFactory.decodeResource( m_context.getResources(), R.drawable.flowers );
    				
    		gl.glGenTextures( 	1, 
    							property.getTextures(),
								0 );
    		gl.glBindTexture( 	GL10.GL_TEXTURE_2D, 
    							property.getTextureByIndex(0) );
    		
    		android.opengl.GLUtils.texImage2D( 	GL10.GL_TEXTURE_2D,
							    				0,
							    				bitmap,
							    				0 );
    	}
    	
    	gl.glBindTexture( 	GL10.GL_TEXTURE_2D, 
    			            property.getTextureByIndex(0) );
		
		gl.glTexEnvf( 	GL10.GL_TEXTURE_ENV, 
						GL10.GL_TEXTURE_ENV_MODE, 
						GL10.GL_DECAL );

		m_animator.getSkeletonFrame( 0, skeletonVertexBuffer, skeletonIndexBuffer );
		
		gl.glDisable( GL10.GL_TEXTURE_2D );
		gl.glColor4f( 0, 0, 0, 1 );
		try {
			ByteBuffer vertexByteBuffer;
			vertexByteBuffer = ByteBuffer.allocateDirect( skeletonVertexBuffer.length * 4 );
			vertexByteBuffer.order( ByteOrder.nativeOrder() );
			FloatBuffer vertexBuffer = vertexByteBuffer.asFloatBuffer();
			vertexBuffer.put( skeletonVertexBuffer );
			vertexBuffer.position( 0 );
			
			ByteBuffer mIndexBuffer = ByteBuffer.allocateDirect( skeletonIndexBuffer.length );
			mIndexBuffer.put( skeletonIndexBuffer );
			mIndexBuffer.position( 0 );

	        gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
			
			gl.glVertexPointer( 	3,
									GL10.GL_FLOAT, 
									0,
									vertexBuffer );
			
			gl.glLineWidth( 2 );
			
			gl.glDrawElements( 	GL10.GL_LINES, 
				  				30, 
				  				GL10.GL_UNSIGNED_BYTE, 
				  				mIndexBuffer );
			
	        gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
    }
}