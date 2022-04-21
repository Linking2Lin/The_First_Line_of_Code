package com.lins.sunnyweatherdemo.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 城市信息数据模型，
 * 用来保存向服务器申请城市信息后返回的对象
 * @author Lin
 * @date 2022/4/12
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */

/**
 * 地址响应类，包含状态和地址集合
 */
data class PlaceResponse(val status:String,val places:List<Place>)

/**
 * 城市信息，
 * 包括：
 * 城市名，位置，格式化地址，
 * SerializedName：让JSON字段和kotlin字段之间建立联系
 */
data class Place(val name:String,val location:Location,@SerializedName("formatted_address") val address:String)

/**
 * 位置对象，包括经纬度信息
 */
data class Location(val lng:String,val lat:String)