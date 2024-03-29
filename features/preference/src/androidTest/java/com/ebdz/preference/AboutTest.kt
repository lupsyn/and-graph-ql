package com.ebdz.preference

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.ebdz.designsystem.Theme
import com.ebdz.preference.presentation.AboutScreen
import org.junit.Rule
import org.junit.Test

internal class AboutTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun test_informationIsDisplayed() {
        // When the view is loaded
        loadView()

        // Then the items are shown
        val appName = "AppName"
        val description = context.getString(R.string.about_description)
        val button = context.getString(R.string.about_button_project)

        composeTestRule.onNodeWithText(text = appName, ignoreCase = true).assertExists()
        composeTestRule.onNodeWithText(text = description, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText(text = button, useUnmergedTree = true).assertExists()
    }

    private fun loadView() {
        composeTestRule.setContent {
            Theme {
                AboutScreen(onUpPress = { })
            }
        }
    }
}
