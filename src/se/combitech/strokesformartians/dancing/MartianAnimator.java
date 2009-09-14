package se.combitech.strokesformartians.dancing;

import java.util.HashMap;
import se.combitech.strokesformartians.Leroy2;
import se.combitech.strokesformartians.Leroy2.Bone;
import android.opengl.*;

public class MartianAnimator 
{
	public Leroy2 leroy;
	public float[] boneVertexBuffer;
	public byte[] boneIndexBuffer;
	public int numVertices;
	public int numIndices;
	public float [] texCoordBuffer;
	
	public static float fatness = 1;
	public static float leglength = 1;
	public static float armlength = 1;
	public static float headlength = 0.5f;
	
	private class VertexWeight
	{
		public Leroy2.Bone [] bone;
		public float [] weight;
	}
	
	VertexWeight [] vertexWeights;
	
	public MartianAnimator()
	{
		leroy = new Leroy2();
		generateSkeleton();

		numVertices = 29;
		numIndices = 100; // change
		
		boneVertexBuffer = new float[numVertices * 3];
		boneIndexBuffer = new byte[numIndices];
		texCoordBuffer= new float[numVertices * 2];
		
		/** @todo add genereateOutline here */
	}
	
	private void generateOutline()
	{
		int index = 0;
	
		// 0
		boneVertexBuffer[index++] = leroy.bones.get("root").restPose[0];
		boneVertexBuffer[index++] = leroy.bones.get("root").restPose[1] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("root").restPose[2];
		
		// 1
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[2];
	
		// 2
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[1] - leglength;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[2];

		// 3
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[1] - leglength;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[2];
		
		// 4
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[2];
		
		// 5
		boneVertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[2];
		
		// 6
		boneVertexBuffer[index++] = leroy.bones.get("spine").restPose[0] - 0.4f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("spine").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("spine").restPose[2];		

		// 7
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[0] + 0.05f;
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[1] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[2];				

		// 8
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[2];						

		// 9
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[1] - armlength;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[2];	
		
		// 10
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[1] - armlength;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[2];		
		
		// 11
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[2];				

		// 12
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[0] - 0.05f;
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[1] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[2];		

		// 13
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[1] + 0.05f;
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[2];				

		// 14
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[0] - 0.1f * fatness + 0.05f;
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[1] + headlength;
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[2];	

		// 15
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[0] + 0.1f * fatness - 0.05f;
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[1] + headlength;
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[2];		
		
		// 16
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[1] + 0.05f;
		boneVertexBuffer[index++] = leroy.bones.get("neck").restPose[2];
		
		// 17
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[0] + 0.05f;
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[1] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[2];		
		
		// 18
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];			
		
		// 19
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1] - armlength;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];			
		
		// 20
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1] - armlength;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];	
		
		// 21
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];
		
		// 22
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] - 0.05f;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];
		
		// 23
		boneVertexBuffer[index++] = leroy.bones.get("spine").restPose[0] + 0.4f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("spine").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("spine").restPose[2];		
		
		// 24
		boneVertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[2];		
		
		// 25
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[2];		
		
		// 26
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[0] + 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[1] - leglength;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[2];		
		
		// 27
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[1] - leglength;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[2];		
		
		// 28
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[0] - 0.1f * fatness;
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[1];
		boneVertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[2];		
	}
	
	private void generateTextureCoordinates()
	{
		for(int loop0 = 0; loop0 < numVertices; ++loop0)
		{
			
		}
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
		
		/** @todo could quite easily support a variable number of bones here */
		float [] tmpVertex0 = new float[3];
		float [] tmpVertex1 = new float[3];
		
		int intframe = (int)frame;
		
		for(int vertex = 0; vertex < numVertices; ++vertex)
		{		
			// @todo weight the second bone as well
			getTransformedVertex(tmpVertex0, 0, vertex, vertexWeights[vertex].bone[0], intframe);
			getTransformedVertex(tmpVertex1, 0, vertex, vertexWeights[vertex].bone[0], intframe);
			
			for(int loop0 = 0; loop0 < 3; ++loop0)
			{
				int vertexStart = 3 * vertex;
				vertices[vertexStart + loop0] = tmpVertex0[loop0] * vertexWeights[vertex].weight[0];
				vertices[vertexStart + loop0] += tmpVertex1[loop0] * vertexWeights[vertex].weight[1];
			}
		}	
		
		// always use indices from indexBuffer
		System.arraycopy(boneIndexBuffer,0,indices,0,boneIndexBuffer.length);
		
		// always use texture coordinates from texCoordBuffer
		System.arraycopy(texCoordBuffer,0,texCoords,0,texCoordBuffer.length);
	}
	
	/**
	 * Poo!
	 * 
	 * @param[out] output where to place the transformed vertices
	 * @param outputOffset where in output to start storing the output, not currently used 
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
