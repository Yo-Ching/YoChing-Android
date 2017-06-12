package tech.redroma.yoching.activities

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tech.redroma.yoching.R

/**
 * Use [SettingsFragment.newInstance] to create an instance of this Fragment.
 * This Fragment takes no parameters.
 *
 * Ensure the [listener][YoSettingsListener] is set.
 */
class SettingsFragment : Fragment()
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
        fun newInstance(): SettingsFragment
        {
            return SettingsFragment()
        }
    }

    var listener: YoSettingsListener? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.fragment_settings, container, false)

        return view
    }

    override fun onAttach(context: Context?)
    {
        super.onAttach(context)

        if (context is YoSettingsListener)
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