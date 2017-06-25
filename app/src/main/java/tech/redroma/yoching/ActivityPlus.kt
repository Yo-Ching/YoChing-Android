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

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LOG : Logger by LoggerFactory.getLogger(LOG::class.java)

internal val AppCompatActivity.actionBarTitleView: TextView? get()
{
    return this.findView(R.id.yo_action_bar_title)
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

internal inline fun <reified V: View> AppCompatActivity.findView(id: Int): V
{
    return findViewById(id) as V
}

private object Icons
{
    lateinit var headsIconSlick: Drawable
    lateinit var tailsIconSlick: Drawable
    lateinit var headsIconStreet: Drawable
    lateinit var tailsIconStreet: Drawable

    var isInitialized = false

    fun initialize(context: Context)
    {
        if (isInitialized)
        {
            return
        }

        headsIconSlick = context.resources.getDrawable(R.drawable.coin_heads_slick)
        tailsIconSlick = context.resources.getDrawable(R.drawable.coin_tails_slick)
        headsIconStreet = context.resources.getDrawable(R.drawable.coin_heads_street)
        tailsIconStreet = context.resources.getDrawable(R.drawable.coin_tails_street)

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