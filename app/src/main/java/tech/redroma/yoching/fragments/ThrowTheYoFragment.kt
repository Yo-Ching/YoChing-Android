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
import android.content.Intent
import android.view.*
import android.widget.*
import com.balysv.materialripple.MaterialRippleLayout
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.Techniques.*
import com.daimajia.androidanimations.library.YoYo
import tech.redroma.yoching.*
import tech.redroma.yoching.CoinResult.HEADS
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.WrexagramLine.SPLIT
import tech.redroma.yoching.activities.ReadActivity
import tech.redroma.yoching.animations.CoinAnimator
import tech.redroma.yoching.extensions.*
import java.util.concurrent.atomic.AtomicInteger


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

    override fun onResume()
    {
        super.onResume()
        player.reset()
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
        lateinit var prompt: TextView
        lateinit var line1: ImageView

        lateinit var line2: ImageView
        lateinit var line3: ImageView
        lateinit var line4: ImageView
        lateinit var line5: ImageView
        lateinit var line6: ImageView

        val lines get() = listOf(line1, line2, line3, line4, line5, line6)

        lateinit var coin1: ImageView
        lateinit var coin2: ImageView
        lateinit var coin3: ImageView
        lateinit var throwButton: Button
        lateinit var throwButtonContainer: MaterialRippleLayout

        val coins get() = listOf(coin1, coin2, coin3)

        fun inflate(view: View)
        {
            prompt = view.findViewById(R.id.yo_prompt) as TextView
            prompt.typeface = context.exoBlack()

            line1 = view.findView(R.id.line_1)
            line2 = view.findView(R.id.line_2)
            line3 = view.findView(R.id.line_3)
            line4 = view.findView(R.id.line_4)
            line5 = view.findView(R.id.line_5)
            line6 = view.findView(R.id.line_6)

            coin1 = view.findView(R.id.coin_1)
            coin2 = view.findView(R.id.coin_2)
            coin3 = view.findView(R.id.coin_3)
            throwButton = view.findView(R.id.throw_the_yo_button)
            throwButtonContainer = view.findView(R.id.throw_yo_container)

            styleElements()
            setListeners()
            showPrompt()
            hideWrexagramLines()
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

        fun showPrompt()
        {
            prompt.visibility = View.VISIBLE
            YoYo.with(Techniques.FadeInDown)
                    .duration(500)
                    .playOn(prompt)
        }

        fun hidePrompt()
        {
            prompt.hide()
        }

        fun hideWrexagramLines()
        {
            lines.forEach { it.hide() }
        }

        fun showAllWrexagramLines()
        {
            lines.forEach { it.show() }
        }

        fun showWrexagramLine(lineNumber: Int, lineType: WrexagramLine, animation: Techniques = FadeIn)
        {
            LOG.info("Showing Line $lineNumber with line type $lineType")
            val view = when (lineNumber)
            {
                1    -> line1
                2    -> line2
                3    -> line3
                4    -> line4
                5    -> line5
                6    -> line6
                else -> return
            }

            val lineDrawable = context.wrexagramLineFor(lineType)
            view.setImageDrawable(lineDrawable)
            view.show()

            YoYo.with(animation)
                    .duration(300)
                    .onStart { view.setImageDrawable(lineDrawable) }
                    .playOn(view)
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

        fun openWrexagram(number: Int)
        {
            if (!number.isValidWrexagramNumber)
                return

            LOG.info("Opening Wrexagram #$number")

            val intent = Intent(context, ReadActivity::class.java)
            intent.putExtra(ReadActivity.Parameters.WREXAGRAM_NUMBER, number)
            startActivity(intent)
            activity.overridePendingTransition(R.anim.enter_from_right, R.anim.abc_fade_out)
        }

    }

    private inner class Player
    {
        private var inFlight = false
        private var throwCount = 0
        private var wrexagram = mutableListOf<WrexagramLine>()
        private val acceptableAnimations = mutableListOf(BounceInLeft, FadeIn, FlipInX, Landing, FadeInDown, Landing)
        private var currentAnimation = acceptableAnimations.anyElement()

        fun throwTheYo()
        {
            if (inFlight)
            {
                return
            }

            inFlight = true

            if (throwCount >= 6)
                reset()

            if (throwCount == 0)
                views.hidePrompt()

            val landedCoins = AtomicInteger(0)
            val results = mutableListOf<CoinResult>()

            views.coins.forEach {

                flip(it) { result ->

                    results.add(result)

                    //All of the coins have landed
                    if (landedCoins.incrementAndGet() >= 3)
                    {
                        if (Settings.tapThatEnabled)
                        {
                            val randomWrexagram = Int.randomFrom(1, 64)
                            actions.openWrexagram(randomWrexagram)
                        }
                        else
                        {
                            processResults(results)
                        }
                    }
                }
            }
        }

        fun flip(coin: ImageView, onDone: (CoinResult) -> Unit)
        {
            val animator = CoinAnimator(context, coin)
            animator.onDone = onDone

            val delay = Int.randomFrom(1, 200).toLong()
            coin.postDelayed(animator, delay)
        }

        fun processResults(coinTossResults: List<CoinResult>)
        {
            LOG.info("Processing results: $coinTossResults")
            throwCount += 1

            val strongLine = coinTossResults.count { it == HEADS } >= 2
            val wrexagramLine = if (strongLine) WrexagramLine.STRONG else SPLIT
            wrexagram.add(wrexagramLine)

            views.showWrexagramLine(throwCount, wrexagramLine, animation = currentAnimation)

            if (throwCount == 6)
            {
                val wrexagramNumber = computeWrexagram(wrexagram)

                val openWrexagram = Runnable {
                    actions.openWrexagram(wrexagramNumber)
                }

                view?.postDelayed(openWrexagram, 700)
            }
            else
            {
                inFlight = false
            }
        }

        fun reset()
        {
            views.hideWrexagramLines()
            views.showPrompt()
            wrexagram.clear()
            throwCount = 0
            inFlight = false
            currentAnimation = acceptableAnimations.anyElement()
        }
    }
}