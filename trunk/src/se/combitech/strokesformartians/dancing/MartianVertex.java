package se.combitech.strokesformartians.dancing;

import java.util.ArrayList;

import android.graphics.PointF;

public class MartianVertex 
{
	private ArrayList< MartianVertexPositionWrapper > m_vertices = null;
	private PointF m_textureCoordinate = null;	
	
	MartianVertex()
	{
		m_vertices  = new ArrayList< MartianVertexPositionWrapper >();
		m_textureCoordinate = new PointF( 0.f, 0.f );
	}
}
