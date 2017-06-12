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

    private var listener: YoSettingsListener? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onAttach(context: Context?)
    {
        super.onAttach(context)

        if (context is YoSettingsListener)
        {
            listener = context
        }
        else
        {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach()
    {
        super.onDetach()
        listener = null
    }


}