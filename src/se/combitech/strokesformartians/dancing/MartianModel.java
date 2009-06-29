package se.combitech.strokesformartians.dancing;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.Matrix;

/**
 * A vertex shaded cube.
 */
class MartianModel
{
	private MartianBone m_rootBone;
	private boolean m_debug;
	
	public MartianModel()
	{
		this( false );
	}
	
	public MartianModel( boolean debugFlag )
    {
		m_rootBone = new MartianBone();
		m_debug = debugFlag;
		if( m_debug )
		{
			createMartian();
		}
    }

	private void createMartian()
	{
		float[] identity = new float[16];
		Matrix.setIdentityM( identity, 0 );
		
		m_rootBone.m_length = 1.0f;
		
		MartianBone child = new MartianBone();
		child.m_length = 0.6f;
		child.m_parent = m_rootBone;
		Matrix.rotateM( child.m_transform, 0, identity, 0, 90, 0, 0, 1 );
		m_rootBone.m_children.add( child );
		
		child = new MartianBone();
		child.m_length = 0.4f;
		child.m_parent = m_rootBone;
		Matrix.rotateM( child.m_transform, 0, identity, 0, -10, 0, 0, 1 );
		m_rootBone.m_children.add( child );
	}
	
	public void setDebug( boolean flag )
	{
		m_debug = flag;
	}
	
    public void draw( GL10 gl )
    {
//    	gl.glScalef(60000, 60000, 60000);
        //gl.glFrontFace( gl.GL_CW );
//        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
//        gl.glLineWidth( 2 );
//        gl.glColor4f(0, 0, 0, 1);
//        gl.glDrawElements( 	GL10.GL_LINES, 
//        					24, 
//        					GL10.GL_UNSIGNED_BYTE, 
//        					mIndexBuffer);
        
        if( m_debug )
        {
        	m_rootBone.draw( gl );
        }
    }
}