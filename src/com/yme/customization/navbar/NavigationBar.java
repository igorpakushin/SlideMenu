package com.yme.customization.navbar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yme.R;


public class NavigationBar extends RelativeLayout {

    private View leftPane = null;
    private View rightPane = null;
    private TextView titleTextView = null;
    private Button backButton = null;
    private iNavigationBarCallback delegate;

    private Context context;


    public  NavigationBar(Context context) {
        super(context);
        this.context = context;
    }

    public  NavigationBar(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
    }

    public  NavigationBar(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        this.context = context;
    }

	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		leftPane = findViewById(R.id.leftPane);
		rightPane = findViewById(R.id.rightPane);
		titleTextView = (TextView)findViewById(R.id.titleTextView);
	}

    // getters/setters
	public CharSequence getTitleText() {
		return titleTextView.getText();
	}

	public void setTitleText(String text) {
		titleTextView.setText(text);
	}

    public iNavigationBarCallback getDelegate() {
        return delegate;
    }

    public void setDelegate(iNavigationBarCallback delegate) {
        this.delegate = delegate;
    }

    public ViewGroup getLeftPane() {
        return (ViewGroup) leftPane;
    }

    public ViewGroup getRightPane() {
        return (ViewGroup) rightPane;
    }

    // back button
    private void allocateBackButton() {
        if (backButton == null) {
            backButton = (Button)LayoutInflater.from(context).inflate(R.layout.ios_black_back_button, null);
            backButton.setText("Back");

            ((ViewGroup)leftPane).addView(backButton);
        }
    }

    public void createBackButton(OnClickListener onClickListener) {
        allocateBackButton();
        backButton.setOnClickListener(onClickListener);
    }

    public void createBackButton() {
        allocateBackButton();
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delegate != null)
                    delegate.onNavigationBarBackClick(backButton);
            }
        });
    }

    // custom button
    public Button createCustomButton(String text, OnClickListener onClickListener) {
        Button btn = (Button) LayoutInflater.from(context).inflate(R.layout.ios_black_button, null);
        btn.setText(text);
        btn.setOnClickListener(onClickListener);
        return btn;
    }

}
