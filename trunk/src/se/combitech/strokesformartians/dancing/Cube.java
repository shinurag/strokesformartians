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
    private FloatBuffer   mVertexBuffer;
    private FloatBuffer   mTexCoordBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
    private int one = 90;
    private float vertices[] = {
    		// Bottom
            -one, -one, -one,
             one, -one, -one,
             one, -one,  one,
             
            -one, -one, -one,
             one, -one,  one,
            -one, -one,  one,

    		 // Right
             one, -one,  one,
             one, -one, -one,
             one,  one, -one,
            
             one, -one,  one,
             one,  one, -one,
             one,  one,  one,
            
             // Back
             one, -one, -one,
            -one, -one, -one,
            -one,  one, -one,
            
             one, -one, -one,
            -one,  one, -one,
             one,  one, -one,

             // Left 
            -one,  one,  one,
            -one,  one, -one,
            -one, -one, -one,
            
            -one,  one,  one,
            -one, -one, -one,
            -one, -one,  one,

             // Front
             one,  one,  one,
            -one, -one,  one,
             one, -one,  one,
            
             one,  one,  one,
            -one,  one,  one,
            -one, -one,  one,
             
             // Top
             one,  one,  one,
             one,  one, -one,
            -one,  one, -one,
            
             one,  one,  one,
            -one,  one, -one,
            -one,  one,  one,
             

             
    };
    
    private float normals[] = {
            0, -1,  0,
            0, -1,  0,
            0, -1,  0,
            0, -1,  0,
            0, -1,  0,
            0, -1,  0,
            1,  0,  0,
            1,  0,  0,
            1,  0,  0,
            1,  0,  0,
            1,  0,  0,
            1,  0,  0,
            0,  1,  0,
            0,  1,  0,
            0,  1,  0,
            0,  1,  0,
            0,  1,  0,
            0,  1,  0,
            -1,  0,  0,
            -1,  0,  0,
            -1,  0,  0,
            -1,  0,  0,
            -1,  0,  0,
            -1,  0,  0,
            0,  0,  1,
            0,  0,  1,
            0,  0,  1,
            0,  0,  1,
            0,  0,  1,
            0,  0,  1,
            0,  0, -1,
            0,  0, -1,
            0,  0, -1,
            0,  0, -1,
            0,  0, -1,
            0,  0, -1,
    };
    
    private float texCoords[] = {
    		0.25f, 0.34f,  0.50f, 0.34f,  0.50f, 0.00f,    0.25f, 0.34f,  0.50f, 0.00f,  0.25f, 0.00f, // bottom
    		0.75f, 0.34f,  0.50f, 0.34f,  0.50f, 0.66f,    0.75f, 0.34f,  0.50f, 0.66f,  0.75f, 0.66f, // right
    		0.50f, 0.34f,  0.25f, 0.34f,  0.25f, 0.66f,    0.50f, 0.34f,  0.25f, 0.66f,  0.50f, 0.66f, // back
    		0.00f, 0.66f,  0.25f, 0.66f,  0.25f, 0.34f,    0.00f, 0.66f,  0.25f, 0.34f,  0.00f, 0.34f, // left
    		0.75f, 0.66f,  1.00f, 0.34f,  0.75f, 0.34f,    0.75f, 0.66f,  1.00f, 0.66f,  1.00f, 0.34f, // front 
    		0.50f, 1.00f,  0.50f, 0.66f,  0.25f, 0.66f,    0.50f, 1.00f,  0.25f, 0.66f,  0.25f, 1.00f, // top
    };
    
    private int colors[] = {
            0,    0,    0,  one,
            one,    0,    0,  one,
            one,  one,    0,  one,
            0,  one,    0,  one,
            0,    0,  one,  one,
            one,    0,  one,  one,
            one,  one,  one,  one,
            0,  one,  one,  one,
    };

    private byte indices[] = {
            0,1,2, 3,4,5,
            6,7,8, 9,10,11,
            12,13,14, 15,16,17,
            18,19,20, 21,22,23,
            24,25,26, 27,28,29,
            30,31,32, 33,34,35,
    };
    
    public Cube()
    {

        // Buffers to be passed to gl*Pointer() functions
        // must be direct, i.e., they must be placed on the
        // native heap where the garbage collector cannot
        // move them.
        //
        // Buffers with multi-byte datatypes (e.g., short, int, float)
        // must have their byte order set to native order

//        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
//        vbb.order(ByteOrder.nativeOrder());
//        mVertexBuffer = vbb.asFloatBuffer();
    	mVertexBuffer = FloatBuffer.allocate( vertices.length * 4 );
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);
        
        FloatBuffer tbb = FloatBuffer.allocate( texCoords.length*4 );
//        tbb.order(ByteOrder.nativeOrder());
//        mTexCoordBuffer = tbb.asFloatBuffer();
        mTexCoordBuffer = tbb;
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
        
		gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
//		gl.glEnableClientState( GL10.GL_NORMAL_ARRAY );
		gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );

    	gl.glVertexPointer( 3, gl.GL_FLOAT, 0, FloatBuffer.wrap( vertices ) );
        gl.glTexCoordPointer( 2, gl.GL_FLOAT, 0,  FloatBuffer.wrap( texCoords ) );
//        gl.glNormalPointer( gl.GL_FLOAT, 0, FloatBuffer.wrap( normals ) );
        
        gl.glDrawElements( gl.GL_TRIANGLES, 36, gl.GL_UNSIGNED_BYTE, ByteBuffer.wrap( indices ) );
        
		gl.glDisableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
//		gl.glDisableClientState( GL10.GL_NORMAL_ARRAY );
		gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );
    }
}