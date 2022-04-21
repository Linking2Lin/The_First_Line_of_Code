package com.lins.sunnyweatherdemo.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lins.sunnyweatherdemo.R
import com.lins.sunnyweatherdemo.logic.model.Location
import com.lins.sunnyweatherdemo.logic.model.LocationHF
import com.lins.sunnyweatherdemo.logic.model.Place

class PlaceAdapter(private val fragment:Fragment,private val placeList:List<LocationHF>):RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val placeName: TextView = view.findViewById<TextView>(R.id.placeName)
        val placeAddress: TextView = view.findViewById<TextView>(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = "${place.country} ${place.adm1} ${place.adm2}"
    }

    override fun getItemCount(): Int {
        return placeList.size
    }
}