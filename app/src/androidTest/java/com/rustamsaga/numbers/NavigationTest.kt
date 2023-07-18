package com.rustamsaga.numbers

import androidx.test.espresso.Espresso.pressBack
import org.junit.Test

class NavigationTest : BaseTest() {

    @Test
    fun details_navigation() {
        val numbersPage = NumbersPage()
        numbersPage.run {
            input.typeText("10")
            getFactButton.click()

            recycler.run {
                viewInRecycler(0, titleItem).checkText("10")
                viewInRecycler(0, subTitleItem).checkText("fact about 10")
                viewInRecycler(0, subTitleItem).click()
            }
        }

        DetailsPage().details.checkText("10\n\nfact about 10")

        pressBack()

        numbersPage.run {
            recycler.run {
                viewInRecycler(0, titleItem).checkText("10")
                viewInRecycler(0, subTitleItem).checkText("fact about 10")
            }
        }
    }
}