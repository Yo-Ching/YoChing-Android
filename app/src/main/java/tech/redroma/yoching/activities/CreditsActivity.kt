package tech.redroma.yoching.activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import android.widget.TextView
import tech.redroma.yoching.R
import tech.redroma.yoching.extensions.*
import tech.redroma.yoching.views.ViewContainer

class CreditsActivity : AppCompatActivity()
{

    private val views = Views()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        views.inflate()
    }

    private inner class Views
    {
        lateinit var yoActionBar: Toolbar
        lateinit var yoActionBarTitle: TextView


        fun inflate()
        {
            yoActionBar = findView(R.id.yo_action_bar)
            yoActionBarTitle = findView(R.id.yo_action_bar_title)

            setupUpButton()
        }

        fun setupUpButton()
        {
            //Action bar code
            setSupportActionBar(yoActionBar)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = ""
            val backButton = resources.getDrawable(R.drawable.arrow_back)
            supportActionBar?.setHomeAsUpIndicator(backButton)

            val button = yoActionBar.firstChildWhere<AppCompatImageButton> { it is AppCompatImageButton } ?: return
            button.setOnClickListener {
                LOG.info("Back Button clicked!")
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            {
                button.setBackgroundDrawable(resources.getDrawable(R.drawable.ripple_circular))
            }

        }
    }
}
