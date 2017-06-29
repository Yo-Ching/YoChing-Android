package tech.redroma.yoching.fragments

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import tech.redroma.yoching.*
import tech.redroma.yoching.activities.CreditsActivity
import tech.redroma.yoching.extensions.*
import tech.redroma.yoching.fragments.SettingsFragment.YoSettingsListener

/**
 * Use [SettingsFragment.newInstance] to create an instance of this Fragment.
 * This Fragment takes no parameters.
 *
 * Ensure the [listener][YoSettingsListener] is set.
 */
class SettingsFragment : android.support.v4.app.Fragment()
{

    var listener: tech.redroma.yoching.fragments.SettingsFragment.YoSettingsListener? = null
    private val views = Views()
    private val actions = Actions()

    override fun onCreate(savedInstanceState: android.os.Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View?
    {
        val view = inflater?.inflate(tech.redroma.yoching.R.layout.fragment_settings, container, false) ?: return null

        views.inflate(view)

        return view
    }

    override fun onAttach(context: android.content.Context?)
    {
        super.onAttach(context)

        if (context is SettingsFragment.YoSettingsListener)
        {
            listener = context
        }
    }

    override fun onDetach()
    {
        super.onDetach()
        listener = null
    }

    interface YoSettingsListener
    {
        fun onTruePlayerEnabled()
        fun onTapThatEnabled()

        fun onStreetStyle()
        fun onSlickStyle()
    }

    companion object Params
    {
        @JvmStatic
        fun newInstance(): SettingsFragment
        {
            return SettingsFragment()
        }
    }

    private inner class Views
    {
        lateinit var throwingStyle: TextView
        lateinit var truePlayer: TextView
        lateinit var truePlayerDescription: TextView
        lateinit var truePlayerCheckMark: ImageView
        lateinit var truePlayerContainer: ViewGroup
        lateinit var tapThat: TextView
        lateinit var tapThatDescription: TextView
        lateinit var tapThatCheckMark: ImageView
        lateinit var tapThatContainer: ViewGroup

        lateinit var changeStyle: TextView
        lateinit var street: TextView
        lateinit var streetDescription: TextView
        lateinit var streetCheckMark: ImageView
        lateinit var streetContainer: ViewGroup
        lateinit var slick: TextView
        lateinit var slickDescription: TextView
        lateinit var slickCheckMark: ImageView
        lateinit var slickContainer: ViewGroup

        lateinit var whatsUp: TextView
        lateinit var buyTheBook: TextView
        lateinit var buyTheBookDescription: TextView
        lateinit var buyTheBookArrow: ImageView
        lateinit var yoAppPlayers: TextView
        lateinit var yoAppPlayersDescription: TextView
        lateinit var yoAppPlayersArrow: ImageView
        lateinit var yoAppPlayersContainer: ViewGroup
        lateinit var learnYoChing: TextView
        lateinit var learnYoChingDescription: TextView
        lateinit var learnYoChingArrow: ImageView

        fun inflate(view: View)
        {
            throwingStyle = view.findView(R.id.throwing_style)
            truePlayer = view.findView(R.id.true_player)
            truePlayerDescription = view.findView(R.id.true_player_description)
            truePlayerCheckMark = view.findView(R.id.true_player_checkmark)
            truePlayerContainer = view.findView(R.id.true_player_container)

            tapThat = view.findView(R.id.tap_that)
            tapThatDescription = view.findView(R.id.tap_that_description)
            tapThatCheckMark = view.findView(R.id.tap_that_checkmark)
            tapThatContainer = view.findView(R.id.tap_that_container)

            changeStyle = view.findView(R.id.change_style)
            street = view.findView(R.id.street)
            streetDescription = view.findView(R.id.street_description)
            streetCheckMark = view.findView(R.id.street_checkmark)
            streetContainer = view.findView(R.id.street_container)

            slick = view.findView(R.id.slick)
            slickDescription = view.findView(R.id.slick_description)
            slickCheckMark = view.findView(R.id.slick_checkmark)
            slickContainer = view.findView(R.id.slick_container)

            whatsUp = view.findView(R.id.whats_up)
            buyTheBook = view.findView(R.id.buy_the_book)
            buyTheBookDescription = view.findView(R.id.buy_the_book_description)
            buyTheBookArrow = view.findView(R.id.buy_the_book_arrow)
            yoAppPlayers = view.findView(R.id.yo_app_players)
            yoAppPlayersDescription = view.findView(R.id.yo_app_players_description)
            yoAppPlayersArrow = view.findView(R.id.yo_app_players_arrow)
            yoAppPlayersContainer = view.findView(R.id.yo_app_players_container)
            learnYoChing = view.findView(R.id.learn_yo_ching)
            learnYoChingDescription = view.findView(R.id.learn_yo_ching_description)
            learnYoChingArrow = view.findView(R.id.learn_yo_ching_arrow)

            setFonts()
            adjustChecks()
            setListeners()
        }


        private fun adjustChecks()
        {
            Settings.init(context)

            if (Settings.truePlayerEnabled)
            {
                truePlayerCheckMark.show()
                tapThatCheckMark.hide()
            }
            else
            {
                truePlayerCheckMark.hide()
                tapThatCheckMark.show()
            }

            if (Settings.slickCoinsEnabled)
            {
                slickCheckMark.show()
                streetCheckMark.hide()
            }
            else
            {
                slickCheckMark.hide()
                streetCheckMark.show()
            }

        }

        private fun setListeners()
        {
            truePlayerContainer.setOnClickListener listener@ {

                if (Settings.truePlayerEnabled)
                    return@listener

                Settings.truePlayerEnabled = true
                adjustChecks()
                listener?.onTruePlayerEnabled()
            }

            tapThatContainer.setOnClickListener listener@ {

                if (Settings.tapThatEnabled)
                    return@listener

                Settings.tapThatEnabled = true
                adjustChecks()
                listener?.onTapThatEnabled()
            }

            streetContainer.setOnClickListener listener@ {

                if (Settings.streetCoinsEnabled)
                    return@listener

                Settings.streetCoinsEnabled = true
                adjustChecks()
                listener?.onStreetStyle()
            }

            slickContainer.setOnClickListener listener@ {

                if (Settings.slickCoinsEnabled)
                    return@listener

                Settings.slickCoinsEnabled = true
                adjustChecks()
                listener?.onSlickStyle()
            }

            yoAppPlayersContainer.setOnClickListener {
                actions.goToCredits()
            }
        }

        private fun setFonts()
        {
            val headerType = context.exoBold()
            throwingStyle.typeface = headerType
            changeStyle.typeface = headerType
            whatsUp.typeface = headerType

            val elementType = context.exoMedium()
            truePlayer.typeface = elementType
            tapThat.typeface = elementType
            street.typeface = elementType
            slick.typeface = elementType
            buyTheBook.typeface = elementType
            yoAppPlayers.typeface = elementType
            learnYoChing.typeface = elementType

            val descriptionType = context.exoLight()
            truePlayerDescription.typeface = descriptionType
            tapThatDescription.typeface = descriptionType
            streetDescription.typeface = descriptionType
            slickDescription.typeface = descriptionType
            buyTheBookDescription.typeface = descriptionType
            yoAppPlayersDescription.typeface = descriptionType
            learnYoChingDescription.typeface = descriptionType
        }


    }


    private inner class Actions
    {
        fun goToCredits()
        {
            val intent = Intent(context, CreditsActivity::class.java)
            startActivity(intent)

        }
    }
}