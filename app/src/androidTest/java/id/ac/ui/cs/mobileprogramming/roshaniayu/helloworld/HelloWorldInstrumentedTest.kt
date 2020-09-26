package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

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
    private val helloWorldText = "Hello World!"
    private val goodnightWorldText = "Goodnight World!"

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun whenButtonIsNotClickedItIsLightMode() {
        Espresso.onView(ViewMatchers.withId(R.id.helloText))
            .check(ViewAssertions.matches(ViewMatchers.withText(helloWorldText)))
    }

    @Test
    fun whenButtonIsClickedItIsDarkMode() {
        Espresso.onView(ViewMatchers.withId(R.id.modeButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.helloText))
            .check(ViewAssertions.matches(ViewMatchers.withText(goodnightWorldText)))
    }
}