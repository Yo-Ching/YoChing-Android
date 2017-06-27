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
        fun newInstance(): tech.redroma.yoching.fragments.SettingsFragment
        {
            return tech.redroma.yoching.fragments.SettingsFragment()
        }
    }

    private inner class Views
    {
        lateinit var throwingStyle: TextView
        lateinit var truePlayer: CheckedTextView
        lateinit var truePlayerDescription: TextView
        lateinit var tapThat: CheckedTextView
        lateinit var tapThatDescription: TextView

        lateinit var changeStyle: TextView
        lateinit var street: CheckedTextView
        lateinit var streetDescription: TextView
        lateinit var slick: CheckedTextView
        lateinit var slickDescription: TextView

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
            tapThat = view.findView(R.id.tap_that)
            tapThatDescription = view.findView(R.id.tap_that_description)

            changeStyle = view.findView(R.id.change_style)
            street = view.findView(R.id.street)
            streetDescription = view.findView(R.id.street_description)
            slick = view.findView(R.id.slick)
            slickDescription = view.findView(R.id.slick_description)

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

            truePlayer.isChecked = Settings.truePlayerEnabled
            tapThat.isChecked = Settings.tapThatEnabled
            slick.isChecked = Settings.slickCoinsEnabled
            street.isChecked = Settings.streetCoinsEnabled

            truePlayer.adjustCheckMark()
            tapThat.adjustCheckMark()
            slick.adjustCheckMark()
            street.adjustCheckMark()

        }

        private fun setListeners()
        {
            truePlayer.setOnClickListener listener@ {

                if (truePlayer.isChecked) return@listener

                truePlayer.tap().shake()
                tapThat.tap()
                Settings.truePlayerEnabled = truePlayer.isChecked
                listener?.onTruePlayerEnabled()
            }

            tapThat.setOnClickListener listener@ {

                if (tapThat.isChecked) return@listener

                tapThat.tap().shake()
                truePlayer.tap()
                Settings.tapThatEnabled = tapThat.isChecked
                listener?.onTapThatEnabled()
            }

            street.setOnClickListener listener@ {

                if (street.isChecked) return@listener

                street.tap().shake()
                slick.tap()
                Settings.streetCoinsEnabled = street.isChecked
                listener?.onStreetStyle()
            }

            slick.setOnClickListener listener@ {

                if (slick.isChecked) return@listener

                slick.tap().shake()
                street.tap()
                Settings.slickCoinsEnabled = slick.isChecked
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
//            activity.overridePendingTransition(R.anim.exit_to_left, R.anim.enter_from_right)

        }
    }
}