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

package tech.redroma.yoching

import android.content.Context
import android.content.SharedPreferences


/**
 *
 * @author SirWellington
 */
object Settings
{
    lateinit var preferences: SharedPreferences
    private var initialized = false

    fun init(context: Context)
    {

        if (initialized)
        {
            return
        }

        preferences = context.getSharedPreferences(Keys.NAMESPACE, Context.MODE_PRIVATE)
        initialized = true
    }

    var truePlayerEnabled: Boolean
        get() = preferences.getBoolean(Keys.TRUE_PLAYER, true)
        set(value)
        {
            preferences.edit()
                    .putBoolean(Keys.TRUE_PLAYER, value)
                    .putBoolean(Keys.TAP_THAT, !value)
                    .apply()
        }

    var tapThatEnabled: Boolean
        get() = !truePlayerEnabled
        set(value)
        {
            truePlayerEnabled = !value
        }

    var streetCoinsEnabled: Boolean
        get() = !slickCoinsEnabled
        set(value)
        {
            slickCoinsEnabled = !value
        }

    var slickCoinsEnabled: Boolean
        get() = preferences.getBoolean(Keys.SLICK, true)
        set(value)
        {
            preferences.edit()
                    .putBoolean(Keys.SLICK, value)
                    .putBoolean(Keys.STREET, !value)
                    .apply()
        }
}

val Context.preferences: SharedPreferences
    get()
    {
        Settings.init(this)
        return Settings.preferences
    }


private object Keys
{
    const val NAMESPACE = "YoChing"

    const val TRUE_PLAYER = "true_player"
    const val TAP_THAT = "tap_that"
    const val STREET = "street"
    const val SLICK = "slick"
}