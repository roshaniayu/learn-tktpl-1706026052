package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    var isDark : Boolean = true

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val modeButton = view.findViewById<Button>(R.id.modeButton)
        modeButton.setOnClickListener {
            val helloText = view.findViewById<TextView>(R.id.helloText)
            val descText = view.findViewById<TextView>(R.id.descText)
            val modeButton = view.findViewById<Button>(R.id.modeButton)

            if (isDark) {
                // Change hello, desc, and mode button to dark mode text
                helloText.text = getHelloWords()
                descText.text = geDescriptionWords()
                modeButton.text = getButtonWords()

                // Change hello text, desc text, and background to dark mode color
                helloText.setTextColor(Color.parseColor(getString(R.color.colorTextDark)))
                descText.setTextColor(Color.parseColor(getString(R.color.colorSecondaryTextDark)))
                view.setBackgroundColor(Color.parseColor(getString(R.color.colorDark)))

                isDark = false
            } else {
                // Change hello, desc, and mode button to light mode text
                helloText.text = getHelloWords()
                descText.text = geDescriptionWords()
                modeButton.text = getButtonWords()

                // Change hello text, desc text, and background to light mode color
                helloText.setTextColor(Color.parseColor(getString(R.color.colorTextLight)))
                descText.setTextColor(Color.parseColor(getString(R.color.colorTextLight)))
                view.setBackgroundColor(Color.parseColor(getString(R.color.colorLight)))

                isDark = true
            }
        }

        return view
    }

    private fun getHelloWords() : String {
        if (isDark) {
            return getString(R.string.hello_text_dark)
        }
        return getString(R.string.hello_text)
    }

    private fun geDescriptionWords() : String {
        if (isDark) {
            return getString(R.string.desc_text_dark)
        }
        return getString(R.string.desc_text)
    }

    private fun getButtonWords() : String {
        if (isDark) {
            return getString(R.string.light_mode)
        }
        return getString(R.string.dark_mode)
    }
}