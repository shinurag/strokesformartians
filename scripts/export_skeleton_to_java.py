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

def printFile(out, content):
    """
    print the file header
    """
    
    out.write("""
/* evil bone representation made by funky bone exporter */
package se.combitech.strokesformartians;
""")
    content(out)

def printSkeleton(out, numframes, content):
    """
    print the start of the skeleton class
    
    content: a function that prints the content of the constructor
    """
    out.write(
        """
public class Skeleton
{{
public Map<String, Bone> bones;
        public int numFrames;

        public Skeleton()
        {{
numFrames = {numframes};
""".format(numframes=str(numframes)))
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
    public class Bone
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
    }
    """)

def printMatrix(out, name, frame, matrix):
    """
    prints something like:
    bones.getValue(name).frames[frame].set(0,0) = " + matrix.get(0,0)
    ...
    """
    pass

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
            for frame in xrange(1,numFrames):
                armature.evaluatePose(frame)
                pose = armature.getPose()
                for bone in pose.bones.values():
                    printMatrix(out, bone.name, frame, bone.poseMatrix)

        printSkeleton(outfile, numFrames, skeletoncontent)
        

    # start printing
    printFile(outfile, filecontent)
            
            

if __name__ == "__main__":
    Blender.Window.FileSelector(export, "Export funky bone stuff")

