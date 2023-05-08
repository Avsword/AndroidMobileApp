package fi.tuni.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val GetAllView = GetAllView()
        val SearchView = SearchView
        val AddView = AddView
        val UpdateView = UpdateView
        val DeleteView = DeleteView

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}