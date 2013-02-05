package com.yme;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ListView;

import com.yme.customization.NavigationBar;

public class SecondActivity extends Activity {
	
	private ListView listView;
    private NavigationBar navigationBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_second);

		listView = (ListView) findViewById(R.id.list_view);
        navigationBar = (NavigationBar) findViewById(R.id.navigation_bar);

        navigationBar.setTitleText("Hello");

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


//		NavigationBar navigationBar = (NavigationBar) LayoutInflater.from(this).inflate(R.layout.navigation_bar, null);
//        parent.addView(navigationBar);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.transition_left_in, R.anim.transition_right_out);
	}

}
