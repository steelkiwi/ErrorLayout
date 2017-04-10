package steelkiwi.com.library.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import steelkiwi.com.library.R;
import steelkiwi.com.library.anim.AnimationType;


/**
 * Created by yaroslav on 2/14/17.
 */

public class ToastView extends LinearLayout {

    private TextView tvErrorMessage;
    private static final int DEFAULT_TEXT_SIZE = 14;
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_BACKGROUND = R.drawable.error_toast_view_background;
    private static final int DEFAULT_GRAVITY = Gravity.CENTER;
    private static final int DEFAULT_DELAY = 1000;
    private int showDelay;
    private AnimationType animationType;

    public ToastView(Context context) {
        super(context);
        init();
    }

    public ToastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToastView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void inflateViews(){
        LayoutInflater.from(getContext()).inflate(R.layout.toast_layout, this, true);
        tvErrorMessage = (TextView) findViewById(R.id.tvErrorMessage);
    }

    private void init(){
        inflateViews();
        setDefaultLayoutParams();
    }

    private void setDefaultLayoutParams(){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        setLayoutParams(layoutParams);
    }

    public void setErrorMessage(String message){
        tvErrorMessage.setText(message);
    }

    public TextView getTvErrorMessage() {
        return tvErrorMessage;
    }

    public void setShowDelay(int showDelay) {
        this.showDelay = showDelay;
    }

    public int getShowDelay() {
        return showDelay;
    }

    public AnimationType getAnimationType() {
        return animationType;
    }

    public void setAnimationType(AnimationType animationType) {
        this.animationType = animationType;
    }

    public static class Builder {
        private ToastView toastView;
        private TextView errorTitle;

        public Builder(Context context) {
            toastView = new ToastView(context);
            errorTitle = toastView.getTvErrorMessage();
            prepareDefaultConfiguration();
        }

        private void prepareDefaultConfiguration(){
            errorTitle.setTextSize(DEFAULT_TEXT_SIZE);
            errorTitle.setTextColor(DEFAULT_TEXT_COLOR);
            errorTitle.setGravity(DEFAULT_GRAVITY);
            toastView.setBackgroundResource(DEFAULT_BACKGROUND);
            toastView.setShowDelay(DEFAULT_DELAY);
            toastView.setAnimationType(AnimationType.TOP);
        }

        public Builder setTextSize(int size) {
            errorTitle.setTextSize(size);
            return this;
        }

        public Builder setTextColor(int color) {
            errorTitle.setTextColor(color);
            return this;
        }

        public Builder setBackground(int background) {
            toastView.setBackgroundResource(background);
            return this;
        }

        public Builder setTextGravity(int gravity) {
            errorTitle.setGravity(gravity);
            return this;
        }

        public Builder setShowDelay(int delay) {
            toastView.setShowDelay(delay * 1000);
            return this;
        }

        public Builder setAnimationType(AnimationType state) {
            toastView.setAnimationType(state);
            return this;
        }

        public ToastView build() {
            return toastView;
        }
    }
}
