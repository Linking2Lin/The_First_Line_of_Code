package lins.app.mai

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun a() {
    withContext(Dispatchers.IO) {
        launch {

        }
    }
}