package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment = HomeFragment()

            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_stopwatch -> selectedFragment = StopwatchFragment ()
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()

            true }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
    }
}