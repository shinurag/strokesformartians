package se.combitech.strokesformartians.dancing;

import java.util.HashMap;
import se.combitech.strokesformartians.Leroy2;
import se.combitech.strokesformartians.Leroy2.Bone;
import android.opengl.*;

public class MartianAnimator 
{
	public Leroy2 leroy;
	private float[] boneVertexBuffer;
	private byte[] boneIndexBuffer;
	private int numVertices;
	byte [] indexBuffer;
	float [] texCoordBuffer;
	
	private class VertexWeight
	{
		public Leroy2.Bone bone0;
		public Leroy2.Bone bone1;
		
		public float weight0;
		public float weight1;
	}
	
	VertexWeight [] vertexWeights;
	
	public MartianAnimator( float[] vertexBuffer, byte[] indexBuffer)
	{
		boneVertexBuffer = vertexBuffer;
		boneIndexBuffer = indexBuffer;
		leroy = new Leroy2();
		generateSkeleton();
	}
	
	private void generateSkeleton()
	{
		int index = 0;
		boneVertexBuffer[index++] = leroy.bones.get( "root" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "root" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "root" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "spine" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "spine" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "spine" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "neck" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "neck" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "neck" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "collar_left" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "collar_left" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "collar_left" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_left" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_left" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_left" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_left" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_left" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_left" ).restPose[2];
		
		boneVertexBuffer[index++] = leroy.bones.get( "collar_right" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "collar_right" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "collar_right" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_right" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_right" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_right" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_right" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_right" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_right" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_left" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_left" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_left" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_left" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_left" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_left" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_left" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_left" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_left" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_right" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_right" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_right" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_right" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_right" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_right" ).restPose[2];

		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_right" ).restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_right" ).restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_right" ).restPose[2];
		
		
		index = 0;
		boneIndexBuffer[ index++ ] = 0;
		boneIndexBuffer[ index++ ] = 1;
		
		boneIndexBuffer[ index++ ] = 1;
		boneIndexBuffer[ index++ ] = 2;
		
		boneIndexBuffer[ index++ ] = 2;
		boneIndexBuffer[ index++ ] = 3;
		
		boneIndexBuffer[ index++ ] = 2;
		boneIndexBuffer[ index++ ] = 4;
		
		boneIndexBuffer[ index++ ] = 4;
		boneIndexBuffer[ index++ ] = 5;
		
		boneIndexBuffer[ index++ ] = 5;
		boneIndexBuffer[ index++ ] = 6;
		
		boneIndexBuffer[ index++ ] = 2;
		boneIndexBuffer[ index++ ] = 7;
		
		boneIndexBuffer[ index++ ] = 7;
		boneIndexBuffer[ index++ ] = 8;

		boneIndexBuffer[ index++ ] = 8;
		boneIndexBuffer[ index++ ] = 9;
		
		boneIndexBuffer[ index++ ] = 0;
		boneIndexBuffer[ index++ ] = 10;
		
		boneIndexBuffer[ index++ ] = 10;
		boneIndexBuffer[ index++ ] = 11;
		
		boneIndexBuffer[ index++ ] = 11;
		boneIndexBuffer[ index++ ] = 12;
		
		boneIndexBuffer[ index++ ] = 0;
		boneIndexBuffer[ index++ ] = 13;
		
		boneIndexBuffer[ index++ ] = 13;
		boneIndexBuffer[ index++ ] = 14;
		
		boneIndexBuffer[ index++ ] = 14;
		boneIndexBuffer[ index++ ] = 15;
	}
	
	public void something()
	{
		
	}
	
	public void getSkeletonFrame( 	float frame, 
									float[] vertexBuffer,
									byte[] indexBuffer )
	{
		vertexBuffer = boneVertexBuffer;
		indexBuffer = boneIndexBuffer;
	}
	
	/**
	 * Get a mesh from the animation
	 * @param frame Which frame to get
	 * @param vertices[out] Where the vertices will be stored
	 * @param texCoords[out] Where the texture coordinates will be stored
	 * @param indices[out] Where the indices will be stored
	 */
	public void getFrame( 	float frame, 
							float [] vertices, 
							float [] texCoords, 
							byte [] indices)
	{
		assert frame>=0;
		assert frame<=leroy.numFrames;
		
		int intframe = (int)frame;
		
		for(int vertex = 0; vertex < numVertices; ++vertex)
		{		
			// @todo weight the second bone as well
			getTransformedVertex(vertices, vertex * 3, vertex, vertexWeights[vertex].bone0, intframe);
		}	
		
		// always use indices from indexBuffer
		System.arraycopy(indexBuffer,0,indices,0,indexBuffer.length);
		
		// always use texture coordinates from texCoordBuffer
		System.arraycopy(texCoordBuffer,0,texCoords,0,texCoordBuffer.length);
	}
	
	/**
	 * Poo!
	 * 
	 * @param[out] output where to place the transformed vertices
	 * @param outputOffset where in output to start storing the output 
	 * @param vertexNum which vertex to transform
	 * @param bone the bone to use for the transform
	 * @param frame which frame to use
	 */
	private void getTransformedVertex(float [] output, int outputOffset, int vertexNum, Leroy2.Bone bone, int frame)
	{
		// get the vertex position relative to the bone, this can be optimized by calculating it only once
		for(int loop0 = 0; loop0 < 3; ++loop0)
		{
			output[loop0] = boneVertexBuffer[vertexNum * 3 + loop0] - bone.restPose[loop0];
		}
		
		// transform using bone transformation
		Matrix.multiplyMV(output, 0, bone.frames, (frame << 4), output, 0);
		
		// add the bone rest pose
		for(int loop0 = 0; loop0 < 3; ++loop0)
		{
			output[loop0] += bone.restPose[loop0];
		}		
	}
}
