package se.combitech.strokesformartians.drawing;

import android.app.Dialog;
import android.content.Context;
import android.graphics.*;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class BrushSizeDialog extends Dialog{
	final float MAX_SIZE = 132.0f;
	final float MIN_SIZE = 6.0f;
//	private OnBrushSizeChangeListener mListener;
	
	private float mInitialSize = 12.0f;
	
    public interface OnBrushSizeChangeListener {
        void onBrushSizeChange(float size);
    }
//    
//	private final int r;
//    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    
	private static class SizePickerView extends View {
    	
    	private OnBrushSizeChangeListener mListener;
    	private Paint mPaint;
    	
//    	SizePickerView(Context c, OnBrushSizeChangeListener l, float s) {
    	SizePickerView(Context c) {	
    		super(c);
//    		mListener = l;

        	mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        	mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(32);
    	}    	
    	
        
    	@Override
    	protected void onDraw(Canvas canvas) {
    	    super.onDraw(canvas);
    	    canvas.setViewport(50, 70);
    	    canvas.drawColor(Color.RED);
    	    canvas.drawCircle(0, 0, 20, mPaint);
    	    
    	}
    }
//	
	public BrushSizeDialog(Context context, OnBrushSizeChangeListener bsListener, float size) {
		super(context);
		//mListener = bsListener;
		mInitialSize = size;
	}

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                      
//        OnBrushSizeChangeListener l = new OnBrushSizeChangeListener() {
//			public void onBrushSizeChange(float size) {
//				mListener.onBrushSizeChange(size);
//				dismiss();
//			}
//        };
       
		LinearLayout myLayout = new LinearLayout(getContext());
		myLayout.setMinimumWidth(200);      
		
		myLayout.setPadding(20, 0, 20, 20);
		//myLayout.setGravity(Gravity.FILL_VERTICAL);
		//myLayout.setLayoutParams(new LinearLayout.LayoutParams(300,36));
		myLayout.setOrientation(LinearLayout.VERTICAL);
		myLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT));
		
		
		SeekBar seekBar = new SeekBar(getContext());
        seekBar.setMax(32);
        seekBar.setLayoutParams(new
        		ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
        		ViewGroup.LayoutParams.WRAP_CONTENT));
        seekBar.setProgress((int)mInitialSize);
		
        myLayout.addView(seekBar);
		myLayout.bringChildToFront(seekBar);
		
		Button helloButton = new Button(getContext());
		helloButton.setText("Ok");
		myLayout.addView(helloButton);
		
		myLayout.addView(new SizePickerView(getContext()));
		//setContentView(myLayout);
		setContentView(new SizePickerView(getContext()));
		
		setTitle("Pick a brush size, fool!");        
    }
		
}
