package dev.msemyak.coroutineslab.ui.main

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ahmadrosid.svgloader.SvgLoader
import dev.msemyak.coroutineslab.R
import dev.msemyak.coroutineslab.data.model.Country

internal class RVAdapterCountry(private var countriesList: List<Country>, private val context: Context) :
    RecyclerView.Adapter<RVAdapterCountry.ViewHolder>() {

    override fun getItemCount(): Int = countriesList.size

    fun setNewData(countriesList: List<Country>) {
        this.countriesList = countriesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterCountry.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.it_country, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val countryToBind = countriesList[position]

        holder.tvCountry.text = countryToBind.name

        SvgLoader.pluck()
            .with(context as Activity)
            .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
            .load(countryToBind.flag, holder.ivFlag)

    }

    internal class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvCountry: TextView = v.findViewById(R.id.tvCountry)
        var ivFlag: ImageView = v.findViewById(R.id.ivFlag)
    }

}


