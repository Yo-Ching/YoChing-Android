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

package tech.redroma.yoching.activities

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.*
import android.widget.*
import tech.redroma.yoching.*
import tech.redroma.yoching.R.string


/**
 *
 * @author SirWellington
 */
class NavigationMenuFragment: ListFragment()
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
        val view = inflater?.inflate(R.layout.fragment_navigation_menu, container, false) ?: return null

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
    }


}

private class NavigationAdapter: ArrayAdapter<String>
{

    private val inflater = LayoutInflater.from(context)

    constructor(context: Context) : super(context, R.layout.nav_item_template, listOf("1", "2", "3", "4"))

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
    {
        if (convertView != null)
        {
            return convertView
        }


        val view = inflater.inflate(R.layout.nav_item_template, parent, false)
        view.setTextForPosition(position)
        view.setIconForPosition(position)

        return view
    }

    private fun View.setTextForPosition(position: Int)
    {
        val text = findViewById(R.id.text) as? TextView ?: return
        text.typeface = context.exoDemiBold()

        when (position)
        {
            0 -> text.text = context.getString(string.nav_throw_the_yo)
            1 -> text.text = context.getString(string.nav_64_wrexagrams)
            2 -> text.text = context.getString(string.nav_settings)
            else -> text.text = context.getString(string.nav_buy_the_book)
        }
    }

    private fun View.setIconForPosition(position: Int)
    {
        val iconView = findViewById(R.id.icon) as? ImageView  ?: return

        val resourceId = when (position)
        {
            0 -> R.drawable.nav_yo_bro
            1 -> R.drawable.nav_wrexagrams
            2 -> R.drawable.nav_settings
            else -> R.drawable.nav_buy
        }

        val icon = resources.getDrawable(resourceId)
        iconView.setImageDrawable(icon)
    }

}