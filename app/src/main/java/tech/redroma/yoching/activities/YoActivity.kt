package tech.redroma.yoching.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import tech.redroma.yoching.*
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.views.ViewContainer

class YoActivity : AppCompatActivity()
{

    private lateinit var view: Views

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setupView()
    }

    private fun setupView()
    {
        setContentView(layout.activity_yo)
        view = Views()
        view.inflate(this)

        setActionBarFont(applicationContext.exoBlack()!!, 30)
    }

    private class Views : ViewContainer
    {
        private lateinit var actionBar: Toolbar
        private lateinit var prompt: TextView
        private lateinit var coin1: ImageView
        private lateinit var coin2: ImageView
        private lateinit var coin3: ImageView

        override fun inflate(activity: AppCompatActivity)
        {
            activity.perform {

                this@Views.actionBar = findViewById(id.action_toolbar) as Toolbar

                prompt = findViewById(id.yo_prompt) as TextView
                prompt.typeface = applicationContext.exoBlack()

                coin1 = findViewById(id.coin_1) as ImageView
                coin2 = findViewById(id.coin_2) as ImageView
                coin3 = findViewById(id.coin_3) as ImageView

            }
        }
    }
}

