package com.lins.sunnyweatherdemo.logic.model.caiyun

data class Weather(val realtime: CaiYunRealtimeResponse.Realtime,val daily:CaiYunDailyResponse.Daily)
