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

package tech.redroma.yoching.wrexagrams

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import tech.redroma.yoching.checkValidWrexNumber


/**
 *
 * @author SirWellington
 */


internal fun Context.idForWrexagramImage(wrexagramNumber: Int): Int?
{
    checkValidWrexNumber(wrexagramNumber)

    val name = "wrex_$wrexagramNumber"
    return resources.getIdentifier(name, "drawable", packageName)
}

internal fun Context.loadWrexagramImage(wrexagramNumber: Int): Bitmap?
{
    checkValidWrexNumber(wrexagramNumber)

    val id = idForWrexagramImage(wrexagramNumber) ?: return null

    return BitmapFactory.decodeResource(resources, id)
}