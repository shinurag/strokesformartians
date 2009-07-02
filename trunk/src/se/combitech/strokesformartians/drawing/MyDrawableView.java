package se.combitech.strokesformartians.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MyDrawableView extends View {
	static public Bitmap bitmap;
	private int mWidth = 0;
	private int mHeight = 0;
	
	public MyDrawableView(Context context) {
		this( context, 100, 100 );
	}
	
	public MyDrawableView(Context context, int width, int height ) {
		super(context);
	
		mWidth = width;
		mHeight = height;
		
		bitmap = Bitmap.createBitmap( width, height, Config.ARGB_8888 );
		this.setOnTouchListener( touchListener );
	}
	
	protected void onDraw(Canvas canvas) {
		canvas.drawARGB( 	255, 
							255, 
							255, 
							255 );
       
        int x = 0;
        int y = 0;

        Paint paint = new Paint();
        
        paint.setColor(Color.GREEN);
        paint.setStyle(Style.FILL);
        paint.setStrokeWidth( 4 );
        
        canvas.drawBitmap( bitmap, x, y, paint );
    }
	
    View.OnTouchListener touchListener = new View.OnTouchListener()
    {
		public synchronized boolean onTouch( View view, MotionEvent me )
		{
			if( me.getX() < mWidth
				&&
				me.getX() > 0
				&& 
				me.getY() < mHeight
				&&
				me.getY() > 0 
			){
				((MyDrawableView)view).bitmap.setPixel((int)me.getX(), (int)me.getY(), Color.BLUE);
				view.postInvalidate();
			}
			return true;
		}
    };	
	
}
