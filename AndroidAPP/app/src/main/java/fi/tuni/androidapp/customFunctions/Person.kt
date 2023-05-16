package fi.tuni.androidapp.customFunctions

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Person(val id:Int=0,val firstName:String="",val lastName:String="",val age:Int=0,val gender:String="")

@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestJSONObject(var users: MutableList<Person>? = null)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AddPerson(val firstName:String="",val lastName:String="",val age:Int=0,val gender:String="")