package com.example.testecarrefour.userinfo

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasCategories
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.testecarrefour.domain.models.UserProfile
import com.example.testecarrefour.launchFragmentInHiltContainer
import com.example.testecarrefour.ui.MainActivity
import com.example.testecarrefour.ui.home.HomeFragment
import com.example.testecarrefour.ui.home.HomeFragmentDirections
import com.example.testecarrefour.ui.reposinfo.RepositoriesBottomSheetDialog
import com.example.testecarrefour.ui.userinfo.UserInfoFragment
import com.example.testecarrefour.ui.userinfo.UserInfoFragmentDirections
import com.example.testecarrefour.utils.IdleResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@HiltAndroidTest
class UserInfoFragmentTest {
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

    @After
    fun releaseIntents() {
        Intents.release()
    }

    @Before
    fun setup() {
        Intents.init()
        hiltRule.inject()
        val userProfile =
            UserProfile(
                "userLogin",
                "userAvatar",
                "profileUrl",
                "userName",
                "company",
                "US",
                0,
                0,
                0
            )
        launchFragmentInHiltContainer<UserInfoFragment>(
            fragmentArgs = bundleOf(
                "userName" to "args",
                "userProfile" to userProfile
            )
        ) {}
    }

    @Test
    fun `is_checkout_repos_button_available`() {
        launchFragmentInHiltContainer<UserInfoFragment>(fragmentArgs = bundleOf("userName" to "")) {}
        onView(withId(com.example.testecarrefour.R.id.checkout_repos)).check(matches(isDisplayed()))
    }

    @Test
    fun `is_checkout_profile_button_available`() {
        onView(withId(com.example.testecarrefour.R.id.checkout_profile)).check(matches(isDisplayed()))
    }

    @Test
    fun `navigate_to_repos_info`() {
        val mockNavController = Mockito.mock(NavController::class.java)
        val action =
            UserInfoFragmentDirections.actionUserInfoFragmentToUserInfoBottomSheetDialog("")
        launchFragmentInHiltContainer<RepositoriesBottomSheetDialog>(fragmentArgs = bundleOf("userName" to "args")) {
            mockNavController.setGraph(com.example.testecarrefour.R.navigation.navigation_graph)
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        mockNavController.navigate(action)
        Mockito.verify(mockNavController).navigate(action)
    }

    @Test
    fun `checkout_profile_action_external_link`() {
        onView(withId(com.example.testecarrefour.R.id.checkout_profile)).perform(click())
        intended(allOf(hasAction(Intent.ACTION_VIEW)))
    }
}