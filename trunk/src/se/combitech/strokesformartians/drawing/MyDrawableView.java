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

public class MyDrawableView extends View {
	private ShapeDrawable shapeDrawable;
	public Bitmap bitmap;
	
	public MyDrawableView(Context context) {
		super(context);
		
		shapeDrawable = new ShapeDrawable(new RectShape());
		shapeDrawable.getPaint().setColor(Color.GRAY);
		shapeDrawable.setBounds(1, 1, 200, 250);
		
		bitmap = Bitmap.createBitmap(200, 250, Config.ARGB_4444);
	}
	
	protected void onDraw(Canvas canvas) {
        shapeDrawable.draw(canvas);
       
        int x = 0;
        int y = 0;

        Paint paint = new Paint();
        
        paint.setColor(Color.GREEN);
        paint.setStyle(Style.FILL);
        //canvas.drawCircle(50, 100, 20, paint);
        
        canvas.drawBitmap(bitmap, 1, 1, paint);
    }
	
	public boolean onTouchEvent(MotionEvent me) {
		bitmap.setPixel((int)me.getX(), (int)me.getY(), Color.BLUE);
		return super.onTouchEvent(me); 
	}
}
