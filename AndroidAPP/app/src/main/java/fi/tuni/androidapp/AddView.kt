package fi.tuni.androidapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import fi.tuni.androidapp.customFunctions.AddPerson
import fi.tuni.androidapp.customFunctions.Person
import fi.tuni.androidapp.customFunctions.addCall
import fi.tuni.androidapp.customFunctions.updateCall

/**
 * Tab for adding a new User.
 *
 * Adds textfields and radio buttons for the name, age and gender.
 * Also adds the "add-button"
 */
class AddView:Fragment(R.layout.addview) {

    /**
     * Initializes every field and adds clickListeners and onChange listeners
     * to the buttons and textfields.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var layout:LinearLayout = view.findViewById(R.id.addViewLayout)
        val addFirstName = EditText(requireContext())
        addFirstName.hint = "First name"


        val addLastName = EditText(requireContext())
        addLastName.hint = "Last name"

        val addAge = EditText(requireContext())
        addAge.inputType = InputType.TYPE_CLASS_NUMBER

        addAge.hint = "Age"

        val genderRadioGroup = RadioGroup(requireContext())
        val genderMaleRadioButton = RadioButton(requireContext())
        val genderFemaleRadioButton = RadioButton(requireContext())

        genderMaleRadioButton.hint = "Male"
        genderFemaleRadioButton.hint = "Female"
        genderMaleRadioButton.id = View.generateViewId()
        genderFemaleRadioButton.id =View.generateViewId()

        layout.addView(addFirstName)
        layout.addView(addLastName)
        layout.addView(addAge)
        genderRadioGroup.addView(genderMaleRadioButton)
        genderRadioGroup.addView(genderFemaleRadioButton)
        genderRadioGroup.check(genderMaleRadioButton.id)
        layout.addView(genderRadioGroup)

        val submitButton = Button(requireContext())
        submitButton.text = "Add"
        submitButton.isEnabled = false
        addFirstName.doAfterTextChanged {
            submitButton.text = "Add ${addFirstName.text.toString().trim()}"
        }
        submitButton.setOnClickListener {
            val context = view.context
            val checkedRadioButton = view.findViewById<RadioButton>(genderRadioGroup.checkedRadioButtonId)
            val checkedText = checkedRadioButton.text.toString()
            addCall(
                AddPerson(
                addFirstName.text.toString(),
                addLastName.text.toString(),
                addAge.text.toString().toInt(),
                checkedText)){
                if(it == 200){
                    activity?.runOnUiThread {
                        Toast.makeText(context, "${addFirstName.text.toString()} Successfully added!", Toast.LENGTH_SHORT).show()
                    }
                }
                Log.d("addCall", it.toString())
            }
        }
        layout.addView(submitButton)


        addFirstName.doAfterTextChanged { inputChanged(addFirstName, addLastName, addAge, submitButton) }
        addLastName.doAfterTextChanged { inputChanged(addFirstName, addLastName, addAge, submitButton) }
        addAge.doAfterTextChanged { inputChanged(addFirstName, addLastName, addAge, submitButton) }
    }

    /**
     * Function runs when a textfield is edited.
     * Determines whether or not values are valid - if they are, enable the button.
     *
     * @param addFirstName First name textfield
     * @param addLastName Last name textfield
     * @param addAge Age textfield
     * @param submitButton "Add"-button to be enabled/disabled.
     */
    @SuppressLint("ResourceAsColor")
    private fun inputChanged(addFirstName: EditText, addLastName: EditText, addAge: EditText, submitButton: Button):Unit{
        // Guard clauses to validate input etc etc
        if(addFirstName.text.toString().isEmpty()){
            submitButton.isEnabled = false
            Log.d("addview","firstname")
            return
        }
        if(addLastName.text.toString().isEmpty()){
            submitButton.isEnabled = false
            Log.d("addview","lastname")
            return
        }
        if(addAge.text.toString()
                .isEmpty()){
            submitButton.isEnabled = false
            Log.d("addview","age")
            return
        }
        // If hasnt returned yet, enable button
        submitButton.isEnabled = true
    }
}




