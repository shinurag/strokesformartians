package se.combitech.strokesformartians.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

public class MyDrawableView extends View {
	private ShapeDrawable shapeDrawable;
	
	public MyDrawableView(Context context) {
		super(context);
		
		shapeDrawable = new ShapeDrawable(new RectShape());
		shapeDrawable.getPaint().setColor(Color.CYAN);
		shapeDrawable.setBounds(10, 10, 100, 100);
	}
	
	protected void onDraw(Canvas canvas) {
        shapeDrawable.draw(canvas);
    }

}
