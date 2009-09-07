package se.combitech.strokesformartians.drawing;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

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

		SeekBar seekBar = new SeekBar(getContext());
		//seekBar.setMax((int)MAX_SIZE);
		seekBar.setMax(100);
		seekBar.setProgress((int)MAX_SIZE);
		seekBar.setMinimumWidth(100);
		
		Button submitButton = new Button(getContext());
		submitButton.setText("Select");
		
		LinearLayout myLayout = new LinearLayout(getContext());
		myLayout.addView(seekBar);
		myLayout.setMinimumWidth(300);
		
		
		
		setContentView(myLayout);        
        //setContentView(new SizePickerView(getContext(), l, mInitialSize));
        //setTitle("Pick a Brush Size!");
    }

}
