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

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by Bartosz Lipinski
 * 13.10.15
 */
abstract class ChangeUpdateListener {

    protected final WeakReference<View> mView;

    public ChangeUpdateListener(View view) {
        this.mView =  new WeakReference<View>(view);
    }

    public float calculateAnimatedValue(float initialValue, float targetValue, float animationFraction) {
        return targetValue - ((targetValue - initialValue) * (1.0f - animationFraction));
    }

    protected boolean hasView() {
        return mView.get() != null;
    }

    protected class IntValues {
        public int mFrom;
        public int mTo;

        public IntValues(int from, int to) {
            this.mFrom = from;
            this.mTo = to;
        }
    }

    protected class FloatValues {
        public float mFrom;
        public float mTo;

        public FloatValues(float from, float to) {
            this.mFrom = from;
            this.mTo = to;
        }
    }
}
