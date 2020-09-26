package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var isDark : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // when button clicks
        val modeButton = findViewById<Button>(R.id.modeButton)
        modeButton.setOnClickListener {
            buttonModeClickListener()
        }
    }

    @SuppressLint("ResourceType")
    private fun buttonModeClickListener() {
        val helloText = findViewById<TextView>(R.id.helloText)
        val descText = findViewById<TextView>(R.id.descText)
        val modeButton = findViewById<Button>(R.id.modeButton)

        if (isDark) {
            // change hello, desc, and mode button to dark mode text
            helloText.text = getHelloWords()
            descText.text = geDescriptionWords()
            modeButton.text = getButtonWords()

            // change hello text, desc text, and background to dark mode color
            helloText.setTextColor(Color.parseColor(getString(R.color.colorTextDark)))
            descText.setTextColor(Color.parseColor(getString(R.color.colorSecondaryTextDark)))
            window.decorView.setBackgroundColor(Color.parseColor(getString(R.color.colorDark)))

            isDark = false
        } else {
            // change hello, desc, and mode button to light mode text
            helloText.text = getHelloWords()
            descText.text = geDescriptionWords()
            modeButton.text = getButtonWords()

            // change hello text, desc text, and background to light mode color
            helloText.setTextColor(Color.parseColor(getString(R.color.colorLight)))
            descText.setTextColor(Color.parseColor(getString(R.color.colorLight)))
            window.decorView.setBackgroundColor(Color.parseColor(getString(R.color.colorTextLight)))

            isDark = true
        }
    }

    fun getHelloWords() : String {
        if (isDark) {
            return "Goodnight World!"
        }
        return getString(R.string.hello_text)
    }

    fun geDescriptionWords() : String {
        if (isDark) {
            return "See you on the next activity :)"
        }
        return getString(R.string.desc_text)
    }

    fun getButtonWords() : String {
        if (isDark) {
            return "Light Mode"
        }
        return getString(R.string.dark_mode)
    }
}