package com.example.servicetest.kotlins.changes.inverse

interface Transformer<in T> {
    fun transform(t:T):String
}