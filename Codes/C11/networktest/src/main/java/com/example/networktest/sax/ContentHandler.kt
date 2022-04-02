package com.example.networktest.sax

import android.os.Build
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class ContentHandler:DefaultHandler() {

    private var nodeName = ""

    private lateinit var id:StringBuilder
    private lateinit var name:StringBuilder
    private lateinit var version:StringBuilder

    override fun startDocument() {
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        //记录当前节点
        if (localName != null) {
            nodeName = localName
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
    }

    override fun endDocument() {
        super.endDocument()
    }

}