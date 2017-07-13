package tech.redroma.yoching.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import com.bluejamesbond.text.DocumentView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.plattysoft.leonids.ParticleSystem
import com.squareup.picasso.Picasso
import tech.redroma.yoching.*
import tech.redroma.yoching.R.*
import tech.redroma.yoching.extensions.*
import tech.redroma.yoching.wrexagrams.*
import tyrantgit.explosionfield.ExplosionField
import java.util.*
import java.util.concurrent.TimeUnit

class ReadActivity : AppCompatActivity()
{
    private val DEFAULT_SUMMARY = WrexagramSummary(number = 1, title = "BRING IT", subTitle = "", whatsUp = "")

    object Parameters
    {
        const val WREXAGRAM_NUMBER = "wrexagram_number"
    }

    private var wrexagramNumber = 8
    private lateinit var wrexagram: WrexagramSummary

    private val views = Views()

    private val startTime = Date()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_read_wrexagram)

        wrexagramNumber = intent.getIntExtra(Parameters.WREXAGRAM_NUMBER, wrexagramNumber)
        LOG.info("Loading Wrexagram #$wrexagramNumber")

        views.inflate()
        explodeIntoView()
        loadWrexagramInfo()

        Aroma.send {
            sendMediumPriorityMessage("Wrexagram Viewed", body = "#$wrexagramNumber - ${wrexagram.title}")
        }
    }

    override fun onPause()
    {
        Aroma.send { sendMediumPriorityMessage("Wrexagram Read", "#$wrexagramNumber - ${wrexagram.title} for ${startTime.secondsSinceNow} seconds.") }

        super.onPause()
    }

    private fun explodeIntoView()
    {
        views.wrexagramTitle.hide()

        val explosion = if (isAtLeastKitKat())
        {
            Runnable {
                ExplosionField.attach2Window(this).explode(views.duplicateTitle)
            }
        }
        else
        {
            Runnable {

                ParticleSystem(this, 200, drawable.particle_black, 900)
                        .setSpeedModuleAndAngleRange(0.01f, 0.3f, 180, 360)
                        .setScaleRange(0.2f, 0.4f)
                        .setFadeOut(700)
                        .oneShot(views.wrexagramTitle, 75)
            }
        }

        val delayForExplosion: Long = if (isAtLeastKitKat()) 30 else 100

        val animation = Runnable {

            views.wrexagramTitle.show()

            YoYo.with(Techniques.BounceInDown)
                    .duration(400)
                    .playOn(views.wrexagramTitle)

            views.wrexagramTitle.postDelayed(explosion, delayForExplosion)
        }

        views.wrexagramTitle.postDelayed(animation, 300)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        LOG.info("Option selected")
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun loadWrexagramInfo()
    {
        views.actionBarTitle.text = "WREXAGRAM $wrexagramNumber"

        wrexagram = applicationContext.loadWrexagramSummary(wrexagramNumber) ?: DEFAULT_SUMMARY

        views.wrexagramTitle.text = wrexagram.title

        val body = applicationContext.loadWrexagramBody(wrexagramNumber)
        views.body.text = body

        views.whatsUpBody.text = wrexagram.whatsUp

        val wrexagramImageId = applicationContext.idForWrexagramImage(wrexagramNumber) ?: return

        views.wrexagramImage.post {

            Picasso.with(this)
                    .load(wrexagramImageId)
                    .resize(views.wrexagramImage.width, views.wrexagramImage.height)
                    .into(views.wrexagramImage)
        }


    }

    private inner class Views
    {
        lateinit var actionBarToolbar: Toolbar
        lateinit var actionBarTitle: TextView
        lateinit var wrexagramImage: ImageView
        lateinit var wrexagramTitle: TextView
        lateinit var duplicateTitle: TextView
        lateinit var body: DocumentView
        lateinit var whatsUpTitle: TextView
        lateinit var whatsUpBody: DocumentView

        fun inflate()
        {
            //Pull out the Views
            actionBarToolbar = findView(id.action_toolbar)
            actionBarTitle = findView(id.yo_action_bar_title)
            wrexagramImage = findView(id.wrexagram_image)
            wrexagramTitle = findView(id.wrexagram_title)
            duplicateTitle = findView(R.id.wrexagram_title_duplicate)
            body = findView(id.wrexagram_body)
            whatsUpTitle = findView(R.id.whats_up_title)
            whatsUpBody = findView(R.id.whats_up_body)

            setFonts()
            setupBackButton()

            body.layoutParams.height = WRAP_CONTENT
            whatsUpBody.layoutParams.height = WRAP_CONTENT
            wrexagramImage.setImageDrawable(null)
        }

        private fun setFonts()
        {
            //Set the typefaces
            whatsUpTitle.typeface = exoExtraBold()
            actionBarTitle.typeface = exoBlack()
            wrexagramTitle.typeface = exoBlack()
        }

        fun setupBackButton()
        {
            //Action bar code
            setSupportActionBar(actionBarToolbar)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = ""

            val backButtonIcon = resources.getDrawable(R.drawable.arrow_back)
            supportActionBar?.setHomeAsUpIndicator(backButtonIcon)

            val backButton = actionBarToolbar.backButton ?: return

            backButton.setOnClickListener {
                LOG.info("Back Button clicked!")
                Aroma.send { sendLowPriorityMessage("Up Button Clicked", "In ReadActivity for Wrexagram #$wrexagramNumber - ${wrexagram.title}") }
                finish()
            }

            if (isAtLeastLollipop())
            {
                backButton.setBackgroundDrawable(resources.getDrawable(R.drawable.ripple_circular))
            }

        }

    }
}
