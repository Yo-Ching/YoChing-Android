package tech.redroma.yoching.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.exoBlack
import tech.redroma.yoching.setActionBarFont

class YoActivity : AppCompatActivity()
{
    private lateinit var actionBar: Toolbar
    private lateinit var prompt: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setupView()
    }

    private fun setupView()
    {
        setContentView(layout.activity_yo)

        actionBar = findViewById(id.action_toolbar) as Toolbar
        prompt = findViewById(id.yo_prompt) as TextView
        prompt.typeface = applicationContext.exoBlack()

        setActionBarFont(applicationContext.exoBlack()!!, 30)

    }
}
