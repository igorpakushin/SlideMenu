SlideMenu
=========

FaceBook like menu implementation

---------

How to use:
=========

* Put the com.yme.customization.sidepanel.CustomHorizontalScrollView.java file to the 
corresponding folder in your project
* Make sure you reference it in your activity layout xml file
* Make sure the only child of this container is LinearLayout container with horizontal orientation
* Put any views you need(menu, content, whatever) inside the LinearLayout container
* Provide the control with views' sizes you need
* Use corresponding methods to scroll to panes you need

Example activity xml:
=========

    <?xml version="1.0" encoding="utf-8"?>
    <com.yme.customization.sidepanel.CustomHorizontalScrollView xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_height="match_parent"
        android:layout_width="match_parent"
        android:id="@+id/main_container">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="#ffff0000"
                android:orientation="vertical">

                <!-- Your menu pane here -->

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="#ff00ff00"
                android:orientation="vertical">

                <!-- Your content pane here -->

            </LinearLayout>

        </LinearLayout>

    </com.yme.customization.sidepanel.CustomHorizontalScrollView>
    
Configuration in code:
=========

    CustomHorizontalScrollView panesController = (CustomHorizontalScrollView) findViewById(R.id.panes_scroll_view);
    // these are percentages for views in the scrollview:
    //      1.0 means 100% of current device screen width
    //      0.8 means 80%
    float[] widths = {0.8f, 1.0f};

    // this forces children to size
    panesController.sizeViews(widths);

    // this sets current pane
    panesController.scrollToPaneIndexInstant(1);

To scroll to the desired pane with animation:

    int duration = 200; // miliseconds
    panesController.scrollToPaneIndexInstant(1, duration);
    
