package se.combitech.strokesformartians.dancing;

import java.util.HashMap;
import se.combitech.strokesformartians.Leroy2;
import se.combitech.strokesformartians.Leroy2.Bone;
import android.opengl.*;

public class MartianAnimator 
{
	private class VertexWeight
	{
		public Leroy2.Bone [] bone;
		public float [] weight;
		
		public VertexWeight(String bone0, String bone1, float weight0, float weight1)
		{
			assert bone0.length() > 0;

			bone = new Leroy2.Bone[2];
		
			if(bone1.length() > 0)
			{
				assert weight1 != 0;
				bone[1] = leroy.bones.get(bone1);
			}
			
			bone[0] = leroy.bones.get(bone0);
			
			weight = new float[ 2 ];
			weight[0] = weight0;		
			weight[1] = weight1;		
		}
	}
	
	public Leroy2 leroy;
	
	// stupid bone stuff
	public float[] boneVertexBuffer;
	public byte[] boneIndexBuffer;
	
	// cool outline stuff
	public float [] texCoordBuffer;
	public float [] vertexBuffer;
	public byte [] indexBuffer;
	VertexWeight [] vertexWeights;	
	
	/** @TODO these are redundant, remove!! */
	public int numVertices;
	public int numIndices;
	public int numTriangles;
	
	public static float fatness = 1;
	public static float leglength = 1;
	public static float armlength = 1;
	public static float headlength = 0.5f;
	
	/** @TODO change these values */
	public static float textureBottom = 0;
	public static float textureLeft = 0;
	public static float textureTop = 1;
	public static float textureRight = 1;
	

	public MartianAnimator()
	{
		leroy = new Leroy2();

		boneVertexBuffer = new float[ 16 * 3 ];
		boneIndexBuffer = new byte[ 30 ];
		
		generateSkeleton();

		numVertices = 29;
		numTriangles = 27;
		numIndices = numTriangles * 3;  
		
		texCoordBuffer= new float[numVertices * 2];
		vertexBuffer = new float[numVertices * 3];
		
		generateOutline();
		generateTextureCoordinates();
		generateIndices();
		generateVertexWeight();
	}
	
	/**
	 * Generate the vertex weights for the outline.
	 */
	private void generateVertexWeight()
	{
		vertexWeights = new VertexWeight[ numVertices ];

		vertexWeights[0] = new VertexWeight("root", "", 1, 0);
		
		// right
		vertexWeights[1] = new VertexWeight("upper_leg_right", "lower_leg_right", 0.9f, 0.1f);
		vertexWeights[2] = new VertexWeight("lower_leg_right", "", 1, 0);
		vertexWeights[3] = new VertexWeight("lower_leg_right", "", 1, 0); 
		vertexWeights[4] = new VertexWeight("upper_leg_right", "lower_leg_right", 0.9f, 0.1f);
		vertexWeights[5] = new VertexWeight("upper_leg_right", "root", 0.9f, 0.1f);
		vertexWeights[6] = new VertexWeight("root", "spine", 0.5f, 0.5f);
		vertexWeights[7] = new VertexWeight("collar_right", "upper_arm_right", 0.7f, 0.3f);
		vertexWeights[8] = new VertexWeight("lower_arm_right", "upper_arm_right", 0.5f, 0.5f);
		vertexWeights[9] = new VertexWeight("lower_arm_right", "", 1.0f, 0.0f);
		vertexWeights[10] = new VertexWeight("lower_arm_right", "", 1.0f, 0.0f);
		vertexWeights[11] = new VertexWeight("lower_arm_right", "upper_arm_right", 0.5f, 0.5f);
		vertexWeights[12] = new VertexWeight("collar_right", "upper_arm_right", 0.5f, 0.5f);
		vertexWeights[13] = new VertexWeight("collar_right", "spine", 0.7f, 0.3f);
		vertexWeights[14] = new VertexWeight("neck", "", 1.0f, 0.0f);

		// left
		vertexWeights[28] = new VertexWeight("upper_leg_left", "lower_leg_left", 0.9f, 0.1f);
		vertexWeights[27] = new VertexWeight("lower_leg_left", "", 1, 0);
		vertexWeights[26] = new VertexWeight("lower_leg_left", "", 1, 0); 
		vertexWeights[25] = new VertexWeight("upper_leg_left", "lower_leg_left", 0.9f, 0.1f);
		vertexWeights[24] = new VertexWeight("upper_leg_left", "root", 0.9f, 0.1f);
		vertexWeights[23] = new VertexWeight("root", "spine", 0.5f, 0.5f);
		vertexWeights[22] = new VertexWeight("collar_left", "upper_arm_left", 0.7f, 0.3f);
		vertexWeights[21] = new VertexWeight("lower_arm_left", "upper_arm_left", 0.5f, 0.5f);
		vertexWeights[20] = new VertexWeight("lower_arm_left", "", 1.0f, 0.0f);
		vertexWeights[19] = new VertexWeight("lower_arm_left", "", 1.0f, 0.0f);
		vertexWeights[18] = new VertexWeight("lower_arm_left", "upper_arm_left", 0.5f, 0.5f);
		vertexWeights[17] = new VertexWeight("collar_left", "upper_arm_left", 0.5f, 0.5f);
		vertexWeights[16] = new VertexWeight("collar_left", "spine", 0.7f, 0.3f);
		vertexWeights[15] = new VertexWeight("neck", "", 1.0f, 0.0f);
	
	}

