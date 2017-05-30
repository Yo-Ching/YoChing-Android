package tech.redroma.yoching

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class YoActivity : AppCompatActivity()
{
    private lateinit var prompt: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_yo)

        prompt = findViewById(R.id.yo_prompt) as TextView
        prompt.typeface = applicationContext.exoBlack()
    }
}
