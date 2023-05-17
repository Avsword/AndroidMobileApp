package fi.tuni.androidapp.customFunctions
import android.util.Log
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * Adds a new user.
 *
 * addCall starts a new thread and creates a JSONObject
 *  based on the person param. Then it establishes an
 *  httpurlconnection to post the new user.
 *
 * @param person Contains data of a user in a specific format.
 * @param callback Defines what we do with the response code.
 */
fun addCall(person: AddPerson, callback: (responseCode:Int)->Unit){
    thread {
        var jsonPerson = JSONObject()
        jsonPerson.put("firstName", person.firstName)
        jsonPerson.put("lastName", person.lastName)
        jsonPerson.put("age",person.age)
        jsonPerson.put("gender",person.gender)

        val jsonString = jsonPerson.toString()

        // According to the docs, we do a delete call to the dummyjson/user/id
        var deleteCallUrl: URL = URL("https://dummyjson.com/users/add")
        var httpConnection: HttpURLConnection = deleteCallUrl.openConnection() as HttpURLConnection
        httpConnection.requestMethod = "POST"
        httpConnection.setRequestProperty("Content-Type", "application/json") // The format of the content we're sending to the server
        httpConnection.setRequestProperty("Accept", "application/json") // The format of response we want to get from the server
        httpConnection.doInput = true
        httpConnection.doOutput = false
        val outputStreamWriter = OutputStreamWriter(httpConnection.outputStream)
        outputStreamWriter.write(jsonString)
        outputStreamWriter.flush()


        Log.d("addCall",httpConnection.responseMessage)
        callback(httpConnection.responseCode)
        httpConnection.disconnect()


    }
}