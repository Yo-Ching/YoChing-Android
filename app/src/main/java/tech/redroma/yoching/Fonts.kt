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
import android.graphics.Paint
import android.graphics.Typeface
import android.support.v4.util.LruCache
import android.text.TextPaint
import android.text.style.MetricAffectingSpan


/**
 *
 * @author SirWellington
 */

private object FontCache: MutableMap<String, Typeface> by mutableMapOf()

fun Context.exoThin() = loadTypeface("fonts/Exo 100.ttf")!!
fun Context.exoExtraLight() = loadTypeface("fonts/Exo 200.ttf")!!
fun Context.exoLight() = loadTypeface("fonts/Exo 300.ttf")!!
fun Context.exoRegular() = loadTypeface("fonts/Exo regular.ttf")!!
fun Context.exoMedium() = loadTypeface("fonts/Exo 500.ttf")!!
fun Context.exoDemiBold() = loadTypeface("fonts/Exo 600.ttf")!!
fun Context.exoBold() = loadTypeface("fonts/Exo 700.ttf")!!
fun Context.exoExtraBold() = loadTypeface("fonts/Exo 800.ttf")!!
fun Context.exoBlack() = loadTypeface("fonts/Exo 900.ttf")!!

fun Context.signikaLight() = loadTypeface("fonts/Signika 300.ttf")!!
fun Context.signikaRegular() = loadTypeface("fonts/Signika regular.ttf")!!
fun Context.signikaSemiBold() = loadTypeface("fonts/Signika 600.ttf")!!
fun Context.signikaBold() = loadTypeface("fonts/Signika 700.ttf")!!


private fun Context.loadTypeface(path: String): Typeface?
{
    if (FontCache.containsKey(path))
    {
        return FontCache[path]
    }

    val assets = this.applicationContext?.assets

    if (assets == null)
    {
        LOG.info("Assets is null")
        return null
    }

    val font = Typeface.createFromAsset(assets, path) ?: null

    if (font == null)
    {
        LOG.info("Font is null")
        return null
    }

    FontCache[path] = font

    return font
}


internal class TypefaceSpan(val typeface: Typeface) : MetricAffectingSpan()
{
    private companion object
    {
        val cache = LruCache<String, Typeface>(12)
    }

    override fun updateMeasureState(p: TextPaint?)
    {
        if (p == null)
        {
            return
        }

        p.typeface = this.typeface
        p.flags = p.flags or Paint.SUBPIXEL_TEXT_FLAG
    }

    override fun updateDrawState(tp: TextPaint?)
    {
        if (tp == null)
        {
            return
        }

        tp.typeface = this.typeface
        tp.flags = tp.flags or Paint.SUBPIXEL_TEXT_FLAG
    }

}