package com.zcf.testapp.fragment4

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.zcf.testapp.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.*
import org.junit.Test

class BottomNav4FragmentTest {

    @Test
    fun checkScreen() {
        val fragmentArgs = BottomNav4FragmentArgs.Builder().setContentId(10).build()
        val scenario = launchFragmentInContainer<BottomNav4Fragment>(fragmentArgs.toBundle(), R.style.Theme_TestApp)
        onView(withId(R.id.text_content)).check(
            matches(allOf(
                isDisplayed(),
                withText("Bottom 4 Content: 10")
            ))
        )
    }
}