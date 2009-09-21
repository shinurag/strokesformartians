
/* evil bone representation made by funky bone exporter */
package se.combitech.strokesformartians;

import java.util.HashMap;

public class Leroy2
{
public class Bone
    {
    	public String name;
	/**
	 * The coordinates in armaturespace that defines the restpose for the bone. A 4x4 matrix.
	 */
        public float [] restPose;

        /**
         * The inverse of the restpose transform
         */
        public float [] restPoseInverse;
	/**
	 * Transformation matrix for the bone on the format frames[framenumber * 16 + matrixindice] where matrixindice is a value from 0 to 15 specifying which element in a column major 4x4 matrix to use.
	 */ 
        public float [] frames;

	public Bone(String name)
	{
		this.name = name;
	}
    }

public HashMap<String, Bone> bones;
        public int numFrames;

        public Leroy2()
        {
numFrames = 30;
bones = new HashMap<String, Bone>();

Bone bone;
bone = new Bone("lower_arm_right");
bones.put("lower_arm_right", bone);
