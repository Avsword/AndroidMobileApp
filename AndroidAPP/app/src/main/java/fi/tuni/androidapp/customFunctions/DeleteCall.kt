package fi.tuni.androidapp.customFunctions

import android.util.Log
import fi.tuni.androidapp.customFunctions.RequestJSONObject
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

fun deleteCall(id: Int): Unit {

    thread {
        Log.d("DeleteCall",id.toString())
        // According to the docs, we do a delete call to the dummyjson/user/id
        var deleteCallUrl:URL = URL("https://dummyjson.com/users/${id}")
        var httpConnection:HttpURLConnection = deleteCallUrl.openConnection() as HttpURLConnection
        httpConnection.requestMethod = "DELETE"


        httpConnection.disconnect()

        Log.d("Deletecall",httpConnection.responseCode.toString())
    }

}