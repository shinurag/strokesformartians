package se.combitech.strokesformartians.dancing;

import java.util.HashMap;
import se.combitech.strokesformartians.*;
import android.opengl.*;

public class MartianAnimator 
{
	private class VertexWeight
	{
		public Leroy3.Bone [] bone;
		public float [] weight;

		public VertexWeight(String bone0, String bone1, float weight0, float weight1)
		{
			assert bone0.length() > 0;

			bone = new Leroy3.Bone[2];
			
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
	
	public Leroy3 leroy;
	
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
	
	public static float fatness = 4;
	public static float leglength = 3;
	public static float armlength = 2;
	public static float headlength = 1f;
	
	/** @TODO change these values */
//	public static float textureBottom = 0;
//	public static float textureLeft = 0;
//	public static float textureTop = 1;
//	public static float textureRight = 1;
	

	public MartianAnimator()
	{
		leroy = new Leroy3();

		boneVertexBuffer = new float[ 16 * 3 ];
		boneIndexBuffer = new byte[ 30 ];
		
		generateSkeleton();

		numVertices = 29;
		numTriangles = 27;
		numIndices = numTriangles * 3;  
		
		texCoordBuffer= new float[numVertices * 2];
		vertexBuffer = new float[numVertices * 3];
		
		generateOutlineImproved();
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

		vertexWeights[0] = new VertexWeight("upper_leg_right", "upper_leg_left", 0.5f, 0.5f);
		
		// right
		vertexWeights[1] = new VertexWeight("upper_leg_right", "lower_leg_right", 0.5f, 0.5f);
		vertexWeights[2] = new VertexWeight("lower_leg_right", "", 1, 0);
		vertexWeights[3] = new VertexWeight("lower_leg_right", "", 1, 0); 
		vertexWeights[4] = new VertexWeight("upper_leg_right", "lower_leg_right", 0.5f, 0.5f);
		vertexWeights[5] = new VertexWeight("upper_leg_right", "pelvic_right", 0.5f, 0.5f);
		vertexWeights[6] = new VertexWeight("root", "spine", 0.5f, 0.5f);
		vertexWeights[7] = new VertexWeight("collar_right", "upper_arm_right", 0.1f, 0.9f);
		vertexWeights[8] = new VertexWeight("lower_arm_right", "upper_arm_right", 0.5f, 0.5f);
		vertexWeights[9] = new VertexWeight("lower_arm_right", "", 1.0f, 0.0f);
		vertexWeights[10] = new VertexWeight("lower_arm_right", "", 1.0f, 0.0f);
		vertexWeights[11] = new VertexWeight("lower_arm_right", "upper_arm_right", 0.5f, 0.5f);
		vertexWeights[12] = new VertexWeight("collar_right", "upper_arm_right", 0.5f, 0.5f);
		vertexWeights[13] = new VertexWeight("collar_right", "spine", 0.2f, 0.8f);
		vertexWeights[14] = new VertexWeight("neck", "", 1.0f, 0.0f);

		// left
		vertexWeights[28] = new VertexWeight("upper_leg_left", "lower_leg_left", 0.5f, 0.5f);
		vertexWeights[27] = new VertexWeight("lower_leg_left", "", 1, 0);
		vertexWeights[26] = new VertexWeight("lower_leg_left", "", 1, 0); 
		vertexWeights[25] = new VertexWeight("upper_leg_left", "lower_leg_left", 0.5f, 0.5f);
		vertexWeights[24] = new VertexWeight("upper_leg_left", "pelvic_left", 0.5f, 0.5f);
		vertexWeights[23] = new VertexWeight("root", "spine", 0.5f, 0.5f);
		vertexWeights[22] = new VertexWeight("collar_left", "upper_arm_left", 0.1f, 0.9f);
		vertexWeights[21] = new VertexWeight("lower_arm_left", "upper_arm_left", 0.5f, 0.5f);
		vertexWeights[20] = new VertexWeight("lower_arm_left", "", 1.0f, 0.0f);
		vertexWeights[19] = new VertexWeight("lower_arm_left", "", 1.0f, 0.0f);
		vertexWeights[18] = new VertexWeight("lower_arm_left", "upper_arm_left", 0.5f, 0.5f);
		vertexWeights[17] = new VertexWeight("collar_left", "upper_arm_left", 0.5f, 0.5f);
		vertexWeights[16] = new VertexWeight("collar_left", "spine", 0.2f, 0.8f);
		vertexWeights[15] = new VertexWeight("neck", "", 1.0f, 0.0f);
	
	}

	/**
	 * Generate the vertices for the outline.
	 */
	private void generateOutline()
	{
		int index = 0;
	
		// 0
		vertexBuffer[index++] = leroy.bones.get("root").restPose[12];
		vertexBuffer[index++] = leroy.bones.get("root").restPose[13] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("root").restPose[14];
		
		// 1
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[14];
	
		// 2
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[13] - leglength;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[14];

		// 3
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[13] - leglength;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[14];
		
		// 4
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[14];
		
		// 5
		vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[12] - 0.06f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[14];
		
		// 6
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[12] - 0.2f * fatness;
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[14];		

		// 7
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[12] + 0.1f;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[13] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[14];				

		// 8
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[14];						

		// 9
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[13] - armlength;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[14];	
		
		// 10
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[13] - armlength;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[14];		
		
		// 11
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[14];				

		// 12
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[12] - 0.05f;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[13] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[14];		

		// 13
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[13] + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[14];				

		// 14
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[12] - 0.1f * fatness + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[13] + headlength;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[14];	

		// 15
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[12] + 0.1f * fatness - 0.05f;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[13] + headlength;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[14];		
		
		// 16
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[13] + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("neck").restPose[14];
		
		// 17
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[12] + 0.05f;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[13] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[14];		
		
		// 18
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[14];			
		
		// 19
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[13] - armlength;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[14];			
		
		// 20
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[13] - armlength;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[14];	
		
		// 21
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[14];
		
		// 22
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[12] - 0.1f;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[13] - 0.09f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[14];
		
		// 23
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[12] + 0.2f * fatness;
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("spine").restPose[14];		
		
		// 24
		vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[12] + 0.06f * fatness;
		vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[14];		
		
		// 25
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[14];		
		
		// 26
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[12] + 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[13] - leglength;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[14];		
		
		// 27
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[13] - leglength;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[14];		
		
		// 28
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[12] - 0.1f * fatness;
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[13];
		vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[14];		
	}

    /**
     * Generate the vertices for the outline in an improved way
     */
    private void generateOutlineImproved()
    {
        int index = 0;
    
        // 0
        vertexBuffer[index++] = leroy.bones.get("root").restPose[12];
        vertexBuffer[index++] = leroy.bones.get("root").restPose[13] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("root").restPose[14];
        
        // 1
        vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[14];
    
        // 2
        vertexBuffer[index++] = leroy.bones.get("foot_right").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("foot_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("foot_right").restPose[14];

        // 3
        vertexBuffer[index++] = leroy.bones.get("foot_right").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("foot_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("foot_right").restPose[14];
        
        // 4
        vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("lower_leg_right").restPose[14];
        
        // 5
        vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[12] - 0.06f * fatness;
        vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("upper_leg_right").restPose[14];
        
        // 6
        vertexBuffer[index++] = leroy.bones.get("spine").restPose[12] - 0.2f * fatness;
        vertexBuffer[index++] = leroy.bones.get("spine").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("spine").restPose[14];      

        // 7
        vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[12] + 0.1f;
        vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[13] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[14];                

        // 8
        vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[14];                        

        // 9
        vertexBuffer[index++] = leroy.bones.get("hand_right").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("hand_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("hand_right").restPose[14];    
        
        // 10
        vertexBuffer[index++] = leroy.bones.get("hand_right").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("hand_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("hand_right").restPose[14];        
        
        // 11
        vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("lower_arm_right").restPose[14];                

        // 12
        vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[12] - 0.05f;
        vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[13] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("upper_arm_right").restPose[14];        

        // 13
        vertexBuffer[index++] = leroy.bones.get("neck").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("neck").restPose[13] + 0.05f;
        vertexBuffer[index++] = leroy.bones.get("neck").restPose[14];               

        // 14
        vertexBuffer[index++] = leroy.bones.get("head").restPose[12] - 0.1f * fatness + 0.05f;
        vertexBuffer[index++] = leroy.bones.get("head").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("head").restPose[14];   

        // 15
        vertexBuffer[index++] = leroy.bones.get("head").restPose[12] + 0.1f * fatness - 0.05f;
        vertexBuffer[index++] = leroy.bones.get("head").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("head").restPose[14];       
        
        // 16
        vertexBuffer[index++] = leroy.bones.get("neck").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("neck").restPose[13] + 0.05f;
        vertexBuffer[index++] = leroy.bones.get("neck").restPose[14];
        
        // 17
        vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[12] + 0.05f;
        vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[13] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[14];     
        
        // 18
        vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[14];         
        
        // 19
        vertexBuffer[index++] = leroy.bones.get("hand_left").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("hand_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("hand_left").restPose[14];         
        
        // 20
        vertexBuffer[index++] = leroy.bones.get("hand_left").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("hand_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("hand_left").restPose[14]; 
        
        // 21
        vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("lower_arm_left").restPose[14];
        
        // 22
        vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[12] - 0.1f;
        vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[13] - 0.09f * fatness;
        vertexBuffer[index++] = leroy.bones.get("upper_arm_left").restPose[14];
        
        // 23
        vertexBuffer[index++] = leroy.bones.get("spine").restPose[12] + 0.2f * fatness;
        vertexBuffer[index++] = leroy.bones.get("spine").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("spine").restPose[14];      
        
        // 24
        vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[12] + 0.06f * fatness;
        vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("upper_leg_left").restPose[14];     
        
        // 25
        vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[14];     
        
        // 26
        vertexBuffer[index++] = leroy.bones.get("foot_left").restPose[12] + 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("foot_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("foot_left").restPose[14];     
        
        // 27
        vertexBuffer[index++] = leroy.bones.get("foot_left").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("foot_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("foot_left").restPose[14];     
        
        // 28
        vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[12] - 0.1f * fatness;
        vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[13];
        vertexBuffer[index++] = leroy.bones.get("lower_leg_left").restPose[14];     
    }
	
	
	/**
	 * Generate texture coordinates using values from the outline.
	 */
	private void generateTextureCoordinates()
	{
		float textureLeft = Float.MAX_VALUE;
		float textureRight = Float.MIN_VALUE;
		float textureBottom = Float.MAX_VALUE;
		float textureTop = Float.MIN_VALUE;

		// calculate minmax values
		for(int loop0 = 0; loop0 < numVertices; ++loop0)
		{
			if(vertexBuffer[loop0 * 3] < textureLeft) textureLeft = vertexBuffer[loop0 * 3];
			if(vertexBuffer[loop0 * 3] > textureRight) textureRight = vertexBuffer[loop0 * 3];
			if(vertexBuffer[loop0 * 3 + 1] < textureBottom) textureBottom = vertexBuffer[loop0 * 3 + 1];
			if(vertexBuffer[loop0 * 3 + 1] > textureTop) textureTop = vertexBuffer[loop0 * 3 + 1];
		}
		
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
		boneVertexBuffer[index++] = leroy.bones.get( "root" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "root" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "root" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "spine" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "spine" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "spine" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "neck" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "neck" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "neck" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "collar_left" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "collar_left" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "collar_left" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_left" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_left" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_left" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_left" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_left" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_left" ).restPose[14];
		
		boneVertexBuffer[index++] = leroy.bones.get( "collar_right" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "collar_right" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "collar_right" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_right" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_right" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_arm_right" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_right" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_right" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_arm_right" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_left" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_left" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_left" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_left" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_left" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_left" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_left" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_left" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_left" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_right" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_right" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "pelvic_right" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_right" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_right" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "upper_leg_right" ).restPose[14];

		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_right" ).restPose[12];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_right" ).restPose[13];
		boneVertexBuffer[index++] = leroy.bones.get( "lower_leg_right" ).restPose[14];
		
		
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
		float [] tmpVertex0 = new float[] {0,0,0};
		float [] tmpVertex1 = new float[] {0,0,0};
		
		int intframe;
		
		// pingpong frame
		intframe = (int)frame;
		intframe = (intframe / leroy.numFrames) % 2 == 0 ? intframe % leroy.numFrames : (leroy.numFrames - 1) - (intframe % leroy.numFrames);
		
		//// sin frame
		//intframe = (int)((Math.sin(frame / 5) + 1) / 2 * 30);
		//// clamp
		//intframe = intframe > 29 ? 29 : intframe < 0 ? 0 : intframe;		
		
		for(int vertex = 0; vertex < numVertices; ++vertex)
		{		
			if(vertexWeights[vertex].bone[0] != null)
				getTransformedVertex(tmpVertex0, 0, vertex, vertexWeights[vertex].bone[0], intframe);
			
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
	private void getTransformedVertex(float [] output, int outputOffset, int vertexNum, Leroy3.Bone bone, int frame)
	{
		float [] tmpdata = new float[4];
		float [] tmpdata2 = new float[4];
		float [] tmpdata3 = new float[4];
		// get the vertex position relative to the bone, this can be optimized by calculating it only once
        /** @TODO optimize */
		System.arraycopy(vertexBuffer, vertexNum * 3, tmpdata3, 0, 3);
		tmpdata3[3] = 1;
		Matrix.multiplyMV(tmpdata, 0, bone.restPoseInverse, 0, tmpdata3, 0);
		
		// transform using bone transformation
		Matrix.multiplyMV(tmpdata2, 0, bone.frames, (frame << 4), tmpdata, 0);
		
		// add the bone rest pose
//		for(int loop0 = 0; loop0 < 3; ++loop0)
//		{
//			tmpdata2[loop0] += bone.restPose[loop0];
//		}		
		
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
