package fi.tuni.androidapp.customFunctions


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.content.ContextCompat.startActivity
import fi.tuni.androidapp.*


fun addEditButton(id: Int, layout: LinearLayout,userBlock: LinearLayout,activity: Activity, context: Context): Unit {
    // Edit Button
    val editButton = ImageButton(context)
    editButton.setImageResource(R.drawable.ic_update)
    editButton.setBackgroundResource(R.color.transparent)
    editButton.setOnClickListener {
        // https://developer.android.com/reference/android/widget/PopupWindow
        startActivity(context, Intent(activity, UpdateView::class.java), Bundle())
    }

    userBlock.addView(editButton)
}