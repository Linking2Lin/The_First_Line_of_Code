package com.lins.sunnyweatherdemo.ui.place.caiyun

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lins.sunnyweatherdemo.logic.data.layer.entry.RespositoryCaiYun
import com.lins.sunnyweatherdemo.logic.model.caiyun.Place

/**
 *
 * @author Lin
 * @date 2022/4/29
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
class PlaceCaiYunViewModel:ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){query->
        RespositoryCaiYun.searchPlaces(query)
    }

    fun searchPlaces(query:String){
        searchLiveData.value = query
    }

    fun savedPlace(place: Place) = RespositoryCaiYun.savePlace(place)

    fun getSavedPlace() = RespositoryCaiYun.getSavedPlace()

    fun isPlaceSaved() = RespositoryCaiYun.isPlaceSaved()
}