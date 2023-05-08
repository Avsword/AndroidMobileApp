package fi.tuni.androidapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView);
        val GetAllView = GetAllView()
        val SearchView = SearchView()
        val AddView = AddView()
        val UpdateView = UpdateView()
        val DeleteView = DeleteView()
        setCurrentFragment(GetAllView)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.allUsers->setCurrentFragment(GetAllView)
                R.id.search->setCurrentFragment(SearchView)
                R.id.add->setCurrentFragment(AddView)
                R.id.update->setCurrentFragment(UpdateView)
                R.id.delete->setCurrentFragment(DeleteView)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameLayout,fragment)
            commit()
        }
}