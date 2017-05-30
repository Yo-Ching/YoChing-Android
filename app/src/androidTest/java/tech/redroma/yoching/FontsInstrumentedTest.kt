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
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import tech.redroma.yoching.test.notNull

@RunWith(AndroidJUnit4::class)
class FontsInstrumentedTest
{

    private lateinit var context: Context

    @Before
    fun setUp()
    {
        context = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun testExoThin()
    {
        val font = context.exoThin()
        assertThat(font, notNull)
    }

    @Test
    fun testExoExtraLight()
    {
        val font = context.exoExtraLight()
        assertThat(font, notNull)
    }

    @Test
    fun testExoLight()
    {
        val font = context.exoLight()
        assertThat(font, notNull)
    }

    @Test
    fun testExoRegular()
    {
        val font = context.exoRegular()
        assertThat(font, notNull)
    }
    
    @Test
    fun testExoMedium()
    {
        val font = context.exoMedium()
        assertThat(font, notNull)
    }

    @Test
    fun testExoDemiBold()
    {
        val font = context.exoDemiBold()
        assertThat(font, notNull)
    }

    @Test
    fun testExoBold()
    {
        val font = context.exoBold()
        assertThat(font, notNull)
    }

    @Test
    fun testExoExtraBold()
    {
        val font = context.exoExtraBold()
        assertThat(font, notNull)
    }

    @Test
    fun testExoBlack()
    {
        val font = context.exoBlack()
        assertThat(font, notNull)
    }

    @Test
    fun testSignikaLight()
    {
        val font = context.signikaLight()
        assertThat(font, notNull)
    }

    @Test
    fun testSignikaRegular()
    {
        val font = context.signikaRegular()
        assertThat(font, notNull)
    }


    @Test
    fun testSignikaSemiBold()
    {
        val font = context.signikaSemiBold()
        assertThat(font, notNull)
    }

    @Test
    fun testSignikaBold()
    {
        val font = context.signikaBold()
        assertThat(font, notNull)
    }


}