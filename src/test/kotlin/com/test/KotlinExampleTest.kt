package com.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.Kirk.Companion.at
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.ext.uploadFile
import demo.tests.value
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

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
            videoFiles.should(have.elementWithText("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi"))
        }
    }

    @Test fun testAdminCanAddNewUser() {
        open(::MainPage).usersTab.click()
        at(::UsersPage) {
            addNewUser("Ivan", "123456", "ivan@email.com")
            table.names.should(have.elementWithText("Ivan"))
        }
    }

    @Test fun testCanDeleteVideo() {
        at(::MainPage) {
            uploadVideo("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi")
            deleteVideo("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi")
            videoFiles.shouldNot(have.elementWithText("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi"))
        }
    }
}

internal class LoginPage(browser: Browser) : Page(browser) {

    override val url: String?
        get() = "/"

    fun login(name: String, password: String) {
        element("#inputEmail3") value name
        element("#inputPassword3") value password
        element("#parent > form > div:nth-child(3) > div > button").click()
    }
}

internal class MainPage(browser: Browser) : Page(browser) {

    val logo = element("a.navbar-brand")
    val usersTab = element("#users_link")
    val videoFiles = all("[data-parent='#accordion'] strong")

    fun uploadVideo(name: String) {
        element("#filestyle-0").uploadFile(name)
        element("#upload_submit > button").click()
    }

    fun deleteVideo(name: String) {
        s("//a/strong[text()='$name']/../../following-sibling::button[@id='delete_item']").click()
        s(".modal-footer a").click()
    }
}

internal class UsersPage(browser: Browser) : Page(browser) {

    override val url: String?
        get() = "/users"

    val table = Table(element("#users_table"))

    fun addNewUser(name: String, password: String, email: String) {
        s("#inputNewUserName") value name
        s("#inputNewUserPassword") value password
        s("#newUserEmail") value email
        s("#add_new_user_btn").click()
    }
}

internal class Table(root: KElement) {
    val names = root.all("td:nth-child(2)")
}

private val STARTING_BRACES = "(\\s*\\(\\s*)*"
private val XPATH_AXES = "ancestor|ancestor-or-self|attribute|child|descendant|descendant-or-self|following|following-sibling|parent|preceding|preceding-sibling|self"
private val XPATH_EXPRESSION_PATTERN = Regex("$STARTING_BRACES(/|($XPATH_AXES)::).*")

fun Page.s(locator: String): KElement {
    if (isXpath(locator)) {
        return this.element(com.automation.remarks.kirk.core.byXpath(locator))
    }
    return this.element(locator)
}

fun isXpath(locator: String): Boolean {
    return XPATH_EXPRESSION_PATTERN.matches(locator)
}
