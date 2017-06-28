package tech.redroma.yoching.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import android.view.textservice.TextInfo
import android.widget.TextView
import org.w3c.dom.Text
import tech.redroma.yoching.*
import tech.redroma.yoching.extensions.*

class CreditsActivity : AppCompatActivity()
{

    private val views = Views()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)

        views.inflate()
    }

    override fun finish()
    {
        super.finish()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }

    private inner class Views
    {
        lateinit var yoActionBar: Toolbar
        lateinit var yoActionBarTitle: TextView

        lateinit var truePlayer: TextView
        lateinit var truePlayerDescription: TextView
        lateinit var truePlayerContainer: ViewGroup

        lateinit var hugh: TextView
        lateinit var hughDescription: TextView
        lateinit var hughContainer: ViewGroup

        lateinit var wellington: TextView
        lateinit var wellingtonDescription: TextView
        lateinit var wellingtonContainer: ViewGroup

        lateinit var marc: TextView
        lateinit var marcDescription: TextView
        lateinit var marcContainer: ViewGroup

        lateinit var brendan: TextView
        lateinit var brendanDescription: TextView
        lateinit var brendanContainer: ViewGroup

        lateinit var maya: TextView
        lateinit var mayaDescription: TextView
        lateinit var mayaContainer: ViewGroup

        fun inflate()
        {
            yoActionBar = findView(R.id.yo_action_bar)
            yoActionBarTitle = findView(R.id.yo_action_bar_title)

            truePlayer = findView(R.id.true_player)
            truePlayerDescription = findView(R.id.true_player_description)
            truePlayerContainer = findView(R.id.true_player_container)

            hugh = findView(R.id.hugh)
            hughDescription = findView(R.id.hugh_description)
            hughContainer = findView(R.id.hugh_container)

            wellington = findView(R.id.wellington)
            wellingtonDescription = findView(R.id.wellington_description)
            wellingtonContainer = findView(R.id.wellington_container)

            marc = findView(R.id.marc)
            marcDescription = findView(R.id.marc_description)
            marcContainer = findView(R.id.marc_container)

            brendan = findView(R.id.brendan)
            brendanDescription = findView(R.id.brendan_description)
            brendanContainer = findView(R.id.brendan_container)

            maya = findView(R.id.maya)
            mayaDescription = findView(R.id.maya_description)
            mayaContainer = findView(R.id.maya_container)

            setupUpButton()
            setFonts()
            setListeners()
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
                finish()
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            {
                button.setBackgroundDrawable(resources.getDrawable(R.drawable.ripple_circular))
            }

        }

        fun setFonts()
        {
            val titleFont = exoDemiBold()
            val subtitleFont = exoRegular()

            listOf(truePlayer, hugh, wellington, marc, brendan, maya)
                    .forEach { it.typeface = titleFont }

            listOf(truePlayerDescription, hughDescription, wellingtonDescription, marcDescription, brendanDescription, mayaDescription)
                    .forEach { it.typeface = subtitleFont }

            yoActionBarTitle.typeface = exoBlack()
        }

        fun setListeners()
        {
            truePlayerContainer.setOnClickListener { openURL(Links.truePlayerLink) }
            hughContainer.setOnClickListener { openURL(Links.hughLink) }
            wellingtonContainer.setOnClickListener { openURL(Links.wellingtonLink) }
            marcContainer.setOnClickListener { openURL(Links.marcLink) }
            brendanContainer.setOnClickListener { openURL(Links.brendanLink) }
            mayaContainer.setOnClickListener { openURL(Links.mayaLink) }
        }

    }

}
