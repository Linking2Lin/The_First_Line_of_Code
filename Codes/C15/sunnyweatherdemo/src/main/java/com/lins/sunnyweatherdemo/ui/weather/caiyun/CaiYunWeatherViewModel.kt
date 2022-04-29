package com.lins.sunnyweatherdemo.ui.weather.caiyun

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lins.sunnyweatherdemo.logic.data.layer.entry.RespositoryCaiYun
import com.lins.sunnyweatherdemo.logic.model.caiyun.Location

class CaiYunWeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    /**
     * 观察locationLiveData
     */
    val weatherLiveData = Transformations.switchMap(locationLiveData){location ->
        RespositoryCaiYun.refreshWeather(location.lng,location.lat)
    }

    /**
     * 用来刷新天气信息，将传入的经纬度值赋值给locationLiveData
     */
    fun refreshWeather(lng:String,lat:String){
        locationLiveData.value = Location(lng, lat)
    }
}