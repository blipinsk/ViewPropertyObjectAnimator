/**
 * Copyright 2016 Bartosz Lipinski
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
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.bartoszlipinski.viewpropertyobjectanimator.Assert.assertDoesntThrowAny;
import static com.bartoszlipinski.viewpropertyobjectanimator.Assert.assertThrows;
import static junit.framework.Assert.assertEquals;


/**
 * Created by Bartosz Lipinski
 * 03.01.2016
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DimensionChangeTests {

    private static final float FLOAT_TEST_VALUE = 987.65f;
    private static final int INT_TEST_VALUE = 123;
    private static final int WIDTH_SOURCE_TEST_VALUE = 234;
    private static final int HEIGHT_SOURCE_TEST_VALUE = 345;
    private static final int WIDTH_TARGET_TEST_VALUE = 456;
    private static final int HEIGHT_TARGET_TEST_VALUE = 567;
    private static final int SIZE_TARGET_TEST_VALUE = 678;

    private View mNoParamsView;
    private View mMatchParentView;
    private View mWithParamsView;

    @Before
    public void prepareTestViews() {
        mNoParamsView = new View(RuntimeEnvironment.application);
        mWithParamsView = new View(RuntimeEnvironment.application);
        mWithParamsView.setLayoutParams(new ViewGroup.LayoutParams(WIDTH_SOURCE_TEST_VALUE, HEIGHT_SOURCE_TEST_VALUE));
        mMatchParentView = new View(RuntimeEnvironment.application);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMatchParentView.setLayoutParams(params);
        mMatchParentView.layout(0, 0, WIDTH_SOURCE_TEST_VALUE, HEIGHT_SOURCE_TEST_VALUE);
    }

    @Test
    public void validateListener() {
        assertThrows(new AssertTest() {
            @Override
            public void perform() {
                new DimensionChangeListener(mNoParamsView);
            }
        }, IllegalStateException.class);
        assertDoesntThrowAny(new AssertTest() {
            @Override
            public void perform() {
                new DimensionChangeListener(mWithParamsView);
            }
        });

        DimensionChangeListener matchParentListener = new DimensionChangeListener(mMatchParentView);
        assertEquals(matchParentListener.currentWidth(), WIDTH_SOURCE_TEST_VALUE);
        assertEquals(matchParentListener.currentHeight(), HEIGHT_SOURCE_TEST_VALUE);

        DimensionChangeListener withParamsListener = new DimensionChangeListener(mWithParamsView);
        assertEquals(withParamsListener.currentWidth(), WIDTH_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.currentHeight(), HEIGHT_SOURCE_TEST_VALUE);

        withParamsListener.height(HEIGHT_TARGET_TEST_VALUE);
        assertEquals(withParamsListener.mHeight.mFrom, HEIGHT_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.mHeight.mTo, HEIGHT_TARGET_TEST_VALUE);

        withParamsListener.heightBy(HEIGHT_TARGET_TEST_VALUE);
        assertEquals(withParamsListener.mHeight.mFrom, HEIGHT_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.mHeight.mTo, HEIGHT_SOURCE_TEST_VALUE + HEIGHT_TARGET_TEST_VALUE);

        withParamsListener.width(WIDTH_TARGET_TEST_VALUE);
        assertEquals(withParamsListener.mWidth.mFrom, WIDTH_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.mWidth.mTo, WIDTH_TARGET_TEST_VALUE);

        withParamsListener.widthBy(WIDTH_TARGET_TEST_VALUE);
        assertEquals(withParamsListener.mWidth.mFrom, WIDTH_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.mWidth.mTo, WIDTH_SOURCE_TEST_VALUE + WIDTH_TARGET_TEST_VALUE);

        withParamsListener.size(SIZE_TARGET_TEST_VALUE);
        assertEquals(withParamsListener.mWidth.mFrom, WIDTH_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.mHeight.mFrom, HEIGHT_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.mWidth.mTo, SIZE_TARGET_TEST_VALUE);
        assertEquals(withParamsListener.mHeight.mTo, SIZE_TARGET_TEST_VALUE);

        withParamsListener.sizeBy(SIZE_TARGET_TEST_VALUE);
        assertEquals(withParamsListener.mWidth.mFrom, WIDTH_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.mHeight.mFrom, HEIGHT_SOURCE_TEST_VALUE);
        assertEquals(withParamsListener.mWidth.mTo, WIDTH_SOURCE_TEST_VALUE + SIZE_TARGET_TEST_VALUE);
        assertEquals(withParamsListener.mHeight.mTo, HEIGHT_SOURCE_TEST_VALUE + SIZE_TARGET_TEST_VALUE);
    }

    @Test
    public void validateAnimatorMethods() {
    }

    private void clearDimensionListener(ViewPropertyObjectAnimator animator) {
        animator.mDimensionListener = null;
    }
}
