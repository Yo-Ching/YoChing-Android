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
import tech.redroma.yoching.*
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.extensions.*
import tech.redroma.yoching.views.ViewContainer
import tech.redroma.yoching.wrexagrams.*
import tyrantgit.explosionfield.ExplosionField

class ReadActivity : AppCompatActivity()
{
    private val DEFAULT_SUMMARY = WrexagramSummary(number = 1, title = "BRING IT", subTitle = "", whatsUp = "")

    object Parameters
    {
        const val WREXAGRAM_NUMBER = "wrexagram_number"
    }

    private var wrexagramNumber = 8
    private lateinit var summary: WrexagramSummary

    private val views = Views()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_read_wrexagram)

        wrexagramNumber = intent.getIntExtra(Parameters.WREXAGRAM_NUMBER, wrexagramNumber)

        LOG.info("Loading Wrexagram #$wrexagramNumber")

        Aroma.send {
            sendMediumPriorityMessage("Wrexagram Viewed", body = "Wrexagram #$wrexagramNumber")
        }

        views.inflate()
        loadWrexagramInfo()
    }

    private fun explodeIntoView()
    {
        views.wrexagramTitle.post {

            YoYo.with(Techniques.BounceInDown)
                    .duration(400)
                    .playOn(views.wrexagramTitle)

            views.duplicateTitle.post {
                val explosion = ExplosionField.attach2Window(this)
                explosion.explode(views.duplicateTitle)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        LOG.info("Option selected")
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun loadWrexagramInfo()
    {
        views.actionBarTitle.text = "WREXAGRAM #$wrexagramNumber"

        val image = applicationContext.loadWrexagramImage(wrexagramNumber)

        if (image != null)
            views.wrexagramImage.setImageBitmap(image)

        summary = applicationContext.loadWrexagramSummary(wrexagramNumber) ?: DEFAULT_SUMMARY

        views.wrexagramTitle.text = summary.title

        val body = applicationContext.loadWrexagramBody(wrexagramNumber)
        views.body.text = body

        views.whatsUpBody.text = summary.whatsUp

        explodeIntoView()
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
                finish()
            }

            if (hasSDKAtLeast(android.os.Build.VERSION_CODES.LOLLIPOP))
            {
                backButton.setBackgroundDrawable(resources.getDrawable(R.drawable.ripple_circular))
            }

        }

    }
}
