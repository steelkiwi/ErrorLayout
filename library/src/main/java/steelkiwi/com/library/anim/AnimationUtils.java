package steelkiwi.com.library.anim;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by yaroslav on 3/31/17.
 */

public final class AnimationUtils {
    public static ObjectAnimator translateToastByY(View view, float y, int duration, Interpolator interpolator) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", y);
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.start();
        return objectAnimator;
    }

    public static ObjectAnimator translateToastByX(View view, float x, int duration, Interpolator interpolator) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", x);
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.start();
        return objectAnimator;
    }

    public static ObjectAnimator toastAlpha(View view, float alpha, int duration, Interpolator interpolator) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", alpha);
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.start();
        return objectAnimator;
    }
}
