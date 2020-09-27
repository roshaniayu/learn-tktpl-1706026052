package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HelloWorldInstrumentedTest {
    private lateinit var helloWorldText: String
    private lateinit var goodnightWorldText: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initString() {
        // Specify a valid string
        helloWorldText = "Hello World!"
        goodnightWorldText = "Goodnight World!"
    }

    @Test
    fun whenButtonIsNotClickedItIsLightMode() {
        // Check hello world text
        onView(ViewMatchers.withId(R.id.helloText))
            .check(ViewAssertions.matches(ViewMatchers.withText(helloWorldText)))
    }

    @Test
    fun whenButtonIsClickedItIsDarkMode() {
        // Click night mode button
        onView(ViewMatchers.withId(R.id.modeButton)).perform(ViewActions.click())
        // Check goodnight world text
        onView(ViewMatchers.withId(R.id.helloText))
            .check(ViewAssertions.matches(ViewMatchers.withText(goodnightWorldText)))
    }
}