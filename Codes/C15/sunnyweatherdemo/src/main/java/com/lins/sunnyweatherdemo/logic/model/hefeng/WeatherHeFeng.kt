package com.lins.sunnyweatherdemo.logic.model.hefeng

import com.lins.sunnyweatherdemo.logic.model.hefeng.DailyResponseHeFeng
import com.lins.sunnyweatherdemo.logic.model.hefeng.RealtimeResponseHeFeng

/**
 * 天气数据模型，包括实时天气和未来几天的天气
 * @author Lin
 * @date 2022/4/21
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
data class WeatherHeFeng(val realtimeResponseHeFeng: RealtimeResponseHeFeng.Now, val dailyResponseHeFeng: DailyResponseHeFeng.Daily)
