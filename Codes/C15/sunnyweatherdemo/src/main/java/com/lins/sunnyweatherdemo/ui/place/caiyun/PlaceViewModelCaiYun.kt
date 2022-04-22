package com.lins.sunnyweatherdemo.ui.place.caiyun

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lins.sunnyweatherdemo.logic.data.layer.entry.RespositoryCaiYun
import com.lins.sunnyweatherdemo.logic.model.caiyun.Place

class PlaceViewModelCaiYun:ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){query->
        RespositoryCaiYun.searchPlaces(query)
    }

    fun searchPlaces(query:String){
        searchLiveData.value = query
    }
}