package codetest.virtusa.ui.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import codetest.virtusa.R
import codetest.virtusa.TestComponentRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class MainActivityTest {
    private val component =
        TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val chain = RuleChain.outerRule(component).around(main)


    @Before
    fun setup() {

    }

    @Test
    fun testCheckViewsDisplay() {
        main.launchActivity(Intent(component.getContext(), MainActivity::class.java))
        onView(withId(R.id.mySearchView))
            .check(matches(isDisplayed()))
    }
}