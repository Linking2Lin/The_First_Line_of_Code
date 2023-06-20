package lins.app.mai

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun a() {
    withContext(Dispatchers.IO) {
        println("HHHHHH")
        for (i in 0 ..< 10) {
            println(i)
        }
    }
}

fun main() {
    runBlocking {
        a()
    }
}