package tech.redroma.yoching.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import org.slf4j.LoggerFactory
import tech.redroma.yoching.*
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.views.ViewContainer
import tech.redroma.yoching.wrexagrams.*

class ReadActivity : AppCompatActivity()
{
    private val LOG = LoggerFactory.getLogger(this::class.java)
    private val DEFAULT_SUMMARY = WrexagramSummary(number = 1, title = "BRING IT", subTitle = "", whatsUp = "")

    private lateinit var summary: WrexagramSummary

    private val views = Views()

    val wrexagramNumber = 15

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_read_wrexagram)

        LOG.info("Loading Wrexagram #$wrexagramNumber")

        Aroma.send {
            sendMediumPriorityMessage("Wrexagram Viewed", body = "Wrexagram #$wrexagramNumber")
        }

        views.inflate(this)
        loadWrexagramInfo()

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
            }

        }
    }
}
