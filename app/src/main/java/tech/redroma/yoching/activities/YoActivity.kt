package tech.redroma.yoching.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ArrayAdapter
import tech.redroma.yoching.*
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.activities.ThrowTheYoFragment.ThrowTheYoListener
import tech.redroma.yoching.views.ViewContainer

class YoActivity : AppCompatActivity()
{

    private val views = Views()

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
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.fragment_container, SettingsFragment.newInstance())
                .addToBackStack("Yo")
                .commit()
    }

    private fun setupView()
    {
        setContentView(layout.activity_yo)
        views.inflate(this)

        setActionBarFont(applicationContext.exoBlack(), 30)
    }

    private class Views : ViewContainer
    {
        private lateinit var actionBar: Toolbar
        private lateinit var drawerToggle: ActionBarDrawerToggle
        private lateinit var adapter: ArrayAdapter<String>
        private lateinit var drawerLayout: DrawerLayout

        override fun inflate(activity: AppCompatActivity)
        {
            activity.perform {

                this@Views.actionBar = findViewById(id.action_toolbar) as Toolbar

                drawerLayout = findViewById(id.drawerLayout) as DrawerLayout
                drawerToggle = DrawerToggle(this, drawerLayout)
                drawerLayout.addDrawerListener(drawerToggle)
            }
        }

    }

    private class DrawerToggle(activity: Activity,
                               drawerLayout: DrawerLayout) : ActionBarDrawerToggle(activity,
                                                                                   drawerLayout,
                                                                                   R.string.drawer_open,
                                                                                   R.string.drawer_close)
    {

        override fun onDrawerOpened(drawerView: View?)
        {
            super.onDrawerOpened(drawerView)
            LOG.info("Drawer opened!")
        }

        override fun onDrawerClosed(drawerView: View?)
        {
            super.onDrawerClosed(drawerView)
            LOG.info("Drawer closed!")
        }
    }
}

