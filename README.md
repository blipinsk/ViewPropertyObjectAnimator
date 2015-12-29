ViewPropertyObjectAnimator
==================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ViewPropertyObjectAnimator-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2625)
[![License](https://img.shields.io/github/license/blipinsk/RecyclerViewHeader.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/com.bartoszlipinski/viewpropertyobjectanimator.svg)](http://gradleplease.appspot.com/#viewpropertyobjectanimator)

Wrapper of the `ObjectAnimator` that can be used similarly to `ViewPropertyAnimator`.

`ViewPropertyObjectAnimator` is as easy to use as `ViewPropertyAnimator` and (unlike `ViewPropertyAnimator`) lets you utilize all the awesome features that `ObjectAnimator` allows (e.g. it can be used inside `AnimatorSet`).

Additionally `ViewPropertyObjectAnimator` lets you animate `View's` `dimensions`, `padding`, `margin` and `scroll`.

`ViewPropertyObjectAnimator` can be also used to animate `percent` parameters from [Percent Support Library ](http://developer.android.com/tools/support-library/features.html#percent).

Usage
=====

To obtain an `ObjectAnimator` object:

  1. Use the static `animate(View view)` method of the `ViewPropertyObjectAnimator`
  2. Call specific `ViewPropertyObjectAnimator` methods to setup an animation of desired properties (just like you would when using `ViewPropertyAnimator`)
  3. Call `get()` method, like so:

        ObjectAnimator animator = ViewPropertyObjectAnimator
                .animate(mView)
                .withLayer()
                .alpha(0f)
                .scaleX(0f)
                .scaleY(0f)
                .scrollY(100)
                .height(200)
                .topPaddingBy(10)
                .setDuration(300)
                .setInterpolator(new AnticipateInterpolator())
                .get();


Including In Your Project
-------------------------
You can grab the library via Maven Central. Just add a proper dependency inside your `build.gradle`. Like this:

```xml
dependencies {
    compile 'com.bartoszlipinski:viewpropertyobjectanimator:1.4.4'
}
```

Developed by
============
 * Bartosz Lipiński

License
=======

    Copyright 2015 Bartosz Lipiński
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
