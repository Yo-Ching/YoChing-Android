package tech.redroma.yoching.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import tech.redroma.yoching.*
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.views.ViewContainer
import tech.redroma.yoching.wrexagrams.*

class ReadActivity : AppCompatActivity()
{
    private val DEFAULT_SUMMARY = WrexagramSummary(number = 1, title = "BRING IT", subTitle = "", whatsUp = "")

    object Parameters
    {
        const val WREXAGRAM_NUMBER = "wrexagram_number"
    }

    private var wrexagramNumber = 2
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

        views.inflate(this)
        loadWrexagramInfo()

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

    }

    private class Views : ViewContainer
    {
        lateinit var toolbar: Toolbar
        lateinit var actionBarTitle: TextView
        lateinit var image: ImageView
        lateinit var title: TextView
        lateinit var body: TextView

        override fun inflate(activity: AppCompatActivity)
        {
            activity.perform {

                actionBarTitle = findViewById(id.yo_action_bar_title) as TextView
                toolbar = findViewById(id.action_toolbar) as Toolbar
                image = findViewById(id.wrexagram_image) as ImageView
                this@Views.title = findViewById(id.wrexagram_title) as TextView
                body = findViewById(id.wrexagram_body) as TextView

                actionBarTitle.typeface = exoBlack()
                this@Views.title.typeface = exoBlack()
                body.typeface = signikaRegular()

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
