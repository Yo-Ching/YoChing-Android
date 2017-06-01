package tech.redroma.yoching

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView

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
        setContentView(R.layout.activity_yo)

        actionBar = findViewById(R.id.yo_action_bar) as Toolbar
        prompt = findViewById(R.id.yo_prompt) as TextView
        prompt.typeface = applicationContext.exoBlack()

        setActionBarFont(applicationContext.exoBlack()!!, 30)

    }
}
