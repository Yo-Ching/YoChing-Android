package tech.redroma.yoching.activities

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
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

        Aroma.send { sendMediumPriorityMessage("App Launched") }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (views.drawerToggle.onOptionsItemSelected(item))
        {
            return true
        }

        return super.onOptionsItemSelected(item)
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
        lateinit var actionToolbar: Toolbar
        lateinit var drawerToggle: ActionBarDrawerToggle
        lateinit var drawerLayout: DrawerLayout

        override fun inflate(activity: AppCompatActivity)
        {
            activity.perform {

                actionToolbar = findViewById(id.action_toolbar) as Toolbar
                setSupportActionBar(actionToolbar)
                title = ""

                drawerLayout = findViewById(id.drawerLayout) as DrawerLayout
                drawerToggle = DrawerToggle(this, drawerLayout)
                drawerToggle.isDrawerIndicatorEnabled = true
                drawerToggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
                drawerLayout.addDrawerListener(drawerToggle)
                drawerToggle.syncState()

                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeButtonEnabled(true)
            }
        }

    }

    private class DrawerToggle(val activity: Activity,
                               val drawerLayout: DrawerLayout) : ActionBarDrawerToggle(activity,
                                                                                   drawerLayout,
                                                                                   R.string.drawer_open,
                                                                                   R.string.drawer_close)
    {

        override fun onDrawerOpened(drawerView: View?)
        {
            super.onDrawerOpened(drawerView)
            LOG.info("Drawer opened!")

            activity.invalidateOptionsMenu()
            syncState()
        }

        override fun onDrawerClosed(drawerView: View?)
        {
            super.onDrawerClosed(drawerView)
            LOG.info("Drawer closed!")

            activity.invalidateOptionsMenu()
            syncState()
        }
    }
}

