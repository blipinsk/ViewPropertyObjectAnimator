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
 * 30.10.15
 */
class ScrollChangeListener extends ChangeUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    private IntValues mScrollX;
    private IntValues mScrollY;

    ScrollChangeListener(View view) {
        super(view);
    }

    private int currentScrollX() {
        return hasView() ? mView.get().getScrollX() : 0;
    }

    private int currentScrollY() {
        return hasView() ? mView.get().getScrollY() : 0;
    }

    public void scrollX(int scrollX) {
        mScrollX = new IntValues(currentScrollX(), scrollX);
    }

    public void scrollXBy(int scrollXBy) {
        mScrollX = new IntValues(currentScrollX(), currentScrollX() + scrollXBy);
    }

    public void scrollY(int scrollY) {
        mScrollY = new IntValues(currentScrollY(), scrollY);
    }

    public void scrollYBy(int scrollYBy) {
        mScrollY = new IntValues(currentScrollY(), currentScrollY() + scrollYBy);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (hasView()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            int scrollX = currentScrollX();
            int scrollY = currentScrollY();
            if (mScrollX != null) {
                scrollX = (int) calculateAnimatedValue(mScrollX.mFrom, mScrollX.mTo, animatedFraction);
            }
            if (mScrollY != null) {
                scrollY = (int) calculateAnimatedValue(mScrollY.mFrom, mScrollY.mTo, animatedFraction);
            }
            mView.get().scrollTo(scrollX, scrollY);
        }
    }

}