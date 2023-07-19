package com.bartoszlipinski.viewpropertyobjectanimator.sample.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import com.bartoszlipinski.viewpropertyobjectanimator.sample.R;

/**
 * Created by Bartosz Lipinski
 * 01.02.15
 */
public class MainActivity extends Activity {

    private ScrollView mScroll;
    private ImageView mImage;
    private AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScroll = findViewById(R.id.scrollView);
        mImage = findViewById(R.id.image);
        mImage.setOnClickListener(v -> {
            boolean isSelectedAfterClick = !v.isSelected();
            v.setSelected(isSelectedAfterClick);
            if (isSelectedAfterClick) {
                reverseAnimator();
            } else {
                animator();
            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(this::animator, 500);
    }

    private void animator() {
        int paddingTop = getResources().getDimensionPixelSize(R.dimen.scroll_padding_top);

        ObjectAnimator scrollAnimator =
                ViewPropertyObjectAnimator.animate(mScroll)
                        .scrollY(paddingTop)
                        .get();

        ObjectAnimator imageAnimator =
                ViewPropertyObjectAnimator.animate(mImage)
                        .verticalMargin(140)
                        .rightMarginBy(10)
                        .width(600)
                        .height(700)
                        .rotationXBy(20)
                        .topPadding(10)
                        .rotationY(360)
                        .leftPaddingBy(100)
                        .rightPadding(300)
                        .get();

        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(scrollAnimator, imageAnimator);
        mAnimatorSet.setDuration(2000);
        mAnimatorSet.start();
    }

    private void reverseAnimator() {
        int width = getResources().getDimensionPixelSize(R.dimen.image_width);
        int height = getResources().getDimensionPixelSize(R.dimen.image_height);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        int padding = getResources().getDimensionPixelSize(R.dimen.image_padding);

        ObjectAnimator scrollAnimator =
                ViewPropertyObjectAnimator.animate(mScroll)
                        .scrollY(0)
                        .get();

        ObjectAnimator imageAnimator =
                ViewPropertyObjectAnimator.animate(mImage)
                        .width(width)
                        .height(height)
                        .margin(margin)
                        .padding(padding)
                        .rotationX(0)
                        .rotationY(0)
                        .get();

        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(scrollAnimator, imageAnimator);
        mAnimatorSet.setDuration(2000);
        mAnimatorSet.start();
    }
}
