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

package tech.redroma.yoching.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.*
import android.widget.*
import tech.redroma.yoching.*
import tech.redroma.yoching.R.*
import tech.redroma.yoching.extensions.LOG
import tech.redroma.yoching.extensions.findView


/**
 *
 * @author SirWellington
 */
class NavigationMenuFragment : ListFragment()
{
    interface NavigationMenuListener
    {
        fun onSelectThrowTheYo()
        fun onSelect64Wrexagrams()
        fun onSelectSettings()
        fun onSelectBuyTheBook()
    }

    companion object
    {
        @JvmStatic
        fun newInstance(): NavigationMenuFragment
        {
            return NavigationMenuFragment()
        }
    }

    var listener: NavigationMenuListener? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(layout.fragment_navigation_menu, container, false) ?: return null

        listAdapter = NavigationAdapter(context)

        return view
    }

    override fun onAttach(context: Context?)
    {
        super.onAttach(context)

        if (context is NavigationMenuListener)
        {
            listener = context
        }
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long)
    {
        LOG.info("List item clicked at position $position")

        when (position)
        {
            0 -> listener?.onSelectThrowTheYo()
            1 -> listener?.onSelect64Wrexagrams()
            2 -> listener?.onSelectSettings()
            3 -> listener?.onSelectBuyTheBook()
        }
    }


}

private class NavigationAdapter(context: Context) : ArrayAdapter<Int>(context,
                                                                      layout.nav_item_template,
                                                                      (1..4).toList())
{

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
    {
        if (convertView != null)
        {
            return convertView
        }

        val view = inflater.inflate(layout.nav_item_template, parent, false)
        view.setTextForPosition(position)

        return view
    }

    private fun View.setTextForPosition(position: Int)
    {
        val textView: TextView = findView(R.id.text)
        textView.typeface = context.exoBold()

        val text = when (position)
        {
            0    -> context.getString(string.nav_throw_the_yo)
            1    -> context.getString(string.nav_64_wrexagrams)
            2    -> context.getString(string.nav_settings)
            else -> context.getString(string.nav_buy_the_book)
        }

        textView.text = text
    }

    private fun View.setIconForPosition(position: Int)
    {
        val iconView: ImageView = findView(R.id.icon)

        val resourceId = when (position)
        {
            0    -> drawable.nav_yo_bro
            1    -> drawable.nav_wrexagrams
            2    -> drawable.nav_settings
            else -> drawable.nav_buy
        }

        val icon = resources.getDrawable(resourceId)
        iconView.setImageDrawable(icon)
    }

}