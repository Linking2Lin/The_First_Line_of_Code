package com.example.myapplication.sax

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.lang.StringBuilder

class ContentHandler:DefaultHandler() {
    private val TAG = "ContentHandler"
    private var nodeName = ""
    private lateinit var id:StringBuilder
    private lateinit var name:StringBuilder
    private lateinit var version: StringBuilder

    /**
     * 开始解析XML时调用
     */
    override fun startDocument() {
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    /**
     * 开始解析某个节点时调用
     */
    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        if (localName != null) {
            nodeName = localName
        }
        Log.d(TAG, "startElement: uri is $uri")
        Log.d(TAG, "startElement: localName is $localName")
        Log.d(TAG, "startElement: qName is $qName")
        Log.d(TAG, "startElement: attributes is $attributes")

    }

    /**
     * 获取节点中内容时调用
     */
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        when(nodeName){
            "id" -> id.append(ch,start,length)
            "name" -> name.append(ch,start,length)
            "version" -> version.append(ch,start,length)
        }
    }

    /**
     * 完成解析某个节点时调用
     */
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if ("app"==localName){
            Log.d(TAG, "endElement: id is ${id.toString().trim()}")
            Log.d(TAG, "endElement: name is ${name.toString().trim()}")
            Log.d(TAG, "endElement: version is ${version.toString().trim()}")
            id.setLength(0)
            name.setLength(0)
            version.setLength(0)
        }
    }

    /**
     * 完成整个文件时调用
     */
    override fun endDocument() {
        super.endDocument()
    }
}