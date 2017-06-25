package tech.redroma.yoching.extensions

/*
 * Copyright 2017 RedRoma, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.test.AndroidTestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import tech.redroma.yoching.BuildConfig
import tech.sirwellington.alchemy.arguments.assertions.greaterThanOrEqualTo
import tech.sirwellington.alchemy.arguments.assertions.lessThanOrEqualTo
import tech.sirwellington.alchemy.arguments.checkThat
import tech.sirwellington.alchemy.generator.NumberGenerators.Companion.integers
import tech.sirwellington.alchemy.generator.one
import tech.sirwellington.alchemy.test.junit.ThrowableAssertion.assertThrows

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class IntegersPlusTest : AndroidTestCase()
{

    private var validWrexagram: Int = 0
    private var invalidWrexagramNumber: Int = 0

    @Before
    fun setup()
    {
        validWrexagram = one(integers(1, 65))
        invalidWrexagramNumber = one(integers(65, Int.MAX_VALUE))
    }

    @Test
    fun testCheckValidWrexNumber()
    {
        checkValidWrexNumber(validWrexagram.toInt())

        assertThrows {
            checkValidWrexNumber(invalidWrexagramNumber.toInt())
        }
    }

    @Test
    fun testRandomFrom()
    {
        val min = one(integers(1, 10))
        val max = one(integers(20, Int.MAX_VALUE))

        val result = Int.randomFrom(min, max)
        checkThat(result)
                .isA(greaterThanOrEqualTo(min))
                .isA(lessThanOrEqualTo(max))
    }

}