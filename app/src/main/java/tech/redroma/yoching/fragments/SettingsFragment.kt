package tech.redroma.yoching.fragments

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

}