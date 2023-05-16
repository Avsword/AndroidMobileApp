package fi.tuni.androidapp.customFunctions

import android.util.Log
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

fun searchCall(searchParameter: String, callback: (result: RequestJSONObject?)-> Unit): Unit {
    thread {
        var searchCallUrl: URL = URL("https://dummyjson.com/users/search?q=${searchParameter}")
        var httpConnection: HttpURLConnection = searchCallUrl.openConnection() as HttpURLConnection
        //httpConnection.requestMethod = "DELETE"
        var result:RequestJSONObject?=null
        try {
            val input: InputStream = BufferedInputStream(httpConnection.getInputStream())
            result = readAndParseStream(input)
        } finally {
            httpConnection.disconnect()
        }
        callback(result)

        Log.d("searchCall",httpConnection.responseCode.toString())
    }
}