package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var isDark : Boolean? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isDark = true
        val helloText = findViewById<TextView>(R.id.helloText)
        val descText = findViewById<TextView>(R.id.descText)
        val modeButton = findViewById<Button>(R.id.modeButton)

        // when button clicks
        modeButton.setOnClickListener {
            if (isDark == true) {
                // change hello, desc, and mode button text
                helloText.text = getString(R.string.hello_text_dark)
                descText.text = getString(R.string.desc_text_dark)
                modeButton.text = getString(R.string.light_mode)

                // change hello text, desc text, and background color
                helloText.setTextColor(Color.parseColor(getString(R.color.colorTextDark)))
                descText.setTextColor(Color.parseColor(getString(R.color.colorSecondaryTextDark)))
                window.decorView.setBackgroundColor(Color.parseColor(getString(R.color.colorDark)))

                isDark = false
            } else {
                // change hello, desc, and mode button text
                helloText.text = getString(R.string.hello_text)
                descText.text = getString(R.string.desc_text)
                modeButton.text = getString(R.string.dark_mode)

                // change hello text, desc text, and background color
                helloText.setTextColor(Color.parseColor(getString(R.color.colorLight)))
                descText.setTextColor(Color.parseColor(getString(R.color.colorLight)))
                window.decorView.setBackgroundColor(Color.parseColor(getString(R.color.colorTextLight)))

                isDark = true
            }
        }
    }
}