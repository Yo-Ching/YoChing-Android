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

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tech.redroma.yoching.R.drawable
import tech.redroma.yoching.R.id
import tech.redroma.yoching.Settings
import tech.redroma.yoching.WrexagramLine
import tech.redroma.yoching.WrexagramLine.SPLIT
import tech.redroma.yoching.WrexagramLine.STRONG

object LOG : Logger by LoggerFactory.getLogger(LOG::class.java)

internal val AppCompatActivity.actionBarTitleView: TextView? get()
{
    return this.findView(id.yo_action_bar_title)
}

/**
 *
 * @author SirWellington
 */
internal fun AppCompatActivity.setActionBarFont(typeface: Typeface, size: Int)
{
    val titleView = actionBarTitleView ?: return
    titleView.typeface = typeface
    titleView.textSize = size.toFloat()
}

internal fun AppCompatActivity.setActionBarTitle(title: String)
{
    val titleView = actionBarTitleView ?: return
    titleView.text = title
}

internal fun AppCompatActivity.perform(block: AppCompatActivity.() -> Unit)
{
    block(this)
}

internal inline fun <reified V : View> AppCompatActivity.findView(id: Int): V
{
    return findViewById(id) as V
}

private object Icons
{
    lateinit var headsIconSlick: Drawable
    lateinit var tailsIconSlick: Drawable
    lateinit var headsIconStreet: Drawable
    lateinit var tailsIconStreet: Drawable

    lateinit var strongLine: Drawable
    lateinit var splitLine: Drawable

    var isInitialized = false

    fun initialize(context: Context)
    {
        if (isInitialized)
        {
            return
        }

        headsIconSlick = context.resources.getDrawable(drawable.coin_heads_slick)
        tailsIconSlick = context.resources.getDrawable(drawable.coin_tails_slick)
        headsIconStreet = context.resources.getDrawable(drawable.coin_heads_street)
        tailsIconStreet = context.resources.getDrawable(drawable.coin_tails_street)

        strongLine = context.resources.getDrawable(drawable.wrex_strong_line)
        splitLine = context.resources.getDrawable(drawable.wrex_split_line)

        isInitialized = true
    }
}

val Context.headsIcon: Drawable
    get()
    {
        Icons.initialize(this)
        Settings.init(this)

        return if (Settings.slickCoinsEnabled) Icons.headsIconSlick else Icons.headsIconStreet
    }

val Context.tailsIcon: Drawable
    get()
    {
        Icons.initialize(this)
        Settings.init(this)

        return if (Settings.slickCoinsEnabled) Icons.tailsIconSlick else Icons.tailsIconStreet
    }

val Context.strongLine: Drawable
    get()
    {
        Icons.initialize(this)
        return Icons.strongLine
    }

val Context.splitLine: Drawable
    get()
    {
        Icons.initialize(this)
        return Icons.splitLine
    }

fun Context.wrexagramLineFor(lineType: WrexagramLine): Drawable
{
    return when(lineType)
    {
        STRONG -> strongLine
        SPLIT -> splitLine
    }
}

fun Context.openURL(url: String)
{
    if (url.isEmpty()) return

    val uri = Uri.parse(url)

    Aroma.send { sendHighPriorityMessage("Opening Link", url) }

    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}

fun hasSDKAtLeast(expectedSDKVersion: Int): Boolean
{
    return android.os.Build.VERSION.SDK_INT >= expectedSDKVersion
}

fun isAtLeastKitKat(): Boolean = hasSDKAtLeast(android.os.Build.VERSION_CODES.KITKAT)
fun isAtLeastLollipop(): Boolean = hasSDKAtLeast(android.os.Build.VERSION_CODES.LOLLIPOP)