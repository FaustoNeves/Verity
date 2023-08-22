package com.example.testecarrefour.home

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.testecarrefour.domain.models.UserProfile
import com.example.testecarrefour.launchFragmentInHiltContainer
import com.example.testecarrefour.ui.MainActivity
import com.example.testecarrefour.ui.home.HomeFragment
import com.example.testecarrefour.ui.home.HomeFragmentDirections
import com.example.testecarrefour.ui.userinfo.UserInfoFragment
import com.example.testecarrefour.utils.EMPTY_INPUT_SEARCH_FIELD
import com.example.testecarrefour.utils.IdleResource
import org.mockito.Mockito.verify
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(IdleResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(IdleResource.countingIdlingResource)
    }

    @Before
    fun setup() {
        hiltRule.inject()
        launchFragmentInHiltContainer<HomeFragment>()
    }

    @Test
    fun `is_user_recycler_view_visible`() {
        launchFragmentInHiltContainer<HomeFragment>()
        onView(withId(com.example.testecarrefour.R.id.users_recycler_view)).check(matches((isDisplayed())))
    }

    @Test
    fun `is_search_field_visible`() {
        onView(withId((com.example.testecarrefour.R.id.search_field_edit_text))).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun `display_error_for_empty_search_field`() {
        onView(withId((com.example.testecarrefour.R.id.search_field_edit_text))).perform((typeText("")))
        closeSoftKeyboard()
        onView(withId(com.example.testecarrefour.R.id.search_button)).perform(click())
        onView(withId((com.example.testecarrefour.R.id.search_field_edit_text))).check(
            matches(
                hasErrorText(EMPTY_INPUT_SEARCH_FIELD)
            )
        )
    }

    @Test
    fun `navigate_to_user_info_screen`() {
        val mockNavController = mock(NavController::class.java)
        val args = ""
        val action = HomeFragmentDirections.actionHomeFragmentToUserInfoFragment(
            "userName", UserProfile("", "", "", "", "", "", 0, 0, 0)
        )
        launchFragmentInHiltContainer<UserInfoFragment>(fragmentArgs = bundleOf("userName" to args)) {
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        mockNavController.navigate(action)
        verify(mockNavController).navigate(action)
    }
}