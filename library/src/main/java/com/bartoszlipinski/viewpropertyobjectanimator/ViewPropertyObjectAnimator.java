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

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Bartosz Lipinski
 * 16.09.15
 */
public class ViewPropertyObjectAnimator {

    private final WeakReference<View> mView;
    private long mDuration = -1;
    private long mStartDelay = -1;
    private boolean mWithLayer = false;
    private Interpolator mInterpolator;
    private List<Animator.AnimatorListener> mListeners = new ArrayList<>();
    private List<ValueAnimator.AnimatorUpdateListener> mUpdateListeners = new ArrayList<>();
    private List<Animator.AnimatorPauseListener> mPauseListeners = new ArrayList<>();
    private ArrayMap<Property<View, Float>, PropertyValuesHolder> mPropertyHoldersMap = new ArrayMap<>();

    private ViewPropertyObjectAnimator(View view) {
        mView = new WeakReference<View>(view);
    }

    public static ViewPropertyObjectAnimator animate(View view) {
        return new ViewPropertyObjectAnimator(view);
    }

    private void animateProperty(Property<View, Float> property, float toValue) {
        if (hasView()) {
            float fromValue = property.get(mView.get());
            animatePropertyBetween(property, fromValue, toValue);
        }
    }

    private void animatePropertyBy(Property<View, Float> property, float byValue) {
        if (hasView()) {
            float fromValue = property.get(mView.get());
            float toValue = fromValue + byValue;
            animatePropertyBetween(property, fromValue, toValue);
        }
    }

    private void animatePropertyBetween(Property<View, Float> property, float fromValue, float toValue) {
        mPropertyHoldersMap.remove(property); //if the same property is assigned again, we want to override it
        mPropertyHoldersMap.put(property, PropertyValuesHolder.ofFloat(property, fromValue, toValue));
    }

    public ViewPropertyObjectAnimator scaleX(float scaleX) {
        animateProperty(View.SCALE_X, scaleX);
        return this;
    }

    public ViewPropertyObjectAnimator scaleXBy(float scaleXBy) {
        animatePropertyBy(View.SCALE_X, scaleXBy);
        return this;
    }

    public ViewPropertyObjectAnimator scaleY(float scaleY) {
        animateProperty(View.SCALE_Y, scaleY);
        return this;
    }

    public ViewPropertyObjectAnimator scaleYBy(float scaleYBy) {
        animatePropertyBy(View.SCALE_Y, scaleYBy);
        return this;
    }

    public ViewPropertyObjectAnimator scales(float scales) {
        scaleY(scales);
        scaleX(scales);
        return this;
    }

    public ViewPropertyObjectAnimator scalesBy(float scalesBy) {
        scaleYBy(scalesBy);
        scaleXBy(scalesBy);
        return this;
    }

    public ViewPropertyObjectAnimator translationX(float translationX) {
        animateProperty(View.TRANSLATION_X, translationX);
        return this;
    }

    public ViewPropertyObjectAnimator translationXBy(float translationXBy) {
        animatePropertyBy(View.TRANSLATION_X, translationXBy);
        return this;
    }

    public ViewPropertyObjectAnimator translationY(float translationY) {
        animateProperty(View.TRANSLATION_Y, translationY);
        return this;
    }

    public ViewPropertyObjectAnimator translationYBy(float translationYBy) {
        animatePropertyBy(View.TRANSLATION_Y, translationYBy);
        return this;
    }

