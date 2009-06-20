package se.combitech.strokesformartians.dancing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * A vertex shaded cube.
 */
class MartianModel
{
    public MartianModel()
    {
    	int one = 255;
        int vertices[] = {   0, 3, 0,
        					 0, 1, 0,
        					 1, 1, 0,
        					 2, 0, 0,
        					 3,-1, 0,
        					 3,-3, 0,
        					 3,-6, 0,
        					 0,-3, 0,
        					-1, 1, 0,
        					-2, 0, 0,
        					-3,-1, 0,
        					-3,-3, 0,
        					-3,-6, 0,
        					};

        int colors[] = {
                0,    0,    0,  one,
                one,    0,    0,  one,
                one,  one,    0,  one,
                0,  one,    0,  one,
                0,    0,  one,  one,
                one,    0,  one,  one,
                one,  one,  one,  one,
                0,  one,  one,  one,
        };

        byte indices[] = {	0,1,
        					1,2,
        					2,3,
        					3,4,
        					1,7,
        					7,5,
        					5,6,
        					1,8,
        					8,9,
        					9,10,
        					7,11,
        					11,12 };

        // Buffers to be passed to gl*Pointer() functions
        // must be direct, i.e., they must be placed on the
        // native heap where the garbage collector cannot
        // move them.
        //
        // Buffers with multi-byte datatypes (e.g., short, int, float)
        // must have their byte order set to native order

        ByteBuffer vbb = ByteBuffer.allocateDirect( vertices.length*4 );
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asIntBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect( colors.length*4 );
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asIntBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect( indices.length );
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    public void draw(GL10 gl)
    {
    	gl.glScalef(60000, 60000, 60000);
        //gl.glFrontFace( gl.GL_CW );
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        //gl.glColorPointer(4, gl.GL_FIXED, 0, mColorBuffer);
        gl.glLineWidth( 2 );
        gl.glColor4f(0, 0, 0, 1);
        gl.glDrawElements(GL10.GL_LINES, 24, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
    }

    private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
}