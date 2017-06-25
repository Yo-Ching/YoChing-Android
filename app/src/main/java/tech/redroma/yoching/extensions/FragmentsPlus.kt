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

package tech.redroma.yoching.extensions

import android.support.v4.app.Fragment
import android.view.InflateException
import android.view.View


/**
 *
 * @author SirWellington
 */


internal fun Fragment.perform(block: Fragment.() -> Unit)
{
    block(this)
}

internal inline fun <reified T: View> Fragment.findViewById(id: Int): T
{
    val view = view ?:
    {
        LOG.warn("Missing view!")
        throw InflateException("Missing View!")
    }()

    return view.findView(id)
}
