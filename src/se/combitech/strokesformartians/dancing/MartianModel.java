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
	private int[] m_textureIds;
	private Context m_context = null;
	
	public MartianModel( Context context )
	{
		this( context, false );
	}
	
	public MartianModel( Context context, boolean debugFlag )
    {
		m_rootBone = new MartianBone();
		m_mesh = new MartianMesh();
		m_animator = new MartianAnimator();
		m_debug = debugFlag;
		m_textureIds = new int[1];
		if( m_debug )
		{
			createMartian();
		}
		
		m_context = context;
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
		Matrix.rotateM( leftArm.m_restTransform, 0, identity, 0, 90, 0, 0, 1 );
		torso.m_children.add( leftArm );

		MartianBone leftWrist = new MartianBone();
		leftWrist.m_length = 0.4f;
		leftWrist.m_parent = leftArm;
		leftWrist.m_rotations[2] = 1;
		leftWrist.m_rotations[3] = 90;
		leftWrist.m_color[1] = 1;
		Matrix.rotateM( leftWrist.m_restTransform, 0, identity, 0, 90, 0, 0, 1 );
		leftArm.m_children.add( leftWrist );
		
		MartianBone rightArm = new MartianBone();
		rightArm.m_length = 0.4f;
		rightArm.m_parent = torso;
		rightArm.m_rotations[2] = 1;
		rightArm.m_rotations[3] = -90;
		Matrix.rotateM( rightArm.m_restTransform, 0, identity, 0, -90, 0, 0, 1 );
		torso.m_children.add( rightArm );
		
		MartianBone rightWrist = new MartianBone();
		rightWrist.m_length = 0.4f;
		rightWrist.m_parent = rightArm;
		rightWrist.m_rotations[2] = 1;
		rightWrist.m_rotations[3] = 135;
		Matrix.rotateM( rightWrist.m_restTransform, 0, identity, 0, 135, 0, 0, 1 );
		rightArm.m_children.add( rightWrist );
		
		MartianBone head = new MartianBone();
		head.m_length = 0.3f;
		head.m_parent = torso;
		Matrix.rotateM( head.m_restTransform, 0, identity, 0, -10, 0, 0, 1 );
		torso.m_children.add( head );
		
		MartianBone leftThigh = new MartianBone();
		leftThigh.m_length = 0.5f;
		leftThigh.m_parent = m_rootBone;
		leftThigh.m_rotations[2] = 1;
		leftThigh.m_rotations[3] = 135;
		Matrix.rotateM( leftThigh.m_restTransform, 0, identity, 0, 135, 0, 0, 1 );
		m_rootBone.m_children.add( leftThigh );
		
		MartianBone leftCalf = new MartianBone();
		leftCalf.m_length = 0.5f;
		leftCalf.m_parent = leftThigh;
		leftCalf.m_rotations[2] = 1;
		leftCalf.m_rotations[3] = 180;
		Matrix.rotateM( leftCalf.m_restTransform, 0, identity, 0, 180, 0, 0, 1 );
		leftThigh.m_children.add( leftCalf );		
		
		MartianBone rightThigh = new MartianBone();
		rightThigh.m_length = 0.5f;
		rightThigh.m_parent = m_rootBone;
		rightThigh.m_rotations[2] = 1;
		rightThigh.m_rotations[3] = -135;
		Matrix.rotateM( rightThigh.m_restTransform, 0, identity, 0, -135, 0, 0, 1 );
		m_rootBone.m_children.add( rightThigh );
		
		MartianBone rightCalf = new MartianBone();
		rightCalf.m_length = 0.5f;
		rightCalf.m_parent = rightThigh;
		rightCalf.m_rotations[2] = 1;
		rightCalf.m_rotations[3] = 180;
		Matrix.rotateM( rightCalf.m_restTransform, 0, identity, 0, 180, 0, 0, 1 );
		rightThigh.m_children.add( rightCalf );	

	}
	
	public void setDebug( boolean flag )
	{
		m_debug = flag;
	}
	
    public void draw( GL10 gl )
    {
    	gl.glColor4f( 0, 1, 1, 1 );
        gl.glEnable( GL10.GL_TEXTURE_2D );
        
    	if( m_textureIds[0] == 0 )
    	{
    		Bitmap bitmap = BitmapFactory.decodeResource( m_context.getResources(), R.drawable.flowers );
    				
    		gl.glGenTextures( 	1, 
    							m_textureIds,
								0 );
    		gl.glBindTexture( 	GL10.GL_TEXTURE_2D, 
    							m_textureIds[0] );
    		
    		android.opengl.GLUtils.texImage2D( 	GL10.GL_TEXTURE_2D,
							    				0,
							    				bitmap,
							    				0 );
    	}
    	
    	gl.glBindTexture( 	GL10.GL_TEXTURE_2D, 
							m_textureIds[0] );
		
		gl.glTexEnvf( 	GL10.GL_TEXTURE_ENV, 
						GL10.GL_TEXTURE_ENV_MODE, 
						GL10.GL_DECAL );

//		try {
//			ByteBuffer vertexByteBuffer;
//			vertexByteBuffer = ByteBuffer.allocateDirect( m_mesh.getVertices().length * 4 );
//			vertexByteBuffer.order( ByteOrder.nativeOrder() );
//			FloatBuffer vertexBuffer = vertexByteBuffer.asFloatBuffer();
//			vertexBuffer.put( m_mesh.getVertices() );
//			vertexBuffer.position( 0 );
//
//			ByteBuffer texCoordByteBuffer;
//			texCoordByteBuffer = ByteBuffer.allocateDirect( m_mesh.getTextureCoordinates().length * 4 );
//			texCoordByteBuffer.order( ByteOrder.nativeOrder() );
//			FloatBuffer textureCoordinateBuffer = texCoordByteBuffer.asFloatBuffer();
//			textureCoordinateBuffer.put( m_mesh.getTextureCoordinates() );
//			textureCoordinateBuffer.position( 0 );
//
//			
//			ByteBuffer mIndexBuffer = ByteBuffer.allocateDirect( m_mesh.getIndices().length );
//			mIndexBuffer.put( m_mesh.getIndices() );
//			mIndexBuffer.position( 0 );
//			 
//	        gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
//	        gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
//			
//			
//			gl.glVertexPointer( 	4,
//									GL10.GL_FLOAT, 
//									0,
//									vertexBuffer );
//			
//			gl.glTexCoordPointer( 	2, 
//									GL10.GL_FLOAT, 
//									0, 
//									textureCoordinateBuffer );
//			
//			gl.glLineWidth( 2 );
//			
//			gl.glDrawElements( 	GL10.GL_TRIANGLES, 
//				  				6, 
//				  				GL10.GL_UNSIGNED_BYTE, 
//				  				mIndexBuffer );
//			
//	        gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );
//	        gl.glDisableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
//
//			
//		} catch ( Exception e ) {
//			e.printStackTrace();
//		}

		float[] skeletonVertexBuffer = null;
		byte[] skeletonIndexBuffer = null;
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
			
			gl.glVertexPointer( 	2,
									GL10.GL_FLOAT, 
									0,
									vertexBuffer );
			
			gl.glLineWidth( 2 );
			
			gl.glDrawElements( 	GL10.GL_LINES, 
				  				15, 
				  				GL10.GL_UNSIGNED_BYTE, 
				  				mIndexBuffer );
			
	        gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
    }
}