package tech.redroma.yoching.fragments

import android.widget.*
import tech.redroma.yoching.R
import tech.redroma.yoching.findViewById
import tech.redroma.yoching.fragments.SettingsFragment.YoSettingsListener

/**
 * Use [SettingsFragment.newInstance] to create an instance of this Fragment.
 * This Fragment takes no parameters.
 *
 * Ensure the [listener][YoSettingsListener] is set.
 */
class SettingsFragment : android.support.v4.app.Fragment()
{

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

    var listener: tech.redroma.yoching.fragments.SettingsFragment.YoSettingsListener? = null

    override fun onCreate(savedInstanceState: android.os.Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View?
    {
        val view = inflater?.inflate(tech.redroma.yoching.R.layout.fragment_settings, container, false)

        return view
    }

    override fun onAttach(context: android.content.Context?)
    {
        super.onAttach(context)

        if (context is tech.redroma.yoching.fragments.SettingsFragment.YoSettingsListener)
        {
            listener = context
        }
    }

    override fun onDetach()
    {
        super.onDetach()
        listener = null
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
        lateinit var learnYoChing: TextView
        lateinit var learnYoChingDescription: TextView
        lateinit var learnYoChingArrow: ImageView

        fun init()
        {
            throwingStyle = findViewById(R.id.throwing_style)
            truePlayer = findViewById(R.id.true_player)
            truePlayerDescription = findViewById(R.id.true_player_description)
            tapThat = findViewById(R.id.tap_that)
            tapThatDescription = findViewById(R.id.tap_that)

            changeStyle = findViewById(R.id.change_style)
            street = findViewById(R.id.street)
            streetDescription = findViewById(R.id.street_description)
            slick = findViewById(R.id.slick)
            slickDescription = findViewById(R.id.slick_description)

            whatsUp = findViewById(R.id.whats_up)
            buyTheBook = findViewById(R.id.buy_the_book)
            buyTheBookDescription = findViewById(R.id.buy_the_book_description)
            buyTheBookArrow = findViewById(R.id.buy_the_book_arrow)
            yoAppPlayers = findViewById(R.id.yo_app_players)
            yoAppPlayersDescription = findViewById(R.id.yo_app_players_description)
            yoAppPlayersArrow = findViewById(R.id.yo_app_players_arrow)
            learnYoChing = findViewById(R.id.learn_yo_ching)
            learnYoChingDescription = findViewById(R.id.learn_yo_ching_description)
            learnYoChingArrow = findViewById(R.id.learn_yo_ching_arrow)

        }


    }

}