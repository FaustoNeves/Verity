package com.example.testecarrefour.reposinfo

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.testecarrefour.launchFragmentInHiltContainer
import com.example.testecarrefour.ui.MainActivity
import com.example.testecarrefour.ui.reposinfo.RepositoriesBottomSheetDialog
import com.example.testecarrefour.ui.userinfo.UserInfoFragment
import com.example.testecarrefour.utils.IdleResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RepositoriesBottomSheetDialogTest {
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
        val args = "mojombo"
        launchFragmentInHiltContainer<RepositoriesBottomSheetDialog>(fragmentArgs = bundleOf("userName" to args)) {
        }
    }

    @Test
    fun `is_repositories_dialog_visible`() {
        onView(withId(com.example.testecarrefour.R.id.repositories_list_title)).inRoot(isDialog())
    }

    @Test
    fun `is_repos_recycler_view_visible`() {
        onView(withId(com.example.testecarrefour.R.id.repos_recycler_view)).check(
            matches(
                isDisplayed()
            )
        )
    }
}