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
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.percent.PercentLayoutHelper;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bartosz Lipinski
 * 11.12.2015
 */
class PercentChangeListener extends ChangeUpdateListener implements AnimatorUpdateListener {

    private final PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;

    private FloatValues mWidthPercent;
    private FloatValues mHeightPercent;
    private FloatValues mLeftMarginPercent;
    private FloatValues mTopMarginPercent;
    private FloatValues mBottomMarginPercent;
    private FloatValues mRightMarginPercent;
    private FloatValues mAspectRatio;

    PercentChangeListener(View view) {
        super(view);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            throw new IllegalStateException("View does not have layout params yet.");
        }
        try {
            if (!(params instanceof PercentLayoutHelper.PercentLayoutParams)) {
                throw new IllegalStateException("Animating percent parameters (aspectRatio is also a \"percent parameter\") is available only for children of PercentRelativeLayout or PercentFrameLayout (part of the Percent Support Library).");
            }
        } catch (NoClassDefFoundError error) {
            throw new IllegalStateException("Animating percent parameters (aspectRatio is also a \"percent parameter\") is available only for children of PercentRelativeLayout or PercentFrameLayout (part of the Percent Support Library).");
        }
        mPercentLayoutInfo = ((PercentLayoutHelper.PercentLayoutParams) params).getPercentLayoutInfo();
    }

    private float currentWidthPercent() {
        return mPercentLayoutInfo.widthPercent;
    }

    private float currentHeightPercent() {
        return mPercentLayoutInfo.heightPercent;
    }

    private float currentLeftMarginPercent() {
        return mPercentLayoutInfo.leftMarginPercent;
    }

    private float currentTopMarginPercent() {
        return mPercentLayoutInfo.topMarginPercent;
    }

    private float currentRightMarginPercent() {
        return mPercentLayoutInfo.rightMarginPercent;
    }

    private float currentBottomMarginPercent() {
        return mPercentLayoutInfo.bottomMarginPercent;
    }

    private float currentAspectRatio() {
        return mPercentLayoutInfo.aspectRatio;
    }

    public void widthPercent(float widthPercent) {
        mWidthPercent = new FloatValues(currentWidthPercent(), widthPercent);
    }

    public void widthPercentBy(float widthPercentBy) {
        mWidthPercent = new FloatValues(currentWidthPercent(), currentWidthPercent() + widthPercentBy);
    }

    public void heightPercent(float heightPercent) {
        mHeightPercent = new FloatValues(currentHeightPercent(), heightPercent);
    }

    public void heightPercentBy(float heightPercentBy) {
        mHeightPercent = new FloatValues(currentHeightPercent(), currentHeightPercent() + heightPercentBy);
    }

    public void sizePercent(float sizePercent) {
        widthPercent(sizePercent);
        heightPercent(sizePercent);
    }

    public void sizePercentBy(float sizePercentBy) {
        widthPercentBy(sizePercentBy);
        heightPercentBy(sizePercentBy);
    }

    public void leftMarginPercent(float marginPercent) {
        mLeftMarginPercent = new FloatValues(currentLeftMarginPercent(), marginPercent);
    }

    public void leftMarginPercentBy(float marginPercentBy) {
        mLeftMarginPercent = new FloatValues(currentLeftMarginPercent(), currentLeftMarginPercent() + marginPercentBy);
    }

    public void topMarginPercent(float marginPercent) {
        mTopMarginPercent = new FloatValues(currentTopMarginPercent(), marginPercent);
    }

    public void topMarginPercentBy(float marginPercentBy) {
        mTopMarginPercent = new FloatValues(currentTopMarginPercent(), currentTopMarginPercent() + marginPercentBy);
    }

    public void bottomMarginPercent(float marginPercent) {
        mBottomMarginPercent = new FloatValues(currentBottomMarginPercent(), marginPercent);
    }

    public void bottomMarginPercentBy(float marginPercentBy) {
        mBottomMarginPercent = new FloatValues(currentBottomMarginPercent(), currentBottomMarginPercent() + marginPercentBy);
    }

    public void rightMarginPercent(float marginPercent) {
        mRightMarginPercent = new FloatValues(currentRightMarginPercent(), marginPercent);
    }

    public void rightMarginPercentBy(float marginPercentBy) {
        mRightMarginPercent = new FloatValues(currentRightMarginPercent(), currentRightMarginPercent() + marginPercentBy);
    }

    public void horizontalMarginPercent(float marginPercent) {
        leftMarginPercent(marginPercent);
        rightMarginPercent(marginPercent);
    }

    public void horizontalMarginPercentBy(float marginPercentBy) {
        leftMarginPercentBy(marginPercentBy);
        rightMarginPercentBy(marginPercentBy);
    }

    public void verticalMarginPercent(float marginPercent) {
        topMarginPercent(marginPercent);
        bottomMarginPercent(marginPercent);
    }

    public void verticalMarginPercentBy(float marginPercentBy) {
        topMarginPercentBy(marginPercentBy);
        bottomMarginPercentBy(marginPercentBy);
    }

    public void marginPercent(float marginPercent) {
        leftMarginPercent(marginPercent);
        topMarginPercent(marginPercent);
        bottomMarginPercent(marginPercent);
        rightMarginPercent(marginPercent);
    }

    public void marginPercentBy(float marginPercentBy) {
        leftMarginPercentBy(marginPercentBy);
        topMarginPercentBy(marginPercentBy);
        bottomMarginPercentBy(marginPercentBy);
        rightMarginPercentBy(marginPercentBy);
    }

    public void aspectRatio(float aspectRatio) {
        mAspectRatio = new FloatValues(currentAspectRatio(), aspectRatio);
    }

    public void aspectRatioBy(float aspectRatioBy) {
        mAspectRatio = new FloatValues(currentAspectRatio(), currentAspectRatio() + aspectRatioBy);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (hasView()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            if (mWidthPercent != null) {
                mPercentLayoutInfo.widthPercent = calculateAnimatedValue(mWidthPercent.mFrom, mWidthPercent.mTo, animatedFraction);
            }
            if (mHeightPercent != null) {
                mPercentLayoutInfo.heightPercent = calculateAnimatedValue(mHeightPercent.mFrom, mHeightPercent.mTo, animatedFraction);
            }
            if (mLeftMarginPercent != null) {
                mPercentLayoutInfo.leftMarginPercent = calculateAnimatedValue(mLeftMarginPercent.mFrom, mLeftMarginPercent.mTo, animatedFraction);
            }
            if (mTopMarginPercent != null) {
                mPercentLayoutInfo.topMarginPercent = calculateAnimatedValue(mTopMarginPercent.mFrom, mTopMarginPercent.mTo, animatedFraction);
            }
            if (mRightMarginPercent != null) {
                mPercentLayoutInfo.rightMarginPercent = calculateAnimatedValue(mRightMarginPercent.mFrom, mRightMarginPercent.mTo, animatedFraction);
            }
            if (mBottomMarginPercent != null) {
                mPercentLayoutInfo.bottomMarginPercent = calculateAnimatedValue(mBottomMarginPercent.mFrom, mBottomMarginPercent.mTo, animatedFraction);
            }
            if (mAspectRatio != null) {
                mPercentLayoutInfo.aspectRatio = calculateAnimatedValue(mAspectRatio.mFrom, mAspectRatio.mTo, animatedFraction);
            }
            mView.get().requestLayout();
        }
    }
}
