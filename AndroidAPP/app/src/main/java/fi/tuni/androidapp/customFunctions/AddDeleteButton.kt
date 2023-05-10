package fi.tuni.androidapp.customFunctions

import android.app.Activity
import android.content.Context
import android.widget.ImageButton
import android.widget.LinearLayout
import fi.tuni.androidapp.R
import kotlin.concurrent.thread

fun addDeleteButton(id: Int, layout: LinearLayout,userBlock: LinearLayout,activity: Activity, context: Context): Unit {
    val deleteButton = ImageButton(context)
    deleteButton.setImageResource(R.drawable.ic_delete)
    deleteButton.setBackgroundResource(R.color.transparent)
    deleteButton.setOnClickListener {
        activity.runOnUiThread{
            deleteCall(id)
            // https://stackoverflow.com/questions/25580944/how-can-i-add-and-remove-element-dynamically-from-linear-layout
            // I'm adding the stackoverflow urls here so that its kind of proof that
            // I dont copypaste from stackoverflow
            layout.removeView(userBlock)
        }
    }
    userBlock.addView(deleteButton)
}