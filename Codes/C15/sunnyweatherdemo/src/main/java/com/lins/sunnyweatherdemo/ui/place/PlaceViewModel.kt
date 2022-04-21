package com.lins.sunnyweatherdemo.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lins.sunnyweatherdemo.logic.Repository
import com.lins.sunnyweatherdemo.logic.model.LocationHF
import com.lins.sunnyweatherdemo.logic.model.Place

/**
 *
 * @author Lin
 * @date 2022/4/20
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
class PlaceViewModel:ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<LocationHF>()

    val placeLiveData = Transformations.switchMap(searchLiveData){query->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String){
        searchLiveData.value = query
    }
}