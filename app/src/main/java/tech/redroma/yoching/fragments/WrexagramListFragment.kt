package tech.redroma.yoching.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import tech.redroma.yoching.*
import tech.redroma.yoching.activities.ReadActivity
import tech.redroma.yoching.wrexagrams.*


/**
 * A simple [Fragment] subclass.
 */
class WrexagramListFragment : Fragment()
{

    companion object
    {
        @JvmStatic
        fun newInstance(): WrexagramListFragment
        {
            return WrexagramListFragment()
        }
    }

    private val views = Views()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_wrexagram_list, container, false) ?: return null

        views.inflate(view)

        return view
    }

    private class Views
    {
        lateinit var recyclerView: RecyclerView

        fun inflate(view: View)
        {
            recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = WrexagramRecyclerAdapter(view.context)
        }
    }

}

private class WrexagramRecyclerAdapter(val context: Context) : RecyclerView.Adapter<WrexagramHolder>()
{
    private val wrexagrams = context.wrexagrams

    override fun onBindViewHolder(holder: WrexagramHolder?, position: Int)
    {
        val wrexagram = wrexagrams[position]
        holder?.setWrexagram(wrexagram)
        LOG.info("View binded at position $position")
    }

    override fun getItemCount(): Int
    {
        return wrexagrams.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WrexagramHolder
    {
        LOG.info("createViewHolder Called")

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wrexagram_item_template, parent, false)
        return WrexagramHolder(view)
    }

}

private class WrexagramHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener
{
    lateinit var _wrexagram: WrexagramSummary

    var wrexagramNumber: TextView
    var wrexagramTitle: TextView
    var wrexagramSummary: TextView
    var wrexagramIcon: ImageView

    private val context get() = view.context

    init
    {
        wrexagramNumber = view.findView(R.id.wrexagram_number)
        wrexagramTitle = view.findView(R.id.wrexagram_title)
        wrexagramSummary = view.findView(R.id.wrexagram_summary)
        wrexagramIcon = view.findView(R.id.wrexagram_icon)

        wrexagramNumber.typeface = context.exoExtraBold()
        wrexagramTitle.typeface = context.exoExtraBold()
        wrexagramSummary.typeface = context.signikaRegular()

        view.setOnClickListener(this)
    }

    fun setWrexagram(wrexagram: WrexagramSummary)
    {
        _wrexagram = wrexagram
        wrexagramNumber.text = wrexagram.number.toString()
        wrexagramTitle.text = wrexagram.title
        wrexagramSummary.text = wrexagram.subTitle

        val imageId = context.idForWrexagramImage(wrexagram.number) ?: return
        Picasso.with(context)
                .load(imageId)
                .resize(200, 200)
                .noPlaceholder()
                .into(wrexagramIcon)

    }

    override fun onClick(v: View?)
    {
        LOG.info("Clicked! $_wrexagram")

        val readIntent = Intent(context, ReadActivity::class.java)
        readIntent.putExtra(ReadActivity.Parameters.WREXAGRAM_NUMBER, _wrexagram.number)
        context.startActivity(readIntent)
    }
}