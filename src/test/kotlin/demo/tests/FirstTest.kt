package demo.tests

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import domain.User
import org.testng.annotations.Test
import pages.LoginPage
import pages.MainPage

/**
 * Created by sergey on 07.06.17.
 */
class FirstTest {

    @Test fun testCanLogin() {
        val admin = User("admin", "admin")
        Browser.drive {
            to(::LoginPage) {
                loginAs(admin)
            }.thanAt(::MainPage) {
                logo.should(have.text("Video service"))
            }
        }
    }
}


