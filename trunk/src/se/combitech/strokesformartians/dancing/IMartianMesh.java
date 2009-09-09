package se.combitech.strokesformartians.dancing;

public interface IMartianMesh 
{
	public float[] getVertices() throws Exception;
	
	public float[] getTextureCoordinates() throws Exception;
	
	public short[] getIndices() throws Exception;
}
