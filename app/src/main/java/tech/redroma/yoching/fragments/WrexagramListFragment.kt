package tech.redroma.yoching.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tech.redroma.yoching.R
import tech.redroma.yoching.perform


/**
 * A simple [Fragment] subclass.
 */
class WrexagramListFragment : Fragment()
{


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_wrexagram_list, container, false) ?: return null



        return view
    }

    private class Views
    {
        lateinit var recyclerView: RecyclerView

        fun inflate(fragment: WrexagramListFragment) {

            fragment.perform {
                recyclerView = view?.findViewById(R.id.recycler_view) as RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(context)
            }

        }
    }

}
