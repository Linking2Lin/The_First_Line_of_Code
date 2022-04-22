package com.lins.sunnyweatherdemo.ui.weather.hefeng

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lins.sunnyweatherdemo.logic.data.layer.entry.Repository
import com.lins.sunnyweatherdemo.logic.model.LocationHF

class WeatherHeFengViewModel:ViewModel() {
     private val locationliveData = MutableLiveData<LocationHF>()

     var locationLng = ""
     var locationLat = ""
     var placeName = ""

     val weartherLiveData = Transformations.switchMap(locationliveData){location ->
          Repository.refreshWeather(location.lon,location.lat)
     }

     fun refershWeather(lon:String,lat:String){
     }
}