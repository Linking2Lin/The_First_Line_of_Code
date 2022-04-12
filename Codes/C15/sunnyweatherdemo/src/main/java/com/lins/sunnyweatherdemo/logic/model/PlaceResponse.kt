package com.lins.sunnyweatherdemo.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 数据模型
 * @author Lin
 * @date 2022/4/12
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */

data class PlaceResponse(val status:String,val places:List<Place>)

data class Place(val name:String,val location:Location,@SerializedName("formatted_address") val address:String)

data class Location(val lng:String,val lat:String)