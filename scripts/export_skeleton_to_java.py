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
        

def createBones(out, bones):
    for bone in bones:
        """
        print something like:
        bones.put(" + bone.name + ", new Bone())
        """
        pass

def printBoneClass(out):
    """
    output the start of the bone class
    """
    out.write(
        """
    class Bone
    {
    	public String name;
	/**
	 * The coordinates in worldspace that defines the restpose for the bone. On the format restPose[index] where index is 0 for x and 1 for y. It only specify the head of the bone. 
	 */
        public float [] restPose;
	/**
	 * Transformation matrix for the bone on the format frames[framenumber * 16 + matrixindice] where matrixindice is a value from 0 to 15 specifying which element in a column major 4x4 matrix to use.
	 */ 
        public float [] frames;

	public Bone(String name)
	{
		this.name = name;
	}
    }
    """)

def printMatrix(out, name, frame, matrix):
    """
    prints something like:
    bones.getValue(name).frames[frame].set(0,0) = " + matrix.get(0,0)
    ...
    """
    out.write("\nbone = bones.getValue(\"{name}\");\n".format(name=name))
    
    tmatrix = matrix.transpose()
    for (x,col) in enumerate(tmatrix):
        for (y,elem) in enumerate(col):
            framestart = frame * 16
            out.write("bone.frames[{index}] = {value};\n".format(index = framestart + x * 4 + y, value = elem))


def export(filename):
    """
    todo: save restpose somehow
    """

    outfile = file(filename, "w")

    armature = Object.Get("Armature")

    def filecontent(out):
        printBoneClass(outfile)

        numFrames = 30

        def skeletoncontent(out):
            # allocate the bones
            for name in (bone.name for bone in armature.getPose().bones.values()):
                out.write("bone = new Bone(\"{name}\");\n".format(name=name))
                out.write("bones.put(\"{name}\", bone);\n".format(name = name))
                out.write("bone.restPose = new float[3];\n")
                out.write("bone.frames = new float[{size}];\n".format(size = 16 * numFrames))


            # output rest poses here
            for bone in armature.getData().bones.values():
                out.write("bone = bones.get(\"{name}\");\n".format(name=bone.name))
                # TODO: tail instead of head here?
                out.write("bone.restPose[{index}] = {value};\n".format(index = 0, value = bone.head["ARMATURESPACE"][0]))
                out.write("bone.restPose[{index}] = {value};\n".format(index = 0, value = bone.head["ARMATURESPACE"][1]))
                out.write("bone.restPose[{index}] = {value};\n".format(index = 0, value = bone.head["ARMATURESPACE"][2]))

            for frame in xrange(1,numFrames):
                armature.evaluatePose(frame)
                pose = armature.getPose()
                for bone in pose.bones.values():
                    printMatrix(out, bone.name, frame, bone.poseMatrix)

        classname = os.path.basename(filename)
        classname = classname[:classname.rindex(".")]
        printSkeleton(outfile, classname,numFrames, skeletoncontent)
        

    # start printing
    printFile(outfile, filecontent)
            
            

if __name__ == "__main__":
    Blender.Window.FileSelector(export, "Export funky bone stuff")

