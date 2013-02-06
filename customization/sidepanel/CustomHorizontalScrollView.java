package com.yme.customization.sidepanel;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import static java.lang.Math.abs;

/**
 * Created with IntelliJ IDEA.
 * User: Igor Pakushin
 * Date: 05/02/2013
 * Time: 17:40
 */
public class CustomHorizontalScrollView extends HorizontalScrollView {

    public static final String SCROLL_VIEW_PANE_ERROR = "SCROLL_VIEW_PANE_ERROR";
    private final Context context;

    private int index;
    private CountDownTimer animationTimer;
    private ViewGroup parent;
    private boolean hasBeingLayedOut = false;
    private boolean needCallScrollPane = false;

    public CustomHorizontalScrollView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init() {
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Do not allow touch events.
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // Do not allow touch events.
        return false;
    }

    public void sizeViews(float[] widthsArr) {
        parent = (ViewGroup) getChildAt(0);

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int scrWidth = metrics.widthPixels;

        for (int i=0; i<parent.getChildCount(); i++) {
            View v = (View) parent.getChildAt(i);
            v.setLayoutParams(new LinearLayout.LayoutParams((int)(scrWidth * widthsArr[i]), LinearLayout.LayoutParams.MATCH_PARENT));
        }

        // this is needed to make sure scrolling won't be performed before view has been layed out
        // and even though if it have, will just postpone actual animation
        ViewTreeObserver observer = getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hasBeingLayedOut = true;
                if (needCallScrollPane) {
                    scrollToPaneIndex(index);
                    needCallScrollPane = false;
                }
            }
        });

    }

    private void scrollToPaneIndexWithAnimation(int index, int duration) {
        this.index = index;
        if (hasBeingLayedOut == false) { // checking if the view is layed out already
            needCallScrollPane = true;   // if it wasn't, remember we need to scroll to a view
            return;                      // when it will be ready
        }

        int widthAccumulator = 0;
        if (index < parent.getChildCount() && index >= 0) {

            // calculating positing to where to scroll
            for (int i=0; i<index; i++)
                widthAccumulator += parent.getChildAt(i).getMeasuredWidth();

            if (duration > 0) performScrollAnimation(duration, 100, widthAccumulator);
            else scrollTo(widthAccumulator, 0);

        } else {
            Log.e(SCROLL_VIEW_PANE_ERROR, "Scroll pane view out of bounds");
        }
    }

    public void scrollToPaneIndex(int index) {
        scrollToPaneIndexWithAnimation(index, 0);
    }

    public void scrollToPaneIndex(int index, int duration) {
        scrollToPaneIndexWithAnimation(index, duration);
    }

    private void performScrollAnimation(final int animDuration, final int numTicks, final int scrollTo) {
        // stop the timer if it's already running
        if (animationTimer != null) animationTimer.cancel();

        // used to perform scroll animation
        // need it final to use within the class implementation
        final float delta = (getScrollX() - scrollTo)/(float)animDuration;

        // this is needed to stop the timer when it's done
        // need it final to use within the class implementation
        final CustomHorizontalScrollView me = this;

        // start the timer
        me.animationTimer = new CountDownTimer(animDuration, animDuration/numTicks) {

            public void onTick(long milisUntillFinished) {
                if (delta > 0)
                    scrollTo((int) (delta * milisUntillFinished), 0);                           // scroll left
                else
                    scrollTo((int) ( abs(delta) * (animDuration - milisUntillFinished)), 0);    // scroll right
            }

            public void onFinish() {
                scrollTo(scrollTo, 0);     // make sure we ended where we should
                me.animationTimer = null;  // clean up
            }

        };
        me.animationTimer.start();
    }

    public int getIndex() {
        return index;
    }

}
