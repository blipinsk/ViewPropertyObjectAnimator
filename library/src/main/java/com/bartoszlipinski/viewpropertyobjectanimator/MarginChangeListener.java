/**
 * Copyright 2015 Bartosz Lipinski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bartoszlipinski.viewpropertyobjectanimator;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bartosz Lipinski
 * 13.10.15
 */
class MarginChangeListener extends ChangeUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    private final ViewGroup.MarginLayoutParams mParams;

    private IntValues mLeftMargin;
    private IntValues mTopMargin;
    private IntValues mRightMargin;
    private IntValues mBottomMargin;

    MarginChangeListener(View view) {
        super(view);
        mParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (mParams == null) {
            throw new IllegalStateException("View does not have layout params yet.");
        }
    }

    private int currentLeftMargin() {
        return mParams.leftMargin;
    }

    private int currentTopMargin() {
        return mParams.topMargin;
    }

    private int currentRightMargin() {
        return mParams.rightMargin;
    }

    private int currentBottomMargin() {
        return mParams.bottomMargin;
    }

    public void leftMargin(int margin) {
        mLeftMargin = new IntValues(currentLeftMargin(), margin);
    }

    public void leftMarginBy(int marginBy) {
        mLeftMargin = new IntValues(currentLeftMargin(), currentLeftMargin() + marginBy);
    }

    public void topMargin(int margin) {
        mTopMargin = new IntValues(currentTopMargin(), margin);
    }

    public void topMarginBy(int marginBy) {
        mTopMargin = new IntValues(currentTopMargin(), currentTopMargin() + marginBy);
    }

    public void bottomMargin(int margin) {
        mBottomMargin = new IntValues(currentBottomMargin(), margin);
    }

    public void bottomMarginBy(int marginBy) {
        mBottomMargin = new IntValues(currentBottomMargin(), currentBottomMargin() + marginBy);
    }

    public void rightMargin(int margin) {
        mRightMargin = new IntValues(currentRightMargin(), margin);
    }

    public void rightMarginBy(int marginBy) {
        mRightMargin = new IntValues(currentRightMargin(), currentRightMargin() + marginBy);
    }

    public void horizontalMargin(int margin) {
        leftMargin(margin);
        rightMargin(margin);
    }

    public void horizontalMarginBy(int marginBy) {
        leftMarginBy(marginBy);
        rightMarginBy(marginBy);
    }

    public void verticalMargin(int margin) {
        topMargin(margin);
        bottomMargin(margin);
    }

    public void verticalMarginBy(int marginBy) {
        topMarginBy(marginBy);
        bottomMarginBy(marginBy);
    }

    public void margin(int margin) {
        leftMargin(margin);
        topMargin(margin);
        bottomMargin(margin);
        rightMargin(margin);
    }

    public void marginBy(int marginBy) {
        leftMarginBy(marginBy);
        topMarginBy(marginBy);
        bottomMarginBy(marginBy);
        rightMarginBy(marginBy);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (hasView()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            if (mLeftMargin != null) {
                mParams.leftMargin = (int) calculateAnimatedValue(mLeftMargin.mFrom, mLeftMargin.mTo, animatedFraction);
            }
            if (mTopMargin != null) {
                mParams.topMargin = (int) calculateAnimatedValue(mTopMargin.mFrom, mTopMargin.mTo, animatedFraction);
            }
            if (mRightMargin != null) {
                mParams.rightMargin = (int) calculateAnimatedValue(mRightMargin.mFrom, mRightMargin.mTo, animatedFraction);
            }
            if (mBottomMargin != null) {
                mParams.bottomMargin = (int) calculateAnimatedValue(mBottomMargin.mFrom, mBottomMargin.mTo, animatedFraction);
            }
            mView.get().requestLayout();
        }
    }

}