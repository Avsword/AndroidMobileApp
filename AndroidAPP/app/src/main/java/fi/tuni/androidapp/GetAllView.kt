package fi.tuni.androidapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import fi.tuni.androidapp.customFunctions.addDeleteButton
import fi.tuni.androidapp.customFunctions.addEditButton
import fi.tuni.androidapp.customFunctions.deleteCall
import fi.tuni.androidapp.customFunctions.fetch


class GetAllView: Fragment(R.layout.getallview) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayout = view.findViewById<LinearLayout>(R.id.GetAllTable)

        fetch(){
            Log.d("GetAllView", it.users!![0].toString())
            for (person in it.users!!){
                // Updates must happen in the uithread.
                activity?.runOnUiThread {
                    // Create a "block" For each person.
                    val userBlock = LinearLayout(requireContext())
                    userBlock.orientation = LinearLayout.HORIZONTAL
                    userBlock.gravity = Gravity.CENTER_VERTICAL

                    // https://stackoverflow.com/questions/9685658/add-padding-on-view-programmatically
                    userBlock.setPadding(15)
                    // First and Last Name
                    val names = TextView(requireContext())
                    names.text = "${person.firstName} ${person.lastName} ${person.age} ${person.gender}"
                    names.gravity = Gravity.CENTER_VERTICAL
                    userBlock.addView(names)

                    val buttonsBlock = LinearLayout(requireContext())
                    buttonsBlock.gravity = Gravity.END
                    buttonsBlock.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    buttonsBlock.setPaddingRelative(0,0,30,0)
                    addEditButton(person, linearLayout, buttonsBlock, requireActivity()
                        ,requireContext())

                    addDeleteButton(person.id, linearLayout, buttonsBlock, requireActivity()
                    ,requireContext())
                    userBlock.addView(buttonsBlock)
                    linearLayout.addView(userBlock)
                }
            }
        }

    }



}