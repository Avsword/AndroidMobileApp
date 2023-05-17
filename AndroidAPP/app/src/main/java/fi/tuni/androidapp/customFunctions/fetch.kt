package fi.tuni.androidapp.customFunctions
import java.io.BufferedInputStream

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.io.*
import android.util.Log
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.concurrent.thread

/**
 * Fetches all users
 *
 * @param callback Callback for the response with a formatted Person.
 */
fun fetch(callback: (result:RequestJSONObject) -> Unit): Unit {
    thread{
        print("Fetching started")
        var result:RequestJSONObject?=null
        val url = URL("https://dummyjson.com/users")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            val input: InputStream = BufferedInputStream(urlConnection.getInputStream())
            result = readAndParseStream(input)
        } finally {
            urlConnection.disconnect()
        }
        callback(result!!)
    }
}

/**
 * Reads an inputstream and returns a parsed JSONObject
 *
 * @param input The response we initially get from an HTTPUrlconnection.
 * @return Returns a JSONObject with only the values of users we want.
 */
// https://stackoverflow.com/questions/8376072/whats-the-readstream-method-i-just-can-not-find-it-anywhere
fun readAndParseStream(input: InputStream): RequestJSONObject {
    // We're using a stringbuilder to get the data from the inputstream
    val sb = StringBuilder()
    // Using the bufferedreader we will read the input
    val r = BufferedReader(InputStreamReader(input), 1000)
    // This returns the first line
    val line: String = r.readLine()
    // loop through the line.
    line.forEach{sb.append(it)}
    Log.d("read-stream",sb.toString())
    input.close()
    r.close()
    //Log.d("readstream",sb.toString())
    val mp = ObjectMapper()
    //Log.d("readstream", mp.readValue(sb.toString(), RequestJSONObject::class.java).toString())
    return mp.readValue(sb.toString(), RequestJSONObject::class.java)
}


