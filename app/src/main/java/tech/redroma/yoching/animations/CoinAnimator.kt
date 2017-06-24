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

package tech.redroma.yoching.animations

import android.animation.*
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.content.res.Resources
import android.widget.ImageView
import tech.redroma.yoching.*


/**
 *
 * @author SirWellington
 */

class CoinAnimator(val context: Context, val imageView: ImageView) : Runnable
{

    val animation = AnimatorInflater.loadAnimator(context, R.animator.coin_toss_animator)!!
    private val HEIGHT_PERCENTAGE = 0.5

    val coinRotateAnimation: ObjectAnimator?
        get()
        {
            val set = animation as? AnimatorSet ?: return null
            return set.childAnimations.firstOrNull() as? ObjectAnimator
        }

    val movementAnimation: ObjectAnimator?
        get()
        {
            val set = animation as? AnimatorSet?: return null
            return set.childAnimations.lastOrNull() as? ObjectAnimator
        }

    var isHeads = true
    val isTails get() = !isHeads

    override fun run()
    {
        adjustAnimationHeight()
        animation.setTarget(imageView)
        addListeners()
        animation.start()
    }


    private fun addListeners()
    {

        coinRotateAnimation?.addUpdateListener { animator ->

            val fraction = animator.animatedFraction
            if (fraction in 0.25..0.75)
            {
                setToTails()
                isHeads = false
            }
            else
            {
                setToHeads()
                isHeads = true
            }

        }

        coinRotateAnimation?.addListener(AnimationEndListener())
    }

    private fun adjustAnimationHeight()
    {
        val animation = movementAnimation ?: return
        val height = Resources.getSystem().displayMetrics.heightPixels

        val scaleY = height.toDouble() * HEIGHT_PERCENTAGE
        val value = animation.values.firstOrNull() ?: return
        LOG.info("Value is $value")
        value.setFloatValues(0f, -(scaleY.toFloat()))
        LOG.info("Value is now $value")
    }

    private fun setToHeads()
    {
        imageView.setImageDrawable(context.headsIcon)
    }

    private fun setToTails()
    {
        imageView.setImageDrawable(context.tailsIcon)
    }

    private inner class AnimationEndListener : AnimatorListener
    {
        private val wasClickable = imageView.isClickable

        override fun onAnimationRepeat(animation: Animator?)
        {

        }

        override fun onAnimationEnd(animation: Animator?)
        {
            imageView.isClickable = wasClickable

            isHeads = Math.random() > 0.5

            if (isHeads)
            {
                setToHeads()
            }
            else
            {
                setToTails()
            }
        }

        override fun onAnimationCancel(animation: Animator?)
        {
            imageView.isClickable = wasClickable
        }

        override fun onAnimationStart(animation: Animator?)
        {
            imageView.isClickable = false
        }

    }

}
