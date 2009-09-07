package se.combitech.strokesformartians.drawing;

import android.app.Dialog;
import android.content.Context;
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
	private OnBrushSizeChangeListener mListener;
	
	private float mInitialSize = 12.0f;
	
    public interface OnBrushSizeChangeListener {
        void onBrushSizeChange(float size);
    }
    
	private static class SizePickerView extends View {
    	
    	private OnBrushSizeChangeListener mListener;
    	
    	public SizePickerView(Context c, OnBrushSizeChangeListener l, float s) {
    		super(c);
    		mListener = l;
    	}
    }
	
	public BrushSizeDialog(Context context, OnBrushSizeChangeListener bsListener, float size) {
		super(context);
		mListener = bsListener;
		mInitialSize = size;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBrushSizeChangeListener l = new OnBrushSizeChangeListener() {
			public void onBrushSizeChange(float size) {
				mListener.onBrushSizeChange(size);
				dismiss();
			}
        };

        
		Button submitButton = new Button(getContext());
		submitButton.setText("Select");
        
        
        //LinearLayout layout = new LinearLayout(getContext());
        //layout.setLayoutParams(new
        //LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
        //LinearLayout.LayoutParams.WRAP_CONTENT));
        //layout.setMinimumWidth(400);
        //layout.setPadding(20, 20, 20, 20);
        
		LinearLayout myLayout = new LinearLayout(getContext());
		myLayout.setMinimumWidth(200);        
		myLayout.setPadding(20, 0, 20, 20);
		
//		myLayout.setGravity(Gravity.TOP);
		myLayout.setGravity(Gravity.FILL_VERTICAL);
		myLayout.setLayoutParams(new LinearLayout.LayoutParams(300,36));
		myLayout.setOrientation(LinearLayout.VERTICAL);
		
		//myLayout.setLayoutParams(new 
		//		LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT));
		
		Button helloButton = new Button(getContext());
		helloButton.setTag("Ok");
		
		SeekBar seekBar = new SeekBar(getContext());
        seekBar.setMax(32);
        seekBar.setLayoutParams(new
        ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT));
        seekBar.setProgress(0); //
        seekBar.setProgress(5);
		myLayout.addView(seekBar);
		myLayout.addView(helloButton);
		myLayout.bringChildToFront(seekBar);
		
		//setContentView(myLayout);
		setContentView(myLayout);
		setTitle("Pick a brush size, fool!");
        //setContentView(new SizePickerView(getContext(), l, mInitialSize));
        //setTitle("Pick a Brush Size!");
    }

}
