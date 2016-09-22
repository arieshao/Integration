package vip.xuanhao.integration.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public class AnimatorHelper {


    public static void startAnim(ObjectAnimator animator, View... view) {

    }

    public static void startAnim(AnimatorSet animatorSet, View... view) {

    }

    public static void playTogether(ObjectAnimator... animator) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.playTogether(animator);
        animatorSet.start();

    }

    public static void bindView(ObjectAnimator animator, final View view) {

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
