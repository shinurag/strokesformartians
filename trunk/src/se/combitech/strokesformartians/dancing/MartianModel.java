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
		
		m_rootBone.m_length = 0.0f;
		
		MartianBone torso = new MartianBone();
		torso.m_length = 1.0f;
		torso.m_parent = m_rootBone;
		torso.m_color[0] = 1;
		torso.m_color[1] = 1;
		m_rootBone.m_children.add( torso );
		
		MartianBone leftArm = new MartianBone();
		leftArm.m_length = 0.4f;
		leftArm.m_parent = torso;
		leftArm.m_rotations[2] = 1;
		leftArm.m_rotations[3] = 90;
		leftArm.m_color[0] = 1;
		Matrix.rotateM( leftArm.m_transform, 0, identity, 0, 90, 0, 0, 1 );
		torso.m_children.add( leftArm );

		MartianBone leftWrist = new MartianBone();
		leftWrist.m_length = 0.4f;
		leftWrist.m_parent = leftArm;
		leftWrist.m_rotations[2] = 1;
		leftWrist.m_rotations[3] = 90;
		leftWrist.m_color[1] = 1;
		Matrix.rotateM( leftWrist.m_transform, 0, identity, 0, 90, 0, 0, 1 );
		leftArm.m_children.add( leftWrist );
		
//		MartianBone rightArm = new MartianBone();
//		rightArm.m_length = 0.4f;
//		rightArm.m_parent = torso;
//		rightArm.m_rotations[2] = 1;
//		rightArm.m_rotations[3] = -90;
//		Matrix.rotateM( rightArm.m_transform, 0, identity, 0, -90, 0, 0, 1 );
//		torso.m_children.add( rightArm );
//		
//		MartianBone rightWrist = new MartianBone();
//		rightWrist.m_length = 0.4f;
//		rightWrist.m_parent = rightArm;
//		rightWrist.m_rotations[2] = 1;
//		rightWrist.m_rotations[3] = 135;
//		Matrix.rotateM( rightWrist.m_transform, 0, identity, 0, 135, 0, 0, 1 );
//		rightArm.m_children.add( rightWrist );
//		
//		MartianBone head = new MartianBone();
//		head.m_length = 0.3f;
//		head.m_parent = torso;
//		Matrix.rotateM( head.m_transform, 0, identity, 0, -10, 0, 0, 1 );
//		torso.m_children.add( head );
//		
//		MartianBone leftThigh = new MartianBone();
//		leftThigh.m_length = 0.5f;
//		leftThigh.m_parent = m_rootBone;
//		leftThigh.m_rotations[2] = 1;
//		leftThigh.m_rotations[3] = 135;
//		Matrix.rotateM( leftThigh.m_transform, 0, identity, 0, 135, 0, 0, 1 );
//		m_rootBone.m_children.add( leftThigh );
//		
//		MartianBone leftCalf = new MartianBone();
//		leftCalf.m_length = 0.5f;
//		leftCalf.m_parent = leftThigh;
//		leftCalf.m_rotations[2] = 1;
//		leftCalf.m_rotations[3] = 180;
//		Matrix.rotateM( leftCalf.m_transform, 0, identity, 0, 180, 0, 0, 1 );
//		leftThigh.m_children.add( leftCalf );		
//		
//		MartianBone rightThigh = new MartianBone();
//		rightThigh.m_length = 0.5f;
//		rightThigh.m_parent = m_rootBone;
//		rightThigh.m_rotations[2] = 1;
//		rightThigh.m_rotations[3] = -135;
//		Matrix.rotateM( rightThigh.m_transform, 0, identity, 0, -135, 0, 0, 1 );
//		m_rootBone.m_children.add( rightThigh );
//		
//		MartianBone rightCalf = new MartianBone();
//		rightCalf.m_length = 0.5f;
//		rightCalf.m_parent = rightThigh;
//		rightCalf.m_rotations[2] = 1;
//		rightCalf.m_rotations[3] = 180;
//		Matrix.rotateM( rightCalf.m_transform, 0, identity, 0, 180, 0, 0, 1 );
//		rightThigh.m_children.add( rightCalf );	

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