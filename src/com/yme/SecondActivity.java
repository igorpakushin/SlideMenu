package com.yme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.yme.customization.navbar.INavigationBarCallback;
import com.yme.customization.navbar.NavigationBar;
import com.yme.customization.slidemenu.CustomHorizontalScrollView;

public class SecondActivity extends Activity implements INavigationBarCallback {
	
	private ListView listView;
    private NavigationBar navigationBar;
    private CustomHorizontalScrollView panesController;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_second);

		listView = (ListView) findViewById(R.id.list_view);
        navigationBar = (NavigationBar) findViewById(R.id.navigation_bar);
        panesController = (CustomHorizontalScrollView) findViewById(R.id.panes_scroll_view);

//        ViewGroup secondViewContainer = (ViewGroup) findViewById(R.id.second_view_container);
//        secondViewContainer.setLayoutParams(new LinearLayout.LayoutParams(480, LinearLayout.LayoutParams.MATCH_PARENT));

        initSidePane();

        initNavigationBar();
        initListContents();
	}

    private void initSidePane() {
        // these are percentages for views in the scrollview:
        //      1.0 means 100% of current device screen width
        //      0.8 means 80%
        float[] widths = {0.8f, 1.0f};

        // this forces children to size
        panesController.sizeViews(widths);

        // this sets current pane
        panesController.scrollToPaneIndex(1);
    }

    private void initListContents() {
        String[] values = new String[] {
                "Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
                "Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
                "Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
                "Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
                "Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
                "Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
                "Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
                "Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
        };

        MySecondViewArrayAdapter myadapter = new MySecondViewArrayAdapter(this, values);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, android.R.id.text1, values);
        listView.setAdapter(myadapter);
    }

    private void initNavigationBar() {
        navigationBar.setTitleText("Hello");
//        navigationBar.createBackButton(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                overridePendingBackTransitionAnimation();
//            }
//        });
        navigationBar.setDelegate(this);
        navigationBar.createBackButton();

        Button btn = navigationBar.createCustomButton("Menu", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panesController.scrollToPaneIndex(0, 200);
            }
        });
        navigationBar.getRightPane().addView(btn);
    }

    private void overridePendingBackTransitionAnimation() {
        overridePendingTransition(R.anim.transition_left_in, R.anim.transition_right_out);
    }

	@Override
	public void onBackPressed() {
		super.onBackPressed();
        overridePendingBackTransitionAnimation();
    }

    @Override
    public void onNavigationBarBackClick(Button button) {
        finish();
        overridePendingBackTransitionAnimation();
    }

    public void menuCloseButton(View button) {
        panesController.scrollToPaneIndex(1, 200);
    }
}
