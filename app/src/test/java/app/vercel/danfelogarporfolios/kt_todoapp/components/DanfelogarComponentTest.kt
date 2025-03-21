package app.vercel.danfelogarporfolios.kt_todoapp.components

import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.doubleClick
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.test.swipeUp
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.DanfelogarComponent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DanfelogarComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun MyFirstTest() {
        composeTestRule.setContent {
            DanfelogarComponent()
        }

        //Finder
        composeTestRule.onNodeWithText(
            text = "TODO",
            ignoreCase = true
        )
        composeTestRule.onNodeWithTag(testTag = "component1")
        composeTestRule.onNodeWithContentDescription("superImage")

        composeTestRule.onAllNodesWithText(":")
        composeTestRule.onAllNodesWithTag("component1")
        composeTestRule.onAllNodesWithContentDescription("visualIcon")

        //Actions
        composeTestRule.onNodeWithText(
            text = "TODO",
            ignoreCase = true
        ).performClick()

        composeTestRule.onAllNodesWithText(
            text = "TODO",
            ignoreCase = true
        ).onFirst().performClick()
        composeTestRule.onNodeWithText(text = "Danfe").performTouchInput {
            longClick()
            doubleClick()
            swipeDown()
            swipeUp()
            swipeLeft()
            swipeRight()
        }
        composeTestRule.onNodeWithText(text = "Danfe").performScrollTo()
        composeTestRule.onNodeWithText(text = "Danfe").performImeAction()
        composeTestRule.onNodeWithText(text = "Danfe").performTextInput("something")
        composeTestRule.onNodeWithText(text = "Danfe").performTextReplacement("some thing")

        //Assertions
        composeTestRule.onNodeWithText(text = "Danfe").assertExists()
        composeTestRule.onNodeWithText(text = "Danfe").assertDoesNotExist()
        composeTestRule.onNodeWithText(text = "Danfe").assertDoesNotExist()
        composeTestRule.onNodeWithText(text = "Danfe").assertContentDescriptionContains("Dnaf")
        composeTestRule.onNodeWithText(text = "Danfe").assertContentDescriptionContains("danfe")
        composeTestRule.onNodeWithText(text = "Danfe").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = "Danfe").assertIsNotDisplayed()
        composeTestRule.onNodeWithText(text = "Danfe").assertIsNotEnabled()
        composeTestRule.onNodeWithText(text = "Danfe").assertIsNotSelected()
        composeTestRule.onNodeWithText(text = "Danfe").assertIsNotFocused()
        composeTestRule.onNodeWithText(text = "Danfe").assertIsFocused()
        composeTestRule.onNodeWithText(text = "Danfe").assertIsOn()
        composeTestRule.onNodeWithText(text = "Danfe").assertIsOff()
        composeTestRule.onNodeWithText(text = "Danfe").assertTextEquals()
        composeTestRule.onNodeWithText(text = "Danfe").assertTextEquals("Danfe")

    }

    @Test
    fun whenComponentStart_thenVerifyContentIsDanfe(){
        composeTestRule.setContent {
            DanfelogarComponent()
        }

        composeTestRule
            .onNodeWithText(text = "Danfe", ignoreCase = true)
            .assertExists()
    }
}