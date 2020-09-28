package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HelloWorldUnitTest {
    private lateinit var goodnightWorldText: String
    private lateinit var descriptionText: String
    private lateinit var lightModeText: String

    @Before
    fun initString() {
        // Specify a valid string
        goodnightWorldText = "Goodnight World!"
        descriptionText = "See you on the next activity :)"
        lightModeText = "Light Mode"
    }

    @Test
    fun getHelloWordsFunctionReturnsString() {
        val activity = MainActivity()
        val helloWordsText = activity.getHelloWords()
        assertNotEquals(helloWordsText, "")
        assertEquals(helloWordsText, goodnightWorldText)
    }

    @Test
    fun getDescriptionWordsFunctionReturnsString() {
        val activity = MainActivity()
        val descriptionWordsText = activity.geDescriptionWords()
        assertNotEquals(descriptionWordsText, "")
        assertEquals(descriptionWordsText, descriptionText)
    }

    @Test
    fun getButtonWordsFunctionReturnsString() {
        val activity = MainActivity()
        val buttonWordsText = activity.getButtonWords()
        assertNotEquals(buttonWordsText, "")
        assertEquals(buttonWordsText, lightModeText)
    }
}