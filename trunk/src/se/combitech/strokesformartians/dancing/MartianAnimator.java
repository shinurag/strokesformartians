package se.combitech.strokesformartians.dancing;

import java.util.HashMap;

import se.combitech.strokesformartians.Leroy2;
import se.combitech.strokesformartians.Leroy2.Bone;

public class MartianAnimator 
{
	private Leroy2 leroy = new Leroy2();
	private float[] boneVertexBuffer;
	private byte[] boneIndexBuffer;
	private int numVertices;
	byte [] indexBuffer;
	float [] texCoordBuffer;
	
	public MartianAnimator()
	{
		generateSkeleton();
	}
	
	private void generateSkeleton()
	{
		boneVertexBuffer = new float[ leroy.bones.size() * 2 ];
		
		int index = 0;
		boneVertexBuffer[index] = leroy.bones.get( "root" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "root" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "spine" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "spine" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "neck" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "neck" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "collar_left" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "collar_left" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "upper_arm_left" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "upper_arm_left" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "lower_arm_left" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "lower_arm_left" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "collar_right" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "collar_right" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "upper_arm_right" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "upper_arm_right" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "lower_arm_right" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "lower_arm_right" ).restPose[index++];
		
		boneVertexBuffer[index] = leroy.bones.get( "pelvic_left" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "pelvic_left" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "upper_leg_left" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "upper_leg_left" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "lower_leg_left" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "lower_leg_left" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "pelvic_right" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "pelvic_right" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "upper_leg_right" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "upper_leg_right" ).restPose[index++];

		boneVertexBuffer[index] = leroy.bones.get( "lower_leg_right" ).restPose[index++];
		boneVertexBuffer[index] = leroy.bones.get( "lower_leg_right" ).restPose[index++];
		
		
		boneIndexBuffer = new byte[30];
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
			
		}	
		
		// always use indices from indexBuffer
		System.arraycopy(indexBuffer,0,indices,0,indexBuffer.length);
		
		// always use texture coordinates from texCoordBuffer
		System.arraycopy(texCoordBuffer,0,texCoords,0,texCoordBuffer.length);
	}
	
	//private void getTransformedVertex(int vertexNum, 
}