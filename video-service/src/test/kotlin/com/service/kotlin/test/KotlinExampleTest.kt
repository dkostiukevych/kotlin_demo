package com.service.kotlin.test

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.Kirk.Companion.at
import com.automation.remarks.kirk.conditions.elementWithText
import com.automation.remarks.kirk.conditions.text
import com.service.page.LoginPage
import com.service.page.MainPage
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 16.07.17.
 */
class KotlinExampleTest {

    @BeforeClass
    fun setUp() {
        Kirk.open(::LoginPage)
                .loginAs("admin", "admin")
    }

    @Test
    fun testCanLogin() {
        at(::MainPage) {
            uploadVideo("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi")
            videoFiles.shouldHave(elementWithText("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi"))
        }
    }
}