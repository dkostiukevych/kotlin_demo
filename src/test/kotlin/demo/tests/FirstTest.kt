package demo.tests

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Configuration.baseUrl
import com.codeborne.selenide.Configuration.browser
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import pages.LoginPage
import test.User

/**
 * Created by sergey on 07.06.17.
 */
class FirstTest {

    @BeforeClass fun setUp() {
        ChromeDriverManager.getInstance().setup()
        browser = "chrome"
        baseUrl = "http://localhost:8086"
    }


    @Test fun testCanLogin() {
        val admin = User("admin", "admin")

        LoginPage().open()
                .loginAs(admin)
                .logo
                .shouldBe(visible)
    }
}


