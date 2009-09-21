#!BPY
"""
Name: 'Funky bone exporter'
Blender: 249
Group: 'Export'
Tooltip: 'Funky bone exporter'
"""

# this is a script for exporting skeleton data from blender to a java class representation

import bpy
import Blender
from Blender import *
import os.path

def printFile(out, content):
    """
    print the file header
    """
    
    out.write("""
/* evil bone representation made by funky bone exporter */
package se.combitech.strokesformartians;

import java.util.HashMap;
""")
    content(out)

def printSkeleton(out, classname, numframes, content):
    """
    print the start of the skeleton class
    
    content: a function that prints the content of the constructor
    """
    out.write(
        """
public class {classname}
{{
public class Bone
    {{
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
	{{
		this.name = name;
	}}
    }}

public HashMap<String, Bone> bones;
        public int numFrames;

        public {classname}()
        {{
numFrames = {numframes};
bones = new HashMap<String, Bone>();

Bone bone;
""".format(numframes=str(numframes), classname=classname))
    content(out)
    out.write(
        """
}
}
""")
        

# def printMatrix(out, name, frame, matrix):
#     """
#     prints something like:
#     bones.getValue(name).frames[frame].set(0,0) = " + matrix.get(0,0)
#     ...
#     """
#     out.write("\nbone = bones.get(\"{name}\");\n".format(name=name))
    
#     tmatrix = matrix.transpose()
#     for (x,col) in enumerate(tmatrix):
#         for (y,elem) in enumerate(col):
#             framestart = frame * 16
#             out.write("bone.frames[{index}] = {value};\n".format(index = framestart + x * 4 + y, value = getJavaFloat(elem)))

def getJavaFloat(value):
    return str(value) + "f"

def shuffleMatrix(matrix):
    # newmatrix = Blender.Mathutils.Matrix()

    # newmatrix[0] = matrix[0]
    # newmatrix[1] = matrix[2]
    # newmatrix[2] = matrix[1]
    # newmatrix[3] = matrix[3]

    # return newmatrix

    ## identity shuffle
    return matrix

def printMatrix(matrix):
    out = ''
    for col in matrix:
        for elem in col:
            out += getJavaFloat(elem) + ","

    return out[:-1] # remove last ,
    

def getBoneTransformFrames(armature,name,numframes):
    """
    print a whole bunch of data
    """
    out = ''

    for frame in xrange(numframes):
        armature.evaluatePose(frame)
        pose = armature.getPose()
        
        matrix = shuffleMatrix(pose.bones[name].poseMatrix)
        
        out += printMatrix(matrix) + ","
        
    return out[:-1] # remove last ,


def export(filename):
    """
    todo: save restpose somehow
    """

    outfile = file(filename, "w")

    armature = Object.Get("Armature")

    def filecontent(out):
        numFrames = 30

        def skeletoncontent(out):
            # allocate the bones
            for bone in armature.getData().bones.values():
                name = bone.name
                out.write("bone = new Bone(\"{name}\");\n".format(name=name))
                out.write("bones.put(\"{name}\", bone);\n".format(name = name))
                # out.write("bone.restPose = new float[]{{{vertex}}};\n".format(vertex = getJavaFloat(bone.head["ARMATURESPACE"][0]) + "," + \
                #                                                                   getJavaFloat(bone.head["ARMATURESPACE"][2]) + "," + \
                #                                                                   getJavaFloat(bone.head["ARMATURESPACE"][1])))
                out.write("bone.restPose = new float[]{{{restpose}}};\n".format(restpose = printMatrix(bone.matrix["ARMATURESPACE"])))
                out.write("bone.restPoseInverse = new float[]{{{restpose}}};\n".format(restpose = printMatrix(bone.matrix["ARMATURESPACE"].copy().invert())))
                out.write("bone.frames = new float[]{{{vertices}}};\n".format(vertices = getBoneTransformFrames(armature,name,numFrames)))

        classname = os.path.basename(filename)
        classname = classname[:classname.rindex(".")]
        printSkeleton(outfile, classname,numFrames, skeletoncontent)
        

    # start printing
    printFile(outfile, filecontent)
            
            

if __name__ == "__main__":
    Blender.Window.FileSelector(export, "Export funky bone stuff")

