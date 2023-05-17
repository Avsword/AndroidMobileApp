package fi.tuni.androidapp.customFunctions

import android.util.Log
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * Search for a user and call "callback" with the result.
 *
 * @param searchParameter We can use multiple search parameters such as ID, Name etc to
 *                          search for a user.
 * @param callback Function that is called when we have a parsed result.
 */
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
            callback(result)
        }
        Log.d("searchCall",httpConnection.responseCode.toString())
    }
}