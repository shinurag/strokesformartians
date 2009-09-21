package se.combitech.strokesformartians.dancing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * A vertex shaded cube.
 */
class Cube
{
    public Cube()
    {
        int one = 1;
        float vertices[] = {
                -one, -one, -one,
                one, -one, -one,
                one,  one, -one,
                -one,  one, -one,
                -one, -one,  one,
                one, -one,  one,
                one,  one,  one,
                -one,  one,  one,
        };

        float texCoords[] = {
        		0.25f, 0.00f,  0.50f, 0.33f,  0.25f,0.33f,    0.25f, 0.00f,  0.50f, 0.33f,  0.50f, 0.00f,
        		0f,0f, 0f,0f, 0f,0f,  0f,0f, 0f,0f, 0f,0f,

        		0f,0f, 0f,0f, 0f,0f,  0f,0f, 0f,0f, 0f,0f,
        		0f,0f, 0f,0f, 0f,0f,  0f,0f, 0f,0f, 0f,0f,

        		0f,0f, 0f,0f, 0f,0f,  0f,0f, 0f,0f, 0f,0f,
        		0f,0f, 0f,0f, 0f,0f,  0f,0f, 0f,0f, 0f,0f

//        		0.75f, 0.33f,  0.50f, 0.33f,  0.50f,0.66f,    0.75f, 0.33f,  0.50f, 0.66f,  0.75f, 0.66f,
//        		0.50f, 1.00f,  0.50f, 0.66f,  0.25f,0.66f,    0.50f, 1.00f,  0.25f, 0.66f,  0.25f, 1.00f,
//        		
//        		0.00f, 0.66f,  0.25f, 0.66f,  0.25f,0.33f,    0.00f, 0.66f,  0.25f, 0.33f,  0.00f, 0.33f,
//        		0.25f, 0.33f,  0.25f, 0.66f,  0.50f,0.66f,    0.25f, 0.33f,  0.50f, 0.66f,  0.50f, 0.33f,
//        		1.00f, 0.66f,  1.00f, 0.33f,  0.75f,0.33f,    1.00f, 0.66f,  0.75f, 0.33f,  0.75f, 0.66f
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

        byte indices[] = {
                0, 4, 5,    0, 5, 1,
                1, 5, 6,    1, 6, 2,
                2, 6, 7,    2, 7, 3,
                3, 7, 4,    3, 4, 0,
                4, 7, 6,    4, 6, 5,
                3, 0, 1,    3, 1, 2
        };

        // Buffers to be passed to gl*Pointer() functions
        // must be direct, i.e., they must be placed on the
        // native heap where the garbage collector cannot
        // move them.
        //
        // Buffers with multi-byte datatypes (e.g., short, int, float)
        // must have their byte order set to native order

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);
        
        ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length*4);
        tbb.order(ByteOrder.nativeOrder());
        mTexCoordBuffer = tbb.asFloatBuffer();
        mTexCoordBuffer.put(texCoords);
        mTexCoordBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asIntBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    public void draw( GL10 gl )
    {
        gl.glFrontFace( gl.GL_CW );
        gl.glVertexPointer( 3, gl.GL_FLOAT, 0, mVertexBuffer );
        gl.glColorPointer( 4, gl.GL_FIXED, 0, mColorBuffer );
        gl.glTexCoordPointer( 36, gl.GL_FLOAT, 0, mTexCoordBuffer );
        
		gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
		gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
        
        gl.glDrawElements( gl.GL_TRIANGLES, 36, gl.GL_UNSIGNED_BYTE, mIndexBuffer );
        
		gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );
		gl.glDisableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
        
    }

    private FloatBuffer   mVertexBuffer;
    private FloatBuffer   mTexCoordBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
}