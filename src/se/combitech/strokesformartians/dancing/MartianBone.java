package se.combitech.strokesformartians.dancing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.Matrix;

public class MartianBone 
{
	public float[] m_restTransform;
	public float[] m_danceTransform;
	public float[] m_rotations;
	public float[] m_color;
	public String m_name;
	public float m_length;
	public MartianBone m_parent;
	public ArrayList< MartianBone > m_children;

	MartianBone( )
	{
		this( null );
	}
	
	MartianBone( MartianBone parent )
	{
		m_children = new ArrayList< MartianBone >();
		m_restTransform = new float[16];
		m_danceTransform = new float[16];
		m_rotations = new float[4];
		m_rotations[0] = m_rotations[1] = m_rotations[3] = 0;
		m_rotations[2] = 1;
		
		m_color = new float[4];
		m_color[3] = 1;

		Matrix.setIdentityM( m_restTransform, 0 );
		m_length = 0.0f;
		m_parent = parent;
	}
	
	private float[] addVec( float[] a, float[] b)
	{
		float[] result = {	a[0] + b[0],
							a[1] + b[1],
							a[2] + b[2],
							1 };
		return result;
	}
	
	private float[] multVec( float[] vec, float scalar )
	{
		float[] transformedVec = new float[ 4 ];
		
		transformedVec[ 0 ] = vec[ 0 ] * scalar;
		transformedVec[ 1 ] = vec[ 1 ] * scalar;
		transformedVec[ 2 ] = vec[ 2 ] * scalar;
		transformedVec[ 3 ] = 1;
		
		return transformedVec;
	}
	
	private float[] normalizeVec( float[] vec )
	{
		float[] transformedVec = new float[ 4 ];
		float length = 	(float) Math.pow( 	vec[ 0 ] * vec[ 0 ] + 
											vec[ 1 ] * vec[ 1 ] + 
											vec[ 2 ] * vec[ 0 ],
											1/3 ); 
				
		transformedVec[ 0 ] = vec[ 0 ] / length;
		transformedVec[ 1 ] = vec[ 1 ] / length;
		transformedVec[ 2 ] = vec[ 2 ] / length;
		transformedVec[ 3 ] = 1;
		
		return transformedVec;
	}

	public float[] getEndRotation()
	{
		return null;
	}
	
	public float[] getEndPosition()
	{
		if( m_parent != null )
		{
			float[] up = { 0, 1, 0, 1 };
			float[] transformedVec = new float[ 4 ];
			
			Matrix.multiplyMV( transformedVec, 0, m_restTransform, 0, up, 0 );
			
			transformedVec = normalizeVec( transformedVec );
			transformedVec = multVec( transformedVec, m_length );
			
			return addVec( m_parent.getEndPosition(), transformedVec );
		}
		else
		{
			float[] up = { 0, 0, 0, 1 };
			return up;
		}
	}
	
	public void draw( GL10 gl )
	{
//		float[] startVert = null;
//		if( m_parent != null )
//		{
//			startVert = m_parent.getEndPosition();
//		}
//		else
//		{
//			startVert = new float[ 4 ];
//			startVert[ 0 ] = startVert[ 1 ] = startVert[ 2 ] = 0.0f;
//			startVert[ 3 ] = 1.0f; 
//		}
//		
//		float[] endVert = null;
//		endVert = this.getEndPosition();
//		
//		float[] boneVertices = { 	startVert[ 0 ],
//									startVert[ 1 ],
//									startVert[ 2 ],
//									startVert[ 3 ],
//									endVert[ 0 ],
//									endVert[ 1 ],
//									endVert[ 2 ],
//									endVert[ 3 ]};
//		
//        ByteBuffer vbb = ByteBuffer.allocateDirect( boneVertices.length * 4 );
//        vbb.order( ByteOrder.nativeOrder() );
//        FloatBuffer mVertexBuffer = vbb.asFloatBuffer();
//        mVertexBuffer.put( boneVertices );
//        mVertexBuffer.position( 0 );
//
//        byte[] indices = { 0, 1 };
//        
//        ByteBuffer mIndexBuffer = ByteBuffer.allocateDirect( indices.length );
//        mIndexBuffer.put( indices );
//        mIndexBuffer.position( 0 );
//
//       
//        gl.glVertexPointer( 4, 
//        					GL10.GL_FLOAT, 
//        					0, 
//        					mVertexBuffer );
//        
//        gl.glLineWidth( 2 );
//        if( m_parent != null )
//        	gl.glColor4f( 0, 0, 0, 1 );
//        else
//        	gl.glColor4f( 1, 0, 0, 1 );
//        
//        gl.glDrawElements( 	GL10.GL_LINES, 
//        					2, 
//        					GL10.GL_UNSIGNED_BYTE, 
//        					mIndexBuffer );
//        
//        Iterator< MartianBone > iter = m_children.iterator();
//        while( iter.hasNext() )
//        {
//        	MartianBone child = iter.next();
//        	child.draw( gl );
//        }
//	        
//		if( m_parent == null )
//		{
//        gl.glPushMatrix();
//	        gl.glRotatef( 	m_rotations[3], // angle
//	        				m_rotations[0], // x
//	        				m_rotations[1], // y
//	        				m_rotations[2] );// z
//	        
//	        
//	        float[] up = { 0, 1, 0, 1 };
//			float[] transformedVec = up;//new float[ 4 ];
//			
//			transformedVec = multVec( up, m_length );	        
//	        
//			float[] boneVertices = { 	0.f,
//										0.f,
//										0.f,
//										1.f,
//										transformedVec[ 0 ],
//										transformedVec[ 1 ],
//										transformedVec[ 2 ],
//										transformedVec[ 3 ]};
//			
//	        ByteBuffer vbb = ByteBuffer.allocateDirect( boneVertices.length * 4 );
//	        vbb.order( ByteOrder.nativeOrder() );
//	        FloatBuffer mVertexBuffer = vbb.asFloatBuffer();
//	        mVertexBuffer.put( boneVertices );
//	        mVertexBuffer.position( 0 );
//
//	        byte[] indices = { 0, 1 };
//	        
//	        ByteBuffer mIndexBuffer = ByteBuffer.allocateDirect( indices.length );
//	        mIndexBuffer.put( indices );
//	        mIndexBuffer.position( 0 );
//
//	       
//	        gl.glVertexPointer( 4, 
//	        					GL10.GL_FLOAT, 
//	        					0, 
//	        					mVertexBuffer );
//	        
//	        gl.glLineWidth( 2 );
//	        
//	        gl.glColor4f( 	m_color[0], 
//	        				m_color[1], 
//	        				m_color[2],
//	        				m_color[3] );
//	        
//	        gl.glDrawElements( 	GL10.GL_LINES, 
//	        					2, 
//	        					GL10.GL_UNSIGNED_BYTE, 
//	        					mIndexBuffer );
//	        
//
//	        gl.glTranslatef(0.0f, m_length, 0);
//	        
//	        Iterator< MartianBone > iter = m_children.iterator();
//	        while( iter.hasNext() )
//	        {
//	        	MartianBone child = iter.next();
//	        	child.draw( gl );
//	        }
//        gl.glPopMatrix();
//	}
	}
	
}
