package fi.tuni.androidapp


import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * MainActivity hosts all of the tabs
 */
class MainActivity : AppCompatActivity() {
    /**
     * Initializing
     *
     * The oncreate method initialized every fragment and the bottom
     *  navigation view.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Hide the action bar
        supportActionBar?.hide()

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView);
        val getAllView = GetAllView()
        val searchView = SearchView()
        val addView = AddView()
//        val UpdateView = UpdateView()
//        val DeleteView = DeleteView()
        setCurrentFragment(getAllView)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.allUsers->setCurrentFragment(getAllView)
                R.id.search->setCurrentFragment(searchView)
                R.id.add->setCurrentFragment(addView)
//                R.id.update->setCurrentFragment(UpdateView)
//                R.id.delete->setCurrentFragment(DeleteView)
            }
            true
        }

    }

    /**
     * Responsible for setting the current tab
     *
     * The tabs use fragments as views. When an item in
     *   the bottomNavigationView is clicked on, we
     *   set the current fragment to match the clicked
     *   item.
     *
     */
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameLayout,fragment)
            commit()
        }

}