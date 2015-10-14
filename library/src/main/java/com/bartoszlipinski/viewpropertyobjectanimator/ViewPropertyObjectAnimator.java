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
import android.view.ViewTreeObserver;
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
    private MarginChangeListener mMarginListener;
    private DimensionChangeListener mDimensionListener;
    private PaddingChangeListener mPaddingListener;

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

    public ViewPropertyObjectAnimator leftMargin(int leftMargin) {
        if (initMarginListener()) {
            mMarginListener.leftMargin(leftMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator leftMarginBy(int leftMarginBy) {
        if (initMarginListener()) {
            mMarginListener.leftMarginBy(leftMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator topMargin(int topMargin) {
        if (initMarginListener()) {
            mMarginListener.topMargin(topMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator topMarginBy(int topMarginBy) {
        if (initMarginListener()) {
            mMarginListener.topMarginBy(topMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator rightMargin(int rightMargin) {
        if (initMarginListener()) {
            mMarginListener.rightMargin(rightMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator rightMarginBy(int rightMarginBy) {
        if (initMarginListener()) {
            mMarginListener.rightMarginBy(rightMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator bottomMargin(int bottomMargin) {
        if (initMarginListener()) {
            mMarginListener.bottomMargin(bottomMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator bottomMarginBy(int bottomMarginBy) {
        if (initMarginListener()) {
            mMarginListener.bottomMarginBy(bottomMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator horizontalMargin(int horizontalMargin) {
        if (initMarginListener()) {
            mMarginListener.horizontalMargin(horizontalMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator horizontalMarginBy(int horizontalMarginBy) {
        if (initMarginListener()) {
            mMarginListener.horizontalMarginBy(horizontalMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator verticalMargin(int verticalMargin) {
        if (initMarginListener()) {
            mMarginListener.verticalMargin(verticalMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator verticalMarginBy(int verticalMarginBy) {
        if (initMarginListener()) {
            mMarginListener.verticalMarginBy(verticalMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator margin(int margin) {
        if (initMarginListener()) {
            mMarginListener.margin(margin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator marginBy(int marginBy) {
        if (initMarginListener()) {
            mMarginListener.marginBy(marginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator width(int width) {
        if (initDimensionListener()) {
            mDimensionListener.width(width);
        }
        return this;
    }

    public ViewPropertyObjectAnimator widthBy(int widthBy) {
        if (initDimensionListener()) {
            mDimensionListener.widthBy(widthBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator height(int height) {
        if (initDimensionListener()) {
            mDimensionListener.height(height);
        }
        return this;
    }

    public ViewPropertyObjectAnimator heightBy(int heightBy) {
        if (initDimensionListener()) {
            mDimensionListener.heightBy(heightBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator size(int size) {
        if (initDimensionListener()) {
            mDimensionListener.size(size);
        }
        return this;
    }

    public ViewPropertyObjectAnimator sizeBy(int sizeBy) {
        if (initDimensionListener()) {
            mDimensionListener.sizeBy(sizeBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator leftPadding(int leftPadding) {
        if (initPaddingListener()) {
            mPaddingListener.leftPadding(leftPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator leftPaddingBy(int leftPaddingBy) {
        if (initPaddingListener()) {
            mPaddingListener.leftPaddingBy(leftPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator topPadding(int topPadding) {
        if (initPaddingListener()) {
            mPaddingListener.topPadding(topPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator topPaddingBy(int topPaddingBy) {
        if (initPaddingListener()) {
            mPaddingListener.topPaddingBy(topPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator rightPadding(int rightPadding) {
        if (initPaddingListener()) {
            mPaddingListener.rightPadding(rightPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator rightPaddingBy(int rightPaddingBy) {
        if (initPaddingListener()) {
            mPaddingListener.rightPaddingBy(rightPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator bottomPadding(int bottomPadding) {
        if (initPaddingListener()) {
            mPaddingListener.bottomPadding(bottomPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator bottomPaddingBy(int bottomPaddingBy) {
        if (initPaddingListener()) {
            mPaddingListener.bottomPaddingBy(bottomPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator horizontalPadding(int horizontalPadding) {
        if (initPaddingListener()) {
            mPaddingListener.horizontalPadding(horizontalPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator horizontalPaddingBy(int horizontalPaddingBy) {
        if (initPaddingListener()) {
            mPaddingListener.horizontalPaddingBy(horizontalPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator verticalPadding(int verticalPadding) {
        if (initPaddingListener()) {
            mPaddingListener.verticalPadding(verticalPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator verticalPaddingBy(int verticalPaddingBy) {
        if (initPaddingListener()) {
            mPaddingListener.verticalPaddingBy(verticalPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator padding(int padding) {
        if (initPaddingListener()) {
            mPaddingListener.padding(padding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator paddingBy(int paddingBy) {
        if (initPaddingListener()) {
            mPaddingListener.paddingBy(paddingBy);
        }
        return this;
    }

    private boolean initMarginListener() {
        //we're initializing margin listener only when needed (it can cause an exception when there are no params)
        if (mMarginListener == null) {
            if (!hasView()) {
                return false;
            }
            mMarginListener = new MarginChangeListener(mView.get());
        }
        return true;
    }

    private boolean initDimensionListener() {
        //we're initializing dimension listener only when needed (it can cause an exception when there are no params)
        if (mDimensionListener == null) {
            if (!hasView()) {
                return false;
            }
            mDimensionListener = new DimensionChangeListener(mView.get());
        }
        return true;
    }

    private boolean initPaddingListener() {
        if (mPaddingListener == null) {
            if (!hasView()) {
                return false;
            }
            mPaddingListener = new PaddingChangeListener(mView.get());
        }
        return true;
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
            if (mMarginListener != null) {
                animator.addUpdateListener(mMarginListener);
            }
            if (mDimensionListener != null) {
                animator.addUpdateListener(mDimensionListener);
            }
            if (mPaddingListener != null) {
                animator.addUpdateListener(mPaddingListener);
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

    public void start() {
        get().start();
    }

}
