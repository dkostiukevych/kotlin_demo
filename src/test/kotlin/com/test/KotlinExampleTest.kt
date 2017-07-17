package com.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.contain
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.drive
import org.testng.annotations.Test
import java.io.File

/**
 * Created by sergey on 16.07.17.
 */
class KotlinExampleTest {

    @Test
    fun testCanLogin() {
        Browser.drive {
            to(::LoginPage).login("admin", "admin")
            at(::MainPage).logo.should(have.text("Video service"))
            at(::MainPage) {
                uploadVideo("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi")
                all("[data-parent='#accordion'] strong").should(contain.elementWithText("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi"))
            }
        }
    }
}

fun KElement.uploadFile(name: String) {
    val resource = Thread.currentThread().contextClassLoader.getResource(name)
    this.setValue(File(resource.toURI()).canonicalPath)
}

internal class LoginPage(browser: Browser) : Page(browser) {

    override val url: String?
        get() = "/"

    fun login(name: String, password: String) {
        element("#inputEmail3").setValue(name)
        element("#inputPassword3").setValue(password)
        element("#parent > form > div:nth-child(3) > div > button").click()
    }
}

internal class MainPage(browser: Browser) : Page(browser) {

    var logo = element("a.navbar-brand")

    fun uploadVideo(name: String) {
        element("#filestyle-0").uploadFile(name)
        element("#upload_submit > button").click()
    }
}