package tech.redroma.yoching

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

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import tech.redroma.yoching.test.notEmptyString
import tech.redroma.yoching.test.notNull

@RunWith(AndroidJUnit4::class)
class WrexagramSummariesKtTest
{
    private lateinit var context: Context

    @Before
    fun setup()
    {
        context = InstrumentationRegistry.getTargetContext()

    }

    @Test
    fun loadWrexagramSummary()
    {

        for (wrex in 1..64)
        {
            var summary = context.loadWrexagramSummary(wrex)
            assertThat(summary, notNull)
            summary = summary ?: return

            assertThat(summary.number, equalTo(wrex))
            assertThat(summary.title, notEmptyString)
            assertThat(summary.subTitle, notEmptyString)
            assertThat(summary.whatsUp, notEmptyString)
        }
    }

}