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

/**
 * Created by Bartosz Lipinski
 * 13.10.15
 */
class PaddingChangeListener extends ChangeUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    private IntValues mLeftPadding;
    private IntValues mTopPadding;
    private IntValues mRightPadding;
    private IntValues mBottomPadding;

    PaddingChangeListener(View view) {
        super(view);
    }

    private int currentLeftPadding() {
        return hasView() ? mView.get().getPaddingLeft() : 0;
    }

    private int currentTopPadding() {
        return hasView() ? mView.get().getPaddingTop() : 0;
    }

    private int currentRightPadding() {
        return hasView() ? mView.get().getPaddingRight() : 0;
    }

    private int currentBottomPadding() {
        return hasView() ? mView.get().getPaddingBottom() : 0;
    }

    public void leftPadding(int padding) {
        mLeftPadding = new IntValues(currentLeftPadding(), padding);
    }

    public void leftPaddingBy(int paddingBy) {
        mLeftPadding = new IntValues(currentLeftPadding(), currentLeftPadding() + paddingBy);
    }

    public void topPadding(int padding) {
        mTopPadding = new IntValues(currentTopPadding(), padding);
    }

    public void topPaddingBy(int paddingBy) {
        mTopPadding = new IntValues(currentTopPadding(), currentTopPadding() + paddingBy);
    }

    public void bottomPadding(int padding) {
        mBottomPadding = new IntValues(currentBottomPadding(), padding);
    }

    public void bottomPaddingBy(int paddingBy) {
        mBottomPadding = new IntValues(currentBottomPadding(), currentBottomPadding() + paddingBy);
    }

    public void rightPadding(int padding) {
        mRightPadding = new IntValues(currentRightPadding(), padding);
    }

    public void rightPaddingBy(int paddingBy) {
        mRightPadding = new IntValues(currentRightPadding(), currentRightPadding() + paddingBy);
    }

    public void horizontalPadding(int padding) {
        leftPadding(padding);
        rightPadding(padding);
    }

    public void horizontalPaddingBy(int paddingBy) {
        leftPaddingBy(paddingBy);
        rightPaddingBy(paddingBy);
    }

    public void verticalPadding(int padding) {
        topPadding(padding);
        bottomPadding(padding);
    }

    public void verticalPaddingBy(int paddingBy) {
        topPaddingBy(paddingBy);
        bottomPaddingBy(paddingBy);
    }

    public void padding(int padding) {
        leftPadding(padding);
        topPadding(padding);
        bottomPadding(padding);
        rightPadding(padding);
    }

    public void paddingBy(int paddingBy) {
        leftPaddingBy(paddingBy);
        topPaddingBy(paddingBy);
        bottomPaddingBy(paddingBy);
        rightPaddingBy(paddingBy);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (hasView()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            int leftPadding = currentLeftPadding();
            int topPadding = currentTopPadding();
            int rightPadding = currentRightPadding();
            int bottomPadding = currentBottomPadding();

            if (mLeftPadding != null) {
                leftPadding = (int) calculateAnimatedValue(mLeftPadding.mFrom, mLeftPadding.mTo, animatedFraction);
            }
            if (mTopPadding != null) {
                topPadding = (int) calculateAnimatedValue(mTopPadding.mFrom, mTopPadding.mTo, animatedFraction);
            }
            if (mRightPadding != null) {
                rightPadding = (int) calculateAnimatedValue(mRightPadding.mFrom, mRightPadding.mTo, animatedFraction);
            }
            if (mBottomPadding != null) {
                bottomPadding = (int) calculateAnimatedValue(mBottomPadding.mFrom, mBottomPadding.mTo, animatedFraction);
            }
            mView.get().setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        }
    }

}