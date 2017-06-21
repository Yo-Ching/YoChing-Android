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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.squareup.picasso.Picasso
import tech.redroma.yoching.R
import tech.redroma.yoching.headsIcon
import tech.redroma.yoching.tailsIcon


/**
 *
 * @author SirWellington
 */

class CoinAnimator(val context: Context, val imageView: ImageView) : Runnable
{

    val animation = AnimatorInflater.loadAnimator(context, R.animator.coin_toss_animator)!!

    val coinFlipAnimation: ObjectAnimator?
        get()
        {
            val set = animation as? AnimatorSet ?: return null
            return set.childAnimations.lastOrNull() as? ObjectAnimator
        }

    var isHeads = true
    val isTails get() = !isHeads

    override fun run()
    {
        animation.setTarget(imageView)
        addListeners()
        animation.start()
    }

    private fun addListeners()
    {

        coinFlipAnimation?.addUpdateListener { animator ->

            if (animator.animatedFraction >= 0.25 && isHeads)
            {
                setToTails()
            }

            if (animator.animatedFraction >= 0.75 && isTails)
            {
                setToHeads()
            }

            isHeads = !isHeads
        }

        coinFlipAnimation?.addListener(AnimationEndListener())
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
