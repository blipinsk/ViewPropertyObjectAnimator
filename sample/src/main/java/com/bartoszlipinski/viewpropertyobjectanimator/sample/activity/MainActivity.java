package com.bartoszlipinski.viewpropertyobjectanimator.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import com.bartoszlipinski.viewpropertyobjectanimator.sample.R;

/**
 * Created by Bartosz Lipinski
 * 01.02.15
 */
public class MainActivity extends Activity {

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.image);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelectedAfterClick = !v.isSelected();
                v.setSelected(isSelectedAfterClick);
                if (isSelectedAfterClick) {
                    reverseAnimator();
                } else {
                    animator();
                }
            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animator();
            }

        }, 500);
    }

    private void animator() {
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
                .setDuration(2000)
                .start();
    }

    private void reverseAnimator() {
        int width = getResources().getDimensionPixelSize(R.dimen.image_width);
        int height = getResources().getDimensionPixelSize(R.dimen.image_height);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        int padding = getResources().getDimensionPixelSize(R.dimen.image_padding);

        ViewPropertyObjectAnimator.animate(mImage)
                .width(width)
                .height(height)
                .margin(margin)
                .padding(padding)
                .rotationX(0)
                .rotationY(0)
                .setDuration(2000)
                .start();
    }
}
