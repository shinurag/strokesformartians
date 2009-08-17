#!/usr/bin/env python

# this is a script for exporting skeleton data from blender to a java class representation

import bpy
import Blender

def skeletonStub():
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

def createBones(bones):
    for bone in bones:
        """
        print something like:
        bones.put(" + bone.name + ", new Bone())
        """
        pass

def boneStub():
    """
    prints out to file something like:
    public class Bone
    {
    	public String name;
        public Matrix restPose;
        public [] Matrix frames;

        public Bone()
        {
    """
    pass

def printMatrix(name, frame, matrix):
    """
    prints something like:
    bones.getValue(name).frames[frame].set(0,0) = " + matrix.get(0,0)
    ...
    """

def export():
    armature = Object.Get("Armature")

    for frame in xrange(1,30):
        armature.evaluatePose(frame)
        pose = armature.getPose()
        for bone in pose.bones.values():
            printMatrix(bone.name, frame, bone.poseMatrix)
            
            
