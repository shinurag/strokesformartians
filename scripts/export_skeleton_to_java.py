#!/usr/bin/env python

# this is a script for exporting skeleton data from blender to a java class representation

import bpy
import Blender

def fileStart(out):
    """
    print the file header
    """
    
    out.write("""
/* evil bone representation made by funky bone exporter */
""")

def skeletonStub(out):
    """
    prints something like:
    public class Skeleton
    {
    	public Map<String, Bone> bones;
        public int numFrames;

        public Skeleton()
        {
    """
    pass

def createBones(out, bones):
    for bone in bones:
        """
        print something like:
        bones.put(" + bone.name + ", new Bone())
        """
        pass

def boneStart(out):
    """
    output the start of the bone class
    """
    out.write(
        """
    public class Bone
    {
    	public String name;
        public float [] restPose;
        public float [][] frames;
    }
    """)

def printMatrix(out, name, frame, matrix):
    """
    prints something like:
    bones.getValue(name).frames[frame].set(0,0) = " + matrix.get(0,0)
    ...
    """

def export(filename):
    """
    todo: save restpose somehow
    """

    outfile = file(filename, "w")

    armature = Object.Get("Armature")

    
    # start printing
    fileStart(outfile)
    boneStub(outfile)

    for frame in xrange(1,30):
        armature.evaluatePose(frame)
        pose = armature.getPose()
        for bone in pose.bones.values():
            printMatrix(bone.name, frame, bone.poseMatrix)
            
            

if __name__ == "__main__":
    Blender.Window.FileSelector(export, "Export funky bone stuff")

