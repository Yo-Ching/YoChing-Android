package tech.redroma.yoching.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.*
import android.widget.TextView
import tech.redroma.yoching.*
import tech.redroma.yoching.R.id
import tech.redroma.yoching.R.layout
import tech.redroma.yoching.fragments.*
import tech.redroma.yoching.fragments.NavigationMenuFragment.NavigationMenuListener
import tech.redroma.yoching.fragments.ThrowTheYoFragment.ThrowTheYoListener
import tech.redroma.yoching.views.ViewContainer

class YoActivity : AppCompatActivity(), NavigationMenuListener
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
                }
            }
        }

        super.onAttachFragment(fragment)
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View?
    {
        val view = super.onCreateView(name, context, attrs)

        if (view is ViewGroup)
        {
            view.clipChildren = false
            view.clipToPadding = false
        }

        return view
    }

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View?
    {
        if (parent is ViewGroup)
        {
            parent.clipChildren = false
            parent.clipToPadding = false
        }
        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (views.drawerToggle.onOptionsItemSelected(item))
        {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupView()
    {
        setContentView(layout.activity_yo)
        views.inflate(this)
        setActionBarFont(applicationContext.exoBlack(), 30)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, ThrowTheYoFragment.newInstance(), "Yo")
                .commit()
    }

    override fun onSelectThrowTheYo()
    {
        switchToFragment(ThrowTheYoFragment.newInstance())
        setActionBarTitle(resources.getString(R.string.yo_ching))
    }

    override fun onSelect64Wrexagrams()
    {
        switchToFragment(WrexagramListFragment.newInstance())
        setActionBarTitle(resources.getString(R.string.nav_64_wrexagrams))

    }

    override fun onSelectSettings()
    {
        switchToFragment(SettingsFragment.newInstance())
        setActionBarTitle(resources.getString(R.string.nav_settings))
    }

    override fun onSelectBuyTheBook()
    {

    }

    private fun switchToFragment(fragment: Fragment)
    {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment, "Yo")
                .addToBackStack("Yo")
                .commit()

        views.drawerLayout.closeDrawer(Gravity.START)

    }


    private class Views : ViewContainer
    {
        lateinit var actionToolbar: Toolbar
        lateinit var actionBarTitle: TextView
        lateinit var drawerToggle: ActionBarDrawerToggle
        lateinit var drawerLayout: DrawerLayout

        override fun inflate(activity: AppCompatActivity)
        {
            activity.perform {

                actionToolbar = findView(id.action_toolbar)
                setSupportActionBar(actionToolbar)
                title = ""

                drawerLayout = findView(id.drawerLayout)
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

