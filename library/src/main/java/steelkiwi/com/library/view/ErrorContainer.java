package steelkiwi.com.library.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import steelkiwi.com.library.R;
import steelkiwi.com.library.anim.AnimationType;
import steelkiwi.com.library.anim.AnimationUtils;


/**
 * Created by yaroslav on 2/8/17.
 */

public class ErrorContainer extends RelativeLayout {
    public static final int DELAY_1000 = 1000;
    public static final int DELAY_900 = 900;
    public static final int DELAY_600 = 600;

    private ToastView toast;
    private int parentWidth;
    private int parentHeight;
    private Handler handler;
    private boolean isAnimFinish = true;
    private boolean isToastShowed = false;

    public ErrorContainer(Context context) {
        super(context);
        init(context);
    }

    public ErrorContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ErrorContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentWidth, parentHeight);
    }

    private void init(Context context) {
        handler = new Handler();
    }

    private void prepareToastView(ToastView toast){
        // prepare error toast instance and add it to parent
        this.toast = toast;
        this.toast.setAlpha(0f);
        prepareDefaultToastState();
        addView(this.toast);
    }

    private void prepareDefaultToastState(){
        AnimationType state = toast.getAnimationType();
        LayoutParams params = (LayoutParams) getToastView().getLayoutParams();
        switch (state) {
            case DOWN:
                params.addRule(ALIGN_PARENT_BOTTOM, 0);
                params.addRule(ALIGN_PARENT_TOP);
                break;
            case TOP:
                params.addRule(ALIGN_PARENT_BOTTOM);
                params.addRule(ALIGN_PARENT_TOP, 0);
                break;
        }
    }

    private int[] calculateViewPosition(final View view){
        int[] locations = new int[2];
        // get view location on the screen
        view.getLocationOnScreen(locations);
        return locations;
    }

    public void showError(View view, String message) {
        prepareDefaultToastState();
        if(view != null && isAnimFinish() && !isToastShowed()) {
            // calculate view position where error happened
            int[] locations = calculateViewPosition(view);
            float x = locations[0];
            float y = locations[1];
            // set error message on toast
            getToastView().setErrorMessage(message);
            // prepare arise animation
            showToastAriseAnimation(view, x, y);
            setToastShowed(true);
            setAnimFinish(false);
        }
    }

    private void showToastAriseAnimation(View view, float x, float y){
        AnimationType state = toast.getAnimationType();
        switch (state) {
            case DOWN:
                ariseToastDownAnimation(view, x, y);
                break;
            case TOP:
                ariseToastTopAnimation(view, x, y);
                break;
        }
    }

    private void ariseToastTopAnimation(View view, final float x, final float y) {
        float translateY = -(parentHeight - y) - getResources().getDimension(R.dimen.bottom_margin);
        AnimationUtils.translateToastByY(getToastView(), translateY, DELAY_1000, new OvershootInterpolator());
        AnimationUtils.toastAlpha(getToastView(), 1f, DELAY_600, new DecelerateInterpolator())
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideToastDisappearanceAnimation();
                    }
                });
    }

    private void ariseToastDownAnimation(View view, final float x, final float y) {
        float translateY = y - view.getHeight() - getResources().getDimension(R.dimen.top_margin);
        AnimationUtils.translateToastByY(getToastView(), translateY, DELAY_1000, new OvershootInterpolator());
        AnimationUtils.toastAlpha(getToastView(), 1f, DELAY_600, new DecelerateInterpolator())
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideToastDisappearanceAnimation();
                    }
                });
    }

    private void hideToastDisappearanceAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideToast();
            }
        }, getToastView().getShowDelay());
    }

    private void hideToast() {
        AnimationType type = getToastView().getAnimationType();
        switch (type) {
            case DOWN:
                disappearToastTopAlphaAnimation();
                break;
            case TOP:
                disappearToastDownAlphaAnimation();
                break;
        }
    }

    private void disappearToastDownAlphaAnimation() {
        AnimationUtils.toastAlpha(getToastView(), 0f, DELAY_600, new AccelerateInterpolator());
        AnimationUtils.translateToastByY(getToastView(), 0f, DELAY_1000, new AccelerateInterpolator())
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        setAnimFinish(true);
                        setToastShowed(false);
                    }
                });
    }

    private void disappearToastTopAlphaAnimation() {
        AnimationUtils.toastAlpha(getToastView(), 0f, DELAY_600, new AccelerateInterpolator());
        AnimationUtils.translateToastByY(getToastView(), 0f, DELAY_1000, new AccelerateInterpolator())
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        setAnimFinish(true);
                        setToastShowed(false);
                    }
                });
    }

    public void setToast(ToastView toast) {
        prepareToastView(toast);
    }

    public ToastView getToastView() {
        return toast;
    }

    public boolean isAnimFinish() {
        return isAnimFinish;
    }

    public void setAnimFinish(boolean animFinish) {
        isAnimFinish = animFinish;
    }

    public boolean isToastShowed() {
        return isToastShowed;
    }

    public void setToastShowed(boolean toastShowed) {
        isToastShowed = toastShowed;
    }
}
