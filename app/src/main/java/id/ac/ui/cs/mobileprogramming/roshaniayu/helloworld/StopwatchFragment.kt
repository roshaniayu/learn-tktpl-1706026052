package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.fragment.app.Fragment

class StopwatchFragment : Fragment() {
    private var stopwatch : Chronometer? = null
    private var pauseOffset : Long = 0
    var running : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        stopwatch = view.findViewById(R.id.stopwatch)
        val startButton = view.findViewById<Button>(R.id.start_stopwatch)
        val pauseButton = view.findViewById<Button>(R.id.pause_stopwatch)
        val resetButton = view.findViewById<Button>(R.id.reset_stopwatch)
        startButton.setOnClickListener { startStopwatch() }
        pauseButton.setOnClickListener { pauseStopwatch() }
        resetButton.setOnClickListener { resetStopwatch() }
        
        return view
    }

    private fun startStopwatch() {
        if (!running) {
            stopwatch?.base = SystemClock.elapsedRealtime() - pauseOffset
            stopwatch?.start();
            running = true;
        }
    }

    private fun pauseStopwatch() {
        if (running) {
            pauseOffset = SystemClock.elapsedRealtime() - stopwatch?.base!!
            stopwatch?.stop();
            running = false;
        }
    }

    private fun resetStopwatch() {
        stopwatch?.base = SystemClock.elapsedRealtime()
        pauseOffset = 0
    }
}