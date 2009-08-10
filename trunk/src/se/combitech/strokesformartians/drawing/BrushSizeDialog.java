package se.combitech.strokesformartians.drawing;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class BrushSizeDialog extends Dialog{
	
	private OnSeekBarChangeListener onSeekBarChangeListener;
	
    private static class SizePickerView extends View {
    	
    	private OnSeekBarChangeListener mListener;
    	
    	public SizePickerView(Context c, OnSeekBarChangeListener l, float s) {
    		super(c);
    		mListener = l;
    		
    		final float MAX_SIZE = 32.0f;
    		final float MIN_SIZE = 6.0f;
    		
    		SeekBar seekBar = new SeekBar(this.getContext());
    	}
    }
	
	public BrushSizeDialog(Context context, OnSeekBarChangeListener scListener, float size) {
		super(context);
		// TODO Auto-generated constructor stub
	}

}
