package se.combitech.strokesformartians.dancing;

import java.util.HashMap;
import java.nio.*;

import se.combitech.strokesformartians.Leroy2;
import se.combitech.strokesformartians.Leroy2.Bone;

public class MartianAnimator 
{
	private Leroy2 leroy;
	private float[] boneVertexBuffer;
	
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
	}
	
	public void something()
	{
		
	}
	
	public void getSkeletonFrame( 	float frame, 
									float[] vertexBuffer )
	{
		vertexBuffer = boneVertexBuffer;
	}
	
	/**
	 * Get a mesh from the animation
	 * @param frame Which frame to get
	 * @param vertices[out] Where the vertices will be stored
	 * @param texCoords[out] Where the texture coordinates will be stored
	 * @param indices[out] Where the indices will be stored
	 */
	public void getFrame( 	float frame, 
							FloatBuffer vertices, 
							FloatBuffer texCoords, 
							ShortBuffer indices)
	{
	
	}
}
