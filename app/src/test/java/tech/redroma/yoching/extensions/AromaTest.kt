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

import org.junit.Test
import org.junit.runner.RunWith
import tech.sirwellington.alchemy.test.junit.runners.*

import org.junit.Assert.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import tech.redroma.yoching.BuildConfig
import tech.sirwellington.alchemy.arguments.assertions.alphabeticString
import tech.sirwellington.alchemy.generator.StringGenerators
import tech.sirwellington.alchemy.generator.one

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class AromaTest
{
    @Test
    fun testSend()
    {
        val message = one(StringGenerators.alphabeticStrings())

        Aroma.send {
            sendLowPriorityMessage(title = "Unit Test Run", body = message)
        }
    }


}