package tech.redroma.yoching.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.plattysoft.leonids.ParticleSystem
import tech.redroma.yoching.*
import tech.redroma.yoching.extensions.*

class CreditsActivity : AppCompatActivity()
{

    private val views = Views()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)

        views.inflate()
    }

    override fun finish()
    {
        super.finish()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }

    private inner class Views
    {
        lateinit var yoActionBar: Toolbar
        lateinit var yoActionBarTitle: TextView

        lateinit var truePlayer: TextView
        lateinit var truePlayerDescription: TextView
        lateinit var truePlayerContainer: ViewGroup

        lateinit var hugh: TextView
        lateinit var hughDescription: TextView
        lateinit var hughContainer: ViewGroup

        lateinit var wellington: TextView
        lateinit var wellingtonDescription: TextView
        lateinit var wellingtonContainer: ViewGroup

        lateinit var marc: TextView
        lateinit var marcDescription: TextView
        lateinit var marcContainer: ViewGroup

        lateinit var brendan: TextView
        lateinit var brendanDescription: TextView
        lateinit var brendanContainer: ViewGroup

        lateinit var maya: TextView
        lateinit var mayaDescription: TextView
        lateinit var mayaContainer: ViewGroup

        fun inflate()
        {
            yoActionBar = findView(R.id.yo_action_bar)
            yoActionBarTitle = findView(R.id.yo_action_bar_title)

            truePlayer = findView(R.id.true_player)
            truePlayerDescription = findView(R.id.true_player_description)
            truePlayerContainer = findView(R.id.true_player_container)

            hugh = findView(R.id.hugh)
            hughDescription = findView(R.id.hugh_description)
            hughContainer = findView(R.id.hugh_container)

            wellington = findView(R.id.wellington)
            wellingtonDescription = findView(R.id.wellington_description)
            wellingtonContainer = findView(R.id.wellington_container)

            marc = findView(R.id.marc)
            marcDescription = findView(R.id.marc_description)
            marcContainer = findView(R.id.marc_container)

            brendan = findView(R.id.brendan)
            brendanDescription = findView(R.id.brendan_description)
            brendanContainer = findView(R.id.brendan_container)

            maya = findView(R.id.maya)
            mayaDescription = findView(R.id.maya_description)
            mayaContainer = findView(R.id.maya_container)

            setupBackButton()
            setFonts()
            setListeners()
            animateCreditsIn()
        }

        fun setupBackButton()
        {
            //Action bar code
            setSupportActionBar(yoActionBar)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = ""

            val backButtonIcon = resources.getDrawable(R.drawable.arrow_back)
            supportActionBar?.setHomeAsUpIndicator(backButtonIcon)

            val backButton = yoActionBar.backButton ?: return

            backButton.setOnClickListener {
                LOG.info("Back Button clicked!")
                finish()
            }

            if (hasSDKAtLeast(android.os.Build.VERSION_CODES.LOLLIPOP))
            {
                backButton.setBackgroundDrawable(resources.getDrawable(R.drawable.ripple_circular))
            }

        }

        fun setFonts()
        {
            val titleFont = exoDemiBold()
            val subtitleFont = exoRegular()

            listOf(truePlayer, hugh, wellington, marc, brendan, maya)
                    .forEach { it.typeface = titleFont }

            listOf(truePlayerDescription, hughDescription, wellingtonDescription, marcDescription, brendanDescription, mayaDescription)
                    .forEach { it.typeface = subtitleFont }

            yoActionBarTitle.typeface = exoBlack()
        }

        fun setListeners()
        {
            truePlayerContainer.setOnClickListener { openURL(Links.truePlayerLink) }
            hughContainer.setOnClickListener { openURL(Links.hughLink) }
            wellingtonContainer.setOnClickListener { openURL(Links.wellingtonLink) }
            marcContainer.setOnClickListener { openURL(Links.marcLink) }
            brendanContainer.setOnClickListener { openURL(Links.brendanLink) }
            mayaContainer.setOnClickListener { openURL(Links.mayaLink) }
        }


        fun animateCreditsIn()
        {
            val step = 250L
            var delay = 50L

            truePlayer.post {

                listOf(maya, brendan, marc, wellington, hugh, truePlayer)
                        .onEach { it.hide() }
                        .onEach { it.animateCreditsIn(delay); delay += step }

            }

        }

        private fun View.animateCreditsIn(delay: Long)
        {
            val technique = Techniques.SlideInLeft
            val duration = 800L

            val showInTime = {

                postDelayed({ this.show() }, delay + 150)
            }

            YoYo.with(technique)
                    .delay(delay)
                    .duration(duration)
                    .onStart { showInTime(); boom(this, delay + duration + 100) }
                    .playOn(this)

        }

        private fun boom(view: View, delay: Long)
        {
            val action = Runnable {

                ParticleSystem(this@CreditsActivity, 200, R.drawable.particle, 2000)
                        .setSpeedModuleAndAngleRange(0.01f, 0.3f, 180, 360)
                        .setScaleRange(0.2f, 0.4f)
                        .setFadeOut(1000)
                        .oneShot(view, 75)
            }

            view.postDelayed(action, delay)
        }

    }

}
