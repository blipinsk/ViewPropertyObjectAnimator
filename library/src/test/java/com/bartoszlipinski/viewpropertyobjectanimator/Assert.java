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

/**
 * Created by Bartosz Lipinski
 * 03.01.2016
 */
public class Assert {
    public static <T extends Exception> void assertThrows(AssertTest assertTest, Class<T> exceptionClass) {
        try {
            assertTest.perform();
            junit.framework.Assert.fail("No exception thrown.");
        } catch (Exception exception) {
            if (!exception.getClass().getCanonicalName().equals(exceptionClass.getCanonicalName())) {
                junit.framework.Assert.fail("Wrong exception type thrown.");
            }
        }
    }

    public static <T extends Exception> void assertDoesntThrow(AssertTest assertTest, Class<T> exceptionClass) {
        try {
            assertTest.perform();
        } catch (Exception exception) {
            if (exception.getClass().getCanonicalName().equals(exceptionClass.getCanonicalName())) {
                junit.framework.Assert.fail("Exception " + exceptionClass.getSimpleName() + " thrown.");
            }
        }
    }

    public static <T extends Exception> void assertDoesntThrowAny(AssertTest assertTest) {
        try {
            assertTest.perform();
        } catch (Exception exception) {
                junit.framework.Assert.fail("Exception thrown.");
        }
    }

}
