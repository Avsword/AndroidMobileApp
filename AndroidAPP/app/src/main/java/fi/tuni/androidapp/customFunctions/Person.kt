package fi.tuni.androidapp.customFunctions

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


/**
 * Data class with info of a user
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Person(val id:Int=0,val firstName:String="",val lastName:String="",val age:Int=0,val gender:String="")

/**
 * Data class containing a list of people//users.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestJSONObject(var users: MutableList<Person>? = null)

/**
 * Data class of a user with necessary values for a post call.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class AddPerson(val firstName:String="",val lastName:String="",val age:Int=0,val gender:String="")