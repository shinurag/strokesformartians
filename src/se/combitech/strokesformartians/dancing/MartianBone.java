package se.combitech.strokesformartians.dancing;

import android.opengl.Matrix;

public class MartianBone 
{
	private float[] m_transform;
	private float m_length;
	private MartianBone m_parent;

	MartianBone( )
	{
		this( null );
	}
	
	MartianBone( MartianBone parent )
	{
		m_transform = new float[16];
		Matrix.setIdentityM( m_transform, 0 );
		m_length = 0.0f;
		m_parent = parent;
	}
	
	
}
