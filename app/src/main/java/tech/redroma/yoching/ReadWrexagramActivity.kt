package tech.redroma.yoching

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import org.slf4j.LoggerFactory

class ReadWrexagramActivity : AppCompatActivity()
{
    private val LOG = LoggerFactory.getLogger(this::class.java)

    private lateinit var toolbar: Toolbar
    private lateinit var wrexImage: ImageView

    private val wrexagramNumber = 2

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_wrexagram)

        LOG.info("Loading Wrexagram #$wrexagramNumber")
        toolbar = findViewById(R.id.action_toolbar) as Toolbar

        Aroma.send {
            it.sendMediumPriorityMessage("Android Launched!")
        }

    }
}
