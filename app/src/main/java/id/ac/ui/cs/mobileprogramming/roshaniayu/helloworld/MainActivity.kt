package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var number: Int = 0
    private lateinit var numberText: TextView
    private lateinit var addButton: Button
    private external fun processAddition(number: Int): Int

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberText = findViewById(R.id.number_text)
        addButton = findViewById(R.id.add_button)

        addButton.setOnClickListener {
//            addOne()
            number = processAddition(number)
            numberText.text = number.toString()
        }
    }

//    private fun addOne() {
//        number += 1
//        numberText.text = number.toString()
//    }
}