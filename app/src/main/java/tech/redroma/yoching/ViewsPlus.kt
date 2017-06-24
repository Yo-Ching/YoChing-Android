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

import android.view.InflateException
import android.view.View
import android.widget.CheckedTextView
import com.daimajia.androidanimations.library.Techniques.BounceIn
import com.daimajia.androidanimations.library.YoYo


/**
 *
 * @author SirWellington
 */

internal inline fun <reified T : View> View.findView(id: Int): T
{
    return findViewById(id) as? T ?: {
        val message = "Could not find a View with ID [$id] of type ${T::class.java}"
        LOG.warn(message)
        throw InflateException(message)
    }()
}


fun CheckedTextView.tap(): CheckedTextView
{
    toggle()
    adjustCheckMark()
    return this
}

fun CheckedTextView.adjustCheckMark(): CheckedTextView
{
    checkMarkDrawable = if (isChecked) resources.getDrawable(R.drawable.yo_check) else null
    return this
}

fun CheckedTextView.shake(): CheckedTextView
{
    YoYo.with(BounceIn)
            .duration(200)
            .playOn(this)

    return this
}