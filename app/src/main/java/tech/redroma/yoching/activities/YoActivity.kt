package tech.redroma.yoching.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import tech.redroma.yoching.*
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.activities.ThrowTheYoFragment.ThrowTheYoListener
import tech.redroma.yoching.views.ViewContainer

class YoActivity : AppCompatActivity()
{

    private lateinit var view: Views

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setupView()

    }

    override fun onAttachFragment(fragment: Fragment)
    {

        LOG.info("Attached fragment! ")

        if (fragment is ThrowTheYoFragment)
        {
            LOG.info("Found Throw Fragment!: $fragment")

            fragment.listener = object : ThrowTheYoListener
            {
                override fun onCoinTapped()
                {
                    replaceFragment()
                }
            }
        }

        super.onAttachFragment(fragment)
    }

    private fun replaceFragment()
    {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment.newInstance())
                .commit()
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

        override fun inflate(activity: AppCompatActivity)
        {
            activity.perform {
                this@Views.actionBar = findViewById(id.action_toolbar) as Toolbar
            }
        }
    }
}