	/**
	 * Generate the vertices for the outline.
	 */
	private void generateOutline()
	{
		int index = 0;
	
		// 0
		vertexBuffer[index++] = leroy.bones.get("root").restPose[0];
		vertexBuffer[index++] = leroy.bones.get("root").restPose[1] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("root").restPose[2];
		
		// 1
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[2];
	
		// 2
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[1] - leglength;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[2];

		// 3
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[1] - leglength;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[2];
		
		// 4
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[2];
		
		// 5
		vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[2];
		
		// 6
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[0] - 0.4f * fatness;
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[2];		

		// 7
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[0] + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[1] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[2];				

		// 8
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[2];						

		// 9
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[1] - armlength;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[2];	
		
		// 10
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[1] - armlength;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[2];		
		
		// 11
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[2];				

		// 12
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[0] - 0.05f;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[1] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[2];		

		// 13
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[1] + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[2];				

		// 14
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[0] - 0.1f * fatness + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[1] + headlength;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[2];	

		// 15
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[0] + 0.1f * fatness - 0.05f;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[1] + headlength;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[2];		
		
		// 16
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[1] + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[2];
		
		// 17
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[0] + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[1] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[2];		
		
		// 18
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];			
		
		// 19
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1] - armlength;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];			
		
		// 20
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1] - armlength;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];	
		
		// 21
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];
		
		// 22
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[0] - 0.05f;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[1] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[2];
		
		// 23
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[0] + 0.4f * fatness;
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[2];		
		
		// 24
		vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[2];		
		
		// 25
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[2];		
		
		// 26
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[0] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[1] - leglength;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[2];		
		
		// 27
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[1] - leglength;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[2];		
		
		// 28
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[0] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[1];
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[2];		
	}
	
	/**
	 * Generate texture coordinates using values from the outline.
	 */
	private void generateTextureCoordinates()
	{
		float width = textureRight - textureLeft;
		float height = textureTop - textureBottom;
		
		assert width > 0;
		assert height > 0;
		
		for(int loop0 = 0; loop0 < numVertices; ++loop0)
		{
			// do some normalization on the x and y coordinates from the outline
			texCoordBuffer[loop0 * 2] = (vertexBuffer[loop0 * 3] - textureLeft) / width;
			texCoordBuffer[loop0 * 2 + 1] = (vertexBuffer[loop0 * 3 + 1] - textureBottom) / height;
		}
	}
	
	/**
	 * Generate indices for the outline
	 * Not using triangle strips currently.
	 */
	private void generateIndices()
	{
		indexBuffer = new byte[] {2,4,3, 2,1,4, 4,1,0, 0,5,4, 0,6,5, 0,23,6, 0, 24,23, 0,25,24, 0,28,25, 28,27,25, 27,26,25, 6,23,7, 7,23,22, 7,22,16, 7,16,13, 7,13,12, 7,12,8, 8,12,11, 8,11,10, 8,10,9, 13,16,14, 16,15,14, 16,22,17, 22,21,17, 21,18,17, 21,20,19, 21,19,18}; 
	
		assert indexBuffer.length == numIndices;
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
		/*assert frame>=0;
		assert frame<leroy.numFrames;*7
		
		/** @TODO could quite easily support a variable number of bones here */
		float [] tmpVertex0 = new float[3];
		float [] tmpVertex1 = new float[3];
		
		int intframe = (int)frame;
		
		// mod frame
		intframe = intframe % 30;
		
		for(int vertex = 0; vertex < numVertices; ++vertex)
		{		
			if(vertexWeights[vertex].bone[0] != null)
				getTransformedVertex(tmpVertex0, 0, vertex, vertexWeights[vertex].bone[0], intframe);
			/** @TODO array out of bounds exception here, why? */
			if(vertexWeights[vertex].bone[1] != null)
				getTransformedVertex(tmpVertex1, 0, vertex, vertexWeights[vertex].bone[1], intframe);
			
			for(int loop0 = 0; loop0 < 3; ++loop0)
			{
				int vertexStart = 3 * vertex;
				vertices[vertexStart + loop0] = tmpVertex0[loop0] * vertexWeights[vertex].weight[0];
				vertices[vertexStart + loop0] += tmpVertex1[loop0] * vertexWeights[vertex].weight[1];
			}
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
	 * @param outputOffset where in output to start storing the output, not currently used 
	 * @param vertexNum which vertex to transform
	 * @param bone the bone to use for the transform
	 * @param frame which frame to use
	 */
	private void getTransformedVertex(float [] output, int outputOffset, int vertexNum, Leroy2.Bone bone, int frame)
	{
		float [] tmpdata = new float[4];
		float [] tmpdata2 = new float[4];
		// get the vertex position relative to the bone, this can be optimized by calculating it only once
		for(int loop0 = 0; loop0 < 3; ++loop0)
		{
			tmpdata[loop0] = vertexBuffer[vertexNum * 3 + loop0] - bone.restPose[loop0];
		}
		
		// transform using bone transformation
		Matrix.multiplyMV(tmpdata2, 0, bone.frames, (frame << 4), tmpdata, 0);
		
		// add the bone rest pose
		for(int loop0 = 0; loop0 < 3; ++loop0)
		{
			tmpdata2[loop0] += bone.restPose[loop0];
		}		
		
		// copy the first three values
		System.arraycopy(tmpdata2, 0, output, outputOffset, 3);
	}
	
	public int getVertexBufferLength()
	{
		return vertexBuffer.length;
	}
	
	public int getIndexBufferLength()
	{
		return indexBuffer.length;
	}
	
	public int getTexCoordBufferLength()
	{
		return texCoordBuffer.length;
	}
}
