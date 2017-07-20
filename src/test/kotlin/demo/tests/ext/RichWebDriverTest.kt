package demo.tests.ext

import com.pages.*
import domain.User
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by sergey on 17.07.17.
 */
class RichWebDriverTest {

    @BeforeMethod
    fun setUp() {
        ChromeDriverManager.getInstance().setup()
    }

    @Test fun testCanDrive() {
        val driver = ChromeDriver()
        driver.open("http://juliemr.github.io/protractor-demo/")
        driver.apply {
            find("input[ng-model='first']").setValue(1)
            find("input[ng-model='second']").setValue(2)
            select("select[ng-model='operator']").selectByVisibleText("/")
            find("#gobutton").click()
            wait().until { find("h2.ng-binding").text == "0.5" }
        }


        val userList = listOf(User("Ivan", 26), User("Dima", 12))
        userList.filter { it.name == "Ivan" }.first().age == 26

    }


}