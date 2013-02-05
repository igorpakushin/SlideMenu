package com.yme.customization;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yme.R;

import java.text.AttributedCharacterIterator;


public class NavigationBar extends RelativeLayout {

	View leftPane = null; 
	View rightPane = null;
	TextView titleTextView = null;
	

    public  NavigationBar(Context context) {
        super(context);
    }

    public  NavigationBar(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public  NavigationBar(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		leftPane = findViewById(R.id.leftPane);
		rightPane = findViewById(R.id.rightPane);
		titleTextView = (TextView)findViewById(R.id.titleTextView);
	}
	
//	public TextView getTitleTextView() {
//		return titleTextView;
//	}
//
	public void setTitleText(String text) {
		titleTextView.setText(text);
	}
}
