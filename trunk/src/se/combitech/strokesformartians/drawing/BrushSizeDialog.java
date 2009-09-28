package se.combitech.strokesformartians.drawing;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class BrushSizeDialog extends Dialog{
	final float MAX_SIZE = 50.0f;
	final float MIN_SIZE = 12.0f;	
	float currentSize = 20.0f;
	private android.view.View.OnClickListener mClickListener;
//	private OnClickListener mClickListener;
	private OnSeekBarChangeListener mListener;
	private View mySizePickerView;
	
	private float mInitialSize = 20.0f;
	
	private SeekBar seekBar;
	
    public interface OnBrushSizeChangeListener {
        void onBrushSizeChange(float size);
    }
    
    public interface mClickListener {
    	void onClick();
    }

	public static class SizePickerView extends View {
    	
		private BrushSizeDialog dialogParent = (BrushSizeDialog)this.getParent();
    	private OnSeekBarChangeListener mListener;
    	private Paint mPaint;
    	private float mSize = 20.0f;
    	private BrushSizeDialog mCallback;
    	
    	SizePickerView(Context c, OnSeekBarChangeListener listener, float s) {
    		super(c);
    		mListener = listener;
    	}    	
    	
    	public void updateSize(float size) {	
    		mSize = size;
    	}
    	
    	public float getSize() {
    		return mSize;
    	}
        
    	@Override
    	protected void onDraw(Canvas canvas) {
    	    super.onDraw(canvas);
    	    
    	    // Draw the circle
        	mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        	mPaint.setStyle(Paint.Style.FILL);
        	mPaint.setColor(Color.YELLOW);
        	mPaint.setColor(0xFFFFB90F);
            
    	    canvas.drawCircle(100, 50, mSize/2, mPaint);
    	    
    	}
    	
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(200, 100);
        }
        
        /**       
    	@Override    	
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    
                	break;
                case MotionEvent.ACTION_MOVE:                    
//                	mSize = mCallback.getSeekBarValue();
//                    invalidate();
                    
                    break;
                case MotionEvent.ACTION_UP:                    
                    break;
            }
            return true;
        }
    	**/
        
    	public void setCallback(BrushSizeDialog c)
    	{
    		mCallback = c;
    	}
    	
    }

	public BrushSizeDialog(Context context, android.view.View.OnClickListener clickListener, float size) {	
		super(context);
		mClickListener = clickListener;
		mInitialSize = size;
		
		seekBar = new SeekBar(context);
	}

	public float getSeekBarValue() {
		return (float)seekBar.getProgress();
	}
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        OnSeekBarChangeListener mListener = new OnSeekBarChangeListener() {

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				currentSize = getSeekBarValue();
				((SizePickerView)mySizePickerView).updateSize(currentSize);
            	mySizePickerView.invalidate();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
        	
        };
        
        OnClickListener mButtonListener = new OnClickListener() {
        	
        	// This does not work.
        	public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();				
			}
        	
        };
        
		seekBar.setOnSeekBarChangeListener(mListener);
		
        mySizePickerView = new SizePickerView(getContext(), mListener, currentSize);
        ((SizePickerView)mySizePickerView).setCallback(this);
        
		LinearLayout myLayout = new LinearLayout(getContext());
		myLayout.setMinimumWidth(200);      
		
		myLayout.setPadding(20, 0, 20, 20);
		myLayout.setOrientation(LinearLayout.VERTICAL);
		myLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT));
		
		
        seekBar.setMax((int)MAX_SIZE);
        seekBar.setLayoutParams(new
        		ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
        		ViewGroup.LayoutParams.WRAP_CONTENT));
        seekBar.setProgress((int)mInitialSize);
		
        myLayout.addView(seekBar);
		myLayout.bringChildToFront(seekBar);
		
		Button okButton = new Button(getContext());
		okButton.setText("Ok");
		okButton.setOnClickListener(mClickListener);
		
		/** Uncomment this line when the button works..
		 * 
		 * 
		myLayout.addView(okButton);		
		 *
		**/
		myLayout.addView(mySizePickerView);
		setContentView(myLayout);
		
//		setTitle("Pick a brush size");        
    }

}
