package se.combitech.strokesformartians.dancing;

public class MartianMesh implements IMartianMesh
{
	private float[] m_vertices = { 	-1, -1,  0,  1,
									 1, -1,  0,  1,
									 1,  1,  0,  1,
									-1,  1,  0,  1 };
	
	private float[] m_textureCoordinates = {	0, 0,
												1, 0,
												1, 1,
												0, 1 };
	private byte[] m_indices = {	0, 1, 2,
									0, 2, 3 };

	
	public MartianMesh( )
	{
	}
	
	public float[] getVertices() throws Exception
	{
		if( m_vertices.length > 0 )
		{
			return m_vertices;
		}
		else
		{
			throw new Exception( "Missing vertices!" );
		}
	}
	
	public float[] getTextureCoordinates() throws Exception
	{
		if( m_textureCoordinates.length > 0 )
		{
			return m_textureCoordinates;
		}
		else
		{
			throw new Exception( "Missing texture coordinates!" );
		}
	}
	
	public byte[] getIndices() throws Exception
	{
		if( m_indices.length > 0 )
		{
			return m_indices;
		}
		else
		{
			throw new Exception( "Missing indices!" );
		}
	}

}
