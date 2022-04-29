package com.lins.sunnyweatherdemo.ui.weather.caiyun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lins.sunnyweatherdemo.R
import com.lins.sunnyweatherdemo.databinding.ActivityWeatherBinding
import com.lins.sunnyweatherdemo.databinding.ForecastBinding
import com.lins.sunnyweatherdemo.databinding.NowBinding
import com.lins.sunnyweatherdemo.logic.model.caiyun.Weather
import com.lins.sunnyweatherdemo.logic.model.caiyun.getSky
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {
    lateinit var binding: ActivityWeatherBinding

    val viewModel by lazy { ViewModelProvider(this).get(CaiYunWeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.locationLng.isEmpty()){
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()){
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()){
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }

        viewModel.weatherLiveData.observe(this){result->
            val weather = result.getOrNull()
            if (weather != null){
                showWeatherInfo(weather)
            }else{
                Toast.makeText(this, "Wufa", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
        viewModel.refreshWeather(viewModel.locationLng,viewModel.locationLat)

    }

    private fun showWeatherInfo(weather:Weather){
        binding.nowLayout.placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily

        //填充now.xml
        val currentTempText = "${realtime.temperature.toInt()} °C"
        binding.nowLayout.currentTemp.text = currentTempText
        binding.nowLayout.cutrrentSky.text = getSky(realtime.skycon).info
        val currentPMText = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
        binding.nowLayout.currentAQI.text = currentPMText
        binding.nowLayout.nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)

        //填充forecast.xml
        binding.forecastLayout.forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days){
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item,binding.forecastLayout.forecastLayout,false)
            val dateInfo = view.findViewById<TextView>(R.id.dateInfo)
            val skyIcon = view.findViewById<ImageView>(R.id.skyIcon)
            val skyInfo = view.findViewById<TextView>(R.id.skyInfo)
            val temperatureInfo = view.findViewById<TextView>(R.id.temperatureInfo)
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} °C"
            temperatureInfo.text = tempText
            binding.forecastLayout.forecastLayout.addView(view)
        }

        //填充life_index.xml
        val lifeIndex = daily.lifeIndex
        binding.lifeIndexLayout.coldRiskText.text = lifeIndex.coldRisk[0].desc
        binding.lifeIndexLayout.dressingText.text = lifeIndex.dressing[0].desc
        binding.lifeIndexLayout.ultravioletText.text = lifeIndex.ultraviolet[0].desc
        binding.lifeIndexLayout.carWashingText.text = lifeIndex.carWashing[0].desc
        binding.weatherLayout.visibility = View.VISIBLE


    }
}