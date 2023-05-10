package fi.tuni.androidapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class UpdateView: Activity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updateview)
        Log.d("UpdateView","Updateview activated")
        val updateFirstName = findViewById<EditText>(R.id.updateFirstName)
        updateFirstName.setBackgroundColor(R.color.black)
        val updateLastName = findViewById<EditText>(R.id.updateLastName)
        val updateAge = findViewById<EditText>(R.id.updateAge)
        val updateGender = findViewById<EditText>(R.id.updateGender)
        val updateButton = findViewById<Button>(R.id.updateButton)

    }

}