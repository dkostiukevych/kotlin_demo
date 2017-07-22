package com.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.Kirk.Companion.at
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.contain
import com.automation.remarks.kirk.conditions.have
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import java.io.File

/**
 * Created by sergey on 16.07.17.
 */
class KotlinExampleTest {

    @BeforeClass
    fun setUp() {
        open(::LoginPage).login("admin", "admin")
    }

    @Test
    fun testCanLogin() {
        at(::MainPage) {
            logo.click()
            logo.should(have.text("Video service"))
            uploadVideo("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi")
            all("[data-parent='#accordion'] strong").should(contain.elementWithText("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi"))
        }
    }

    @Test fun testAdminCanAddNewUser() {
        open(::MainPage) { usersTab.click() }
        at(::UsersPage) {
            addNewUser("Ivan", "123456", "ivan@email.com")
            table.names.should(contain.elementWithText("Ivan"))
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

    val logo = element("a.navbar-brand")
    val usersTab = element("#users_link")

    fun uploadVideo(name: String) {
        element("#filestyle-0").uploadFile(name)
        element("#upload_submit > button").click()
    }
}

internal class UsersPage(browser: Browser) : Page(browser) {

    override val url: String?
        get() = "/users"

    val table = Table(element("#users_table"))

    fun addNewUser(name: String, password: String, email: String) {
        element("#inputNewUserName").setValue(name)
        element("#inputNewUserPassword").setValue(password)
        element("#newUserEmail").setValue(email)
        element("#add_new_user_btn").click()
    }
}

internal class Table(root: KElement) {

    val names = root.all("td:nth-child(2)")
}