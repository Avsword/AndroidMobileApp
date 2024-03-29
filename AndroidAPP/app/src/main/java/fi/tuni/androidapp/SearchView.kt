package fi.tuni.androidapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import fi.tuni.androidapp.customFunctions.searchCall

/**
 * View / Tab for searching for a user and displaying the
 *      fetched data in a minimalistic format.
 */
class SearchView:Fragment(R.layout.searchview) {
    /**
     * When created, initialize values and set onClickListener to the button.
     *
     * SearchButton calls "searchCall" and in it's callback it adds what is returned.
     *     The response can be empty if the search parameter is bad, so it has that handled.
     */
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layout = view.findViewById<LinearLayout>(R.id.searchViewLinearLayout)
        layout.setPaddingRelative(30,60,60,30)

        val userInput = EditText(requireContext())
        userInput.setHint("Search for a user")
        layout.addView(userInput)

        val searchButton = Button(requireContext().applicationContext)
        searchButton.text = "Search!"
        // SORRY FOR THE INDENTS BUT THIS IS REALLY AS CONCISE AS CAN BE WITHOUT
        //      COMPROMISING READABILITY.
        searchButton.setOnClickListener {
            searchCall(userInput.text.toString()){
                Log.d("searchCall", it.toString())
                if (it != null) {
                    try {
                        val searchResult = LinearLayout(requireContext().applicationContext)
                        val result = TextView(requireContext().applicationContext)
                        val person = it.users!![0]
                        result.text = "${person.firstName} ${person.lastName} ${person.age} ${person.gender}"
                        searchResult.addView(result)
                        activity?.runOnUiThread { layout.addView(searchResult) }
                    }catch (error: java.lang.IndexOutOfBoundsException){
                        val searchResult = LinearLayout(requireContext().applicationContext)
                        val result = TextView(requireContext().applicationContext)
                        result.text = "ERROR! Bad search parameter."
                        searchResult.addView(result)
                        activity?.runOnUiThread { layout.addView(searchResult) }
                    }
                }
            }
        }
        layout.addView(searchButton)
    }
}