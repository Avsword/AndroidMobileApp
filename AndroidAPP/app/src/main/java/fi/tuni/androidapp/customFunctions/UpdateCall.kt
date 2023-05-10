package fi.tuni.androidapp.customFunctions

import android.util.Log
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

fun updateCall(person: fi.tuni.androidapp.customFunctions.Person, callback: (responseCode:Int)->Unit){
    thread {
        var jsonPerson = JSONObject()
        jsonPerson.put("id", person.id)
        jsonPerson.put("firstName", person.firstName)
        jsonPerson.put("lastName", person.lastName)
        jsonPerson.put("age",person.age)
        jsonPerson.put("gender",person.gender)

        val jsonString = jsonPerson.toString()

        Log.d("UpdateCall",person.id.toString())
        // According to the docs, we do a delete call to the dummyjson/user/id
        var deleteCallUrl: URL = URL("https://dummyjson.com/users/${person.id}")
        var httpConnection: HttpURLConnection = deleteCallUrl.openConnection() as HttpURLConnection
        httpConnection.requestMethod = "PUT"
        httpConnection.setRequestProperty("Content-Type", "application/json") // The format of the content we're sending to the server
        httpConnection.setRequestProperty("Accept", "application/json") // The format of response we want to get from the server
        httpConnection.doInput = true
        httpConnection.doOutput = false
        val outputStreamWriter = OutputStreamWriter(httpConnection.outputStream)
        outputStreamWriter.write(jsonString)
        outputStreamWriter.flush()


        Log.d("UpdateCall",httpConnection.responseMessage)
        callback(httpConnection.responseCode)
        httpConnection.disconnect()


    }
}