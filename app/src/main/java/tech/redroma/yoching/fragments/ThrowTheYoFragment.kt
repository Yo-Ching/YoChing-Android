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

import android.animation.AnimatorInflater
import android.os.Handler
import android.view.*
import android.widget.*
import com.balysv.materialripple.MaterialRippleLayout
import tech.redroma.yoching.*
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.animations.CoinAnimator
import tech.redroma.yoching.extensions.*


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
    private val player = Player()
    private val views = Views()

    override fun onCreate(savedInstanceState: android.os.Bundle?)
    {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: android.os.Bundle?): View?
    {
        val view = inflater?.inflate(layout.fragment_throw_the_yo, container, false)

        if (view != null)
        {
            views.inflate(view)
        }

        return view
    }

    override fun onAttach(context: android.content.Context?)
    {
        super.onAttach(context)

        if (context is ThrowTheYoFragment.ThrowTheYoListener)
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
        private lateinit var prompt: TextView
        private lateinit var coin1: ImageView
        private lateinit var coin2: ImageView
        private lateinit var coin3: ImageView
        private lateinit var throwButton: Button
        private lateinit var throwButtonContainer: MaterialRippleLayout

        val coins get() = listOf(coin1, coin2, coin3)

        fun inflate(view: View)
        {
            prompt = view.findViewById(R.id.yo_prompt) as TextView
            prompt.typeface = context.exoBlack()

            coin1 = view.findView(R.id.coin_1)
            coin2 = view.findView(R.id.coin_2)
            coin3 = view.findView(R.id.coin_3)
            throwButton = view.findView(R.id.throw_the_yo_button)
            throwButtonContainer = view.findView(R.id.throw_yo_container)

            styleElements()
            setListeners()
        }

        private fun styleElements()
        {
            coins.forEach {
                it.setImageDrawable(context.headsIcon)
            }
            throwButton.typeface = context.exoDemiBold()
        }

        private fun setListeners()
        {
            coins.forEach {
                it.setOnClickListener { actions.onCoinTapped(it) }
            }

            throwButton.isClickable = true
            throwButton.setOnClickListener {
                player.throwTheYo()
            }

        }
    }

    private inner class Actions
    {
        fun onCoinTapped(view: View)
        {
            listener?.onCoinTapped()
            Aroma.send { sendLowPriorityMessage("Coin Tapped") }

            val imageView = view as? ImageView ?: return
            val animation = AnimatorInflater.loadAnimator(context, R.animator.flip_coin) ?: return

            animation.setTarget(imageView)
            animation.start()

            val isHeads = Int.randomFrom(0, 10).isEven
            val coin = if (isHeads) context.headsIcon else context.tailsIcon
            imageView.setImageDrawable(coin)
        }

    }

    private inner class Player
    {
        fun throwTheYo()
        {
            views.coins.forEach(this::flip)

        }

        fun flip(coin: ImageView)
        {
            val animator = CoinAnimator(context, coin)
            coin.post(animator)
        }
    }
}