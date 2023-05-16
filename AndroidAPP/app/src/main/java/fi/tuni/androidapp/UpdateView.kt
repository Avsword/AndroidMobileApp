package fi.tuni.androidapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import fi.tuni.androidapp.customFunctions.Person
import fi.tuni.androidapp.customFunctions.updateCall


class UpdateView: Activity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updateview)

        val updateSuccess = findViewById<TextView>(R.id.UpdateSuccess)
        val updateFirstName = findViewById<EditText>(R.id.updateFirstName)

        updateFirstName.hint = intent.getStringExtra("firstName")
        val updateLastName = findViewById<EditText>(R.id.updateLastName)

        updateLastName.hint = intent.getStringExtra("lastName")
        val updateAge = findViewById<EditText>(R.id.updateAge)

        updateAge.hint = intent.getIntExtra("age", 0).toString()
        val updateGender = findViewById<EditText>(R.id.updateGender)
        updateGender.hint = intent.getStringExtra("gender")

        val updateBackButton = findViewById<Button>(R.id.updateBackButton)
        updateBackButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val updateButton = findViewById<Button>(R.id.updateButton)
        updateButton.setOnClickListener {
            if(updateFirstName.text.toString() == "" || updateFirstName.toString().contains("[a-z]".toRegex())){
                updateFirstName.setText(intent.getStringExtra("firstName").toString())
            }
            if(updateLastName.text.toString() == "" || updateLastName.toString().contains("[a-z]".toRegex())){
                updateLastName.setText(intent.getStringExtra("lastName").toString())
            }
            if(updateAge.text.toString() == "" || updateLastName.toString().lowercase().contains("[0-9]".toRegex())){
                updateAge.setText(intent.getIntExtra("age", 0).toString())
            }
            if(updateGender.text.toString() == "" || (updateGender.text.toString().lowercase() != "male" || updateGender.text.toString().lowercase() != "female")){
                updateGender.setText(intent.getStringExtra("gender"))
            }
            // If age is set to an invalid number, reset it
            try {
                updateAge.text.toString().toInt()
            }catch (e:java.lang.NumberFormatException){
                updateAge.setText(intent.getIntExtra("age", 0).toString())
            }
            updateCall(Person(intent.getIntExtra("id", 0), updateFirstName.text.toString(), updateLastName.text.toString(), updateAge.text.toString().toInt() , updateGender.toString())){
                if (it == 200){
                    runOnUiThread {
                        updateSuccess.setText("Success!")
                        updateSuccess.setBackgroundResource(R.color.success)
                    }
                }
            }
        }

    }

}

