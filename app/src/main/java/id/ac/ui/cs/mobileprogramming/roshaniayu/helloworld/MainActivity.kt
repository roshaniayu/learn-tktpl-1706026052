package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.fragment.DiaryInputFragment
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.fragment.StopwatchFragment

class MainActivity : AppCompatActivity() {
    var stopwatchIsRunning: Boolean = false
    var stopwatchText: TextView? = null
    private var millisecondTime: Long = 0
    private var startTime: Long = 0
    private var timeBuff: Long = 0
    private var updateTime: Long = 0
    private var seconds: Int = 0
    private var minutes: Int = 0
    private var milliSeconds: Int = 0
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHandler = Handler()
        mRunnable = Runnable {
            millisecondTime = SystemClock.uptimeMillis() - startTime
            updateTime = timeBuff + millisecondTime;
            seconds = (updateTime / 1000).toInt();
            minutes = seconds / 60;
            seconds %= 60;
            milliSeconds = (updateTime % 100).toInt();
            mHandler.postDelayed(mRunnable, 0);
            stopwatchText?.text =
                (String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + ":" + String.format("%02d", milliSeconds));
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment = DiaryInputFragment()

            when (item.itemId) {
                R.id.nav_home -> selectedFragment = DiaryInputFragment()
                R.id.nav_stopwatch -> selectedFragment = StopwatchFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment).commit()

            true
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            DiaryInputFragment()
        )
            .commit()
    }

    fun hideKeyboard() {
        val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
    }

    fun startStopwatch() {
        startTime = SystemClock.uptimeMillis()
        mHandler.postDelayed(mRunnable, 0)
        stopwatchIsRunning = true
    }

    fun pauseStopwatch() {
        timeBuff += millisecondTime
        mHandler.removeCallbacks(mRunnable)
        stopwatchIsRunning = false
    }

    fun resetStopwatch() {
        millisecondTime = 0
        startTime = 0
        timeBuff = 0
        updateTime = 0
        seconds = 0
        minutes = 0
        milliSeconds = 0
    }
}