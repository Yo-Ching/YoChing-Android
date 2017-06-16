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

import tech.redroma.yoching.exoBlack


/**
 *
 * @author SirWellington
 */
class ThrowTheYoFragment : android.support.v4.app.Fragment()
{
    interface ThrowTheYoListener
    {
        fun onCoinTapped()
    }

    companion object
    {
        @JvmStatic
        fun newInstance(): tech.redroma.yoching.fragments.ThrowTheYoFragment
        {
            return tech.redroma.yoching.fragments.ThrowTheYoFragment()
        }
    }

    var listener: tech.redroma.yoching.fragments.ThrowTheYoFragment.ThrowTheYoListener? = null
    private val actions = Actions()
    private val views = Views()

    override fun onCreate(savedInstanceState: android.os.Bundle?)
    {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View?
    {
        val view = inflater?.inflate(tech.redroma.yoching.R.layout.fragment_throw_the_yo, container, false)

        if (view != null)
        {
            views.inflate(view)
        }

        return view
    }

    override fun onAttach(context: android.content.Context?)
    {
        super.onAttach(context)

        if (context is tech.redroma.yoching.fragments.ThrowTheYoFragment.ThrowTheYoListener)
        {
            listener = context
        }
    }

    override fun onDetach()
    {
        super.onDetach()
        listener = null
    }

    private inner class Views
    {
        private lateinit var prompt: android.widget.TextView
        private lateinit var coin1: android.widget.ImageView
        private lateinit var coin2: android.widget.ImageView
        private lateinit var coin3: android.widget.ImageView

        fun inflate(view: android.view.View)
        {
            prompt = view.findViewById(tech.redroma.yoching.R.id.yo_prompt) as android.widget.TextView
            prompt.typeface = context.exoBlack()

            coin1 = view.findViewById(tech.redroma.yoching.R.id.coin_1) as android.widget.ImageView
            coin2 = view.findViewById(tech.redroma.yoching.R.id.coin_2) as android.widget.ImageView
            coin3 = view.findViewById(tech.redroma.yoching.R.id.coin_3) as android.widget.ImageView

            listOf(coin1, coin2, coin3).forEach { it.setOnClickListener { actions.onCoinTapped(it) } }
        }
    }

    private inner class Actions
    {
        fun onCoinTapped(view: android.view.View)
        {
            listener?.onCoinTapped()

            tech.redroma.yoching.Aroma.send { sendLowPriorityMessage("Coin Tapped") }
        }
    }
}