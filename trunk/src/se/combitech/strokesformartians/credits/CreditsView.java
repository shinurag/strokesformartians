package se.combitech.strokesformartians.credits;

import se.combitech.strokesformartians.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CreditsView extends View implements Runnable {

	private int mScrollPosition = -30;
	private int mHeight = 0; 
	private final String[] mCreditsText = {"Henrik \"freebase\"" ,
											"Joel \"hash-map\"",
											"Richard \"svampen\"",
											"Mike \"silen\"",
											"Hong \"fimpen\"",
											"Lina \"the supplier\"",
											"Jimmy \"speedy\"" };
	
	public CreditsView(Context context) {
		super(context);

	}
	
	public CreditsView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}
	
	public CreditsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	@Override
	public void draw( Canvas canvas ) {
		if( mHeight == 0 )
		{
			mHeight = canvas.getHeight();
		}
		canvas.drawARGB(255, 0, 0, 0);
		
		Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG );
		paint.setARGB(255, 200, 200, 200);
		paint.setTextSize( 20 );
		
		int index;
		for( index = 0; index < mCreditsText.length; index++ )
		{
			canvas.drawText( mCreditsText[index], 20, (mHeight - mScrollPosition) + index * 24, paint);
		}
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo );
		canvas.drawBitmap( bitmap, 10, (mHeight - mScrollPosition) + ( index + 1 ) * 24, paint );
	}

	public void run() 
	{
		while( true )
		{
			if( mHeight != 0 && mScrollPosition + 40 < mHeight )
			{
				mScrollPosition++;
				this.postInvalidate();
			}
			
			try {
				Thread.sleep( 10 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
