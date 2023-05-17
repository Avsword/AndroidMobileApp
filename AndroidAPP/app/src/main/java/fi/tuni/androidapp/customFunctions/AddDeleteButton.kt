package fi.tuni.androidapp.customFunctions

import android.app.Activity
import android.content.Context
import android.widget.ImageButton
import android.widget.LinearLayout
import fi.tuni.androidapp.R
import kotlin.concurrent.thread

/**
 * Adds a Delete-button for a user
 *
 * The function adds an ImageButton to a
 *  single user and sets an onclick listener
 *  to delete the whole user from the
 *  linearLayout, should it be pressed.
 *
 *  @param id ID of the user to be deleted
 *  @param layout The entire linearlayout full of users.
 *  @param buttonsBlock Update and Delete buttons are in a separate block.
 *  @param activity Required for runOnUiThread.
 *  @param context Context required for creating new items.
 *  @param userBlock The "block" of a single user.
 */
fun addDeleteButton(
    id: Int,
    layout: LinearLayout,
    buttonsBlock: LinearLayout,
    activity: Activity,
    context: Context,
    userBlock: LinearLayout): Unit {
        val deleteButton = ImageButton(context)
        deleteButton.setImageResource(R.drawable.ic_delete)
        deleteButton.setBackgroundResource(R.color.transparent)
        deleteButton.setOnClickListener {
            activity.runOnUiThread{
                deleteCall(id)
                // https://stackoverflow.com/questions/25580944/how-can-i-add-and-remove-element-dynamically-from-linear-layout
                // I'm adding the stackoverflow urls here so that its kind of proof that
                // I dont copypaste from gpt
                layout.removeView(userBlock)
            }
        }
        buttonsBlock.addView(deleteButton)
}