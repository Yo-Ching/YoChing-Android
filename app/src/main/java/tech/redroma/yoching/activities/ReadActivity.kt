package tech.redroma.yoching.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import com.bluejamesbond.text.DocumentView
import tech.redroma.yoching.*
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
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
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_read_wrexagram)

        wrexagramNumber = intent.getIntExtra(Parameters.WREXAGRAM_NUMBER, wrexagramNumber)

        LOG.info("Loading Wrexagram #$wrexagramNumber")

        Aroma.send {
            sendMediumPriorityMessage("Wrexagram Viewed", body = "Wrexagram #$wrexagramNumber")
        }

        views.inflate(this)
        loadWrexagramInfo()
        explodeText()
    }

    private fun explodeText()
    {
        views.duplicateTitle.post {
            val explosion = ExplosionField.attach2Window(this)
            explosion.explode(views.duplicateTitle)
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
            views.image.setImageBitmap(image)

        this.summary = applicationContext.loadWrexagramSummary(wrexagramNumber) ?: DEFAULT_SUMMARY
        views.title.text = summary.title

        val body = applicationContext.loadWrexagramBody(wrexagramNumber)
        views.body.text = body

        views.whatsUpBody.text = summary.whatsUp
    }

    private class Views : ViewContainer
    {
        lateinit var toolbar: Toolbar
        lateinit var actionBarTitle: TextView
        lateinit var image: ImageView
        lateinit var title: TextView
        lateinit var duplicateTitle: TextView
        lateinit var body: DocumentView
        lateinit var whatsUpTitle: TextView
        lateinit var whatsUpBody: DocumentView

        override fun inflate(activity: AppCompatActivity)
        {
            activity.perform {

                actionBarTitle = findViewById(id.yo_action_bar_title) as TextView
                toolbar = findViewById(id.action_toolbar) as Toolbar
                image = findViewById(id.wrexagram_image) as ImageView
                this@Views.title = findViewById(id.wrexagram_title) as TextView
                duplicateTitle = findViewById(R.id.wrexagram_title_duplicate) as TextView
                body = findViewById(id.wrexagram_body) as DocumentView
                body.layoutParams.height = WRAP_CONTENT

                whatsUpTitle = findViewById(R.id.whats_up_title) as TextView
                whatsUpTitle.typeface = exoExtraBold()
                whatsUpBody = findViewById(R.id.whats_up_body) as DocumentView
                whatsUpBody.layoutParams.height = WRAP_CONTENT

                actionBarTitle.typeface = exoBlack()
                this@Views.title.typeface = exoBlack()

                setSupportActionBar(this@Views.toolbar)
                title = ""
                supportActionBar?.setHomeButtonEnabled(true)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)

                val backButton = resources.getDrawable(R.drawable.arrow_back)
                supportActionBar?.setHomeAsUpIndicator(backButton)

            }

        }

    }
}