    @SuppressLint("NewApi")
    public ViewPropertyObjectAnimator translationZ(float translationZ) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateProperty(View.TRANSLATION_Z, translationZ);
        }
        return this;
    }

    @SuppressLint("NewApi")
    public ViewPropertyObjectAnimator translationZBy(float translationZBy) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animatePropertyBy(View.TRANSLATION_Z, translationZBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator alpha(float alpha) {
        animateProperty(View.ALPHA, alpha);
        return this;
    }

    public ViewPropertyObjectAnimator alphaBy(float alphaBy) {
        animatePropertyBy(View.ALPHA, alphaBy);
        return this;
    }

    public ViewPropertyObjectAnimator rotation(float rotation) {
        animateProperty(View.ROTATION, rotation);
        return this;
    }

    public ViewPropertyObjectAnimator rotationBy(float rotationBy) {
        animatePropertyBy(View.ROTATION, rotationBy);
        return this;
    }

    public ViewPropertyObjectAnimator rotationX(float rotationX) {
        animateProperty(View.ROTATION_X, rotationX);
        return this;
    }

    public ViewPropertyObjectAnimator rotationXBy(float rotationXBy) {
        animatePropertyBy(View.ROTATION_X, rotationXBy);
        return this;
    }

    public ViewPropertyObjectAnimator rotationY(float rotationY) {
        animateProperty(View.ROTATION_Y, rotationY);
        return this;
    }

    public ViewPropertyObjectAnimator rotationYBy(float rotationYBy) {
        animatePropertyBy(View.ROTATION_Y, rotationYBy);
        return this;
    }

    public ViewPropertyObjectAnimator x(float x) {
        animateProperty(View.X, x);
        return this;
    }

    public ViewPropertyObjectAnimator xBy(float xBy) {
        animatePropertyBy(View.X, xBy);
        return this;
    }

    public ViewPropertyObjectAnimator y(float y) {
        animateProperty(View.Y, y);
        return this;
    }

    public ViewPropertyObjectAnimator yBy(float yBy) {
        animatePropertyBy(View.Y, yBy);
        return this;
    }

    @SuppressLint("NewApi")
    public ViewPropertyObjectAnimator z(float z) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateProperty(View.Z, z);
        }
        return this;
    }

    @SuppressLint("NewApi")
    public ViewPropertyObjectAnimator zBy(float zBy) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animatePropertyBy(View.Z, zBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator withLayer() {
        mWithLayer = true;
        return this;
    }

    public ViewPropertyObjectAnimator setStartDelay(long startDelay) {
        if (startDelay < 0) {
            throw new IllegalArgumentException("startDelay cannot be < 0");
        }
        mStartDelay = startDelay;
        return this;
    }

    public ViewPropertyObjectAnimator setDuration(long duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("duration cannot be < 0");
        }
        mDuration = duration;
        return this;
    }

    public ViewPropertyObjectAnimator setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
        return this;
    }

    public ViewPropertyObjectAnimator addListener(Animator.AnimatorListener listener) {
        mListeners.add(listener);
        return this;
    }

    public ViewPropertyObjectAnimator removeListener(Animator.AnimatorListener listener) {
        mListeners.remove(listener);
        return this;
    }

    public ViewPropertyObjectAnimator removeAllListeners() {
        mListeners.clear();
        return this;
    }

    public ViewPropertyObjectAnimator addUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        mUpdateListeners.add(listener);
        return this;
    }

    public ViewPropertyObjectAnimator removeUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        mUpdateListeners.remove(listener);
        return this;
    }

    public ViewPropertyObjectAnimator removeAllUpdateListeners() {
        mUpdateListeners.clear();
        return this;
    }

    public ViewPropertyObjectAnimator addPauseListener(ValueAnimator.AnimatorPauseListener listener) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mPauseListeners.add(listener);
        }
        return this;
    }

    public ViewPropertyObjectAnimator removePauseListener(ValueAnimator.AnimatorPauseListener listener) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mPauseListeners.remove(listener);
        }
        return this;
    }

    public ViewPropertyObjectAnimator removeAllPauseListeners() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mPauseListeners.clear();
        }
        return this;
    }

    public ViewPropertyObjectAnimator withStartAction(final Runnable runnable) {
        return addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                runnable.run();
                removeListener(this);
            }
        });
    }

    public ViewPropertyObjectAnimator withEndAction(final Runnable runnable) {
        return addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                runnable.run();
                removeListener(this);
            }
        });
    }

    private boolean hasView() {
        return mView.get() != null;
    }

    @SuppressLint("NewApi")
    public ObjectAnimator get() {
        if (hasView()) {
            Collection<PropertyValuesHolder> holders = mPropertyHoldersMap.values();
            ObjectAnimator animator =
                    ObjectAnimator.ofPropertyValuesHolder(mView.get(),
                            holders.toArray(new PropertyValuesHolder[holders.size()]));
            if (mWithLayer) {
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (hasView()) {
                            mView.get().setLayerType(View.LAYER_TYPE_HARDWARE, null);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (hasView()) {
                            mView.get().setLayerType(View.LAYER_TYPE_NONE, null);
                        }
                    }
                });
            }
            if (mStartDelay != -1) {
                animator.setStartDelay(mStartDelay);
            }
            if (mDuration != -1) {
                animator.setDuration(mDuration);
            }
            if (mInterpolator != null) {
                animator.setInterpolator(mInterpolator);
            }
            for (Animator.AnimatorListener listener : mListeners) {
                animator.addListener(listener);
            }
            for (ValueAnimator.AnimatorUpdateListener listener : mUpdateListeners) {
                animator.addUpdateListener(listener);
            }
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                for (Animator.AnimatorPauseListener listener : mPauseListeners) {
                    animator.addPauseListener(listener);
                }
            }
            return animator;
        }
        return ObjectAnimator.ofFloat(null, View.ALPHA, 1, 1);
    }

    public void start(){
        get().start();
    }
}
