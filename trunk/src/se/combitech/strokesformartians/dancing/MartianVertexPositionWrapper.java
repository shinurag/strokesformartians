package se.combitech.strokesformartians.dancing;

import android.opengl.Matrix;

public class MartianVertexPositionWrapper {

	public float m_weight = 0.0f;
	public MartianBone m_bone = null;
	public float[] m_relativeTransform = new float[16];
	
	MartianVertexPositionWrapper()
	{
		Matrix.setIdentityM( m_relativeTransform, 0 );
	}
	
}
