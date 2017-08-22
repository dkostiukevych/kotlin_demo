package piggy

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.text
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.testng.annotations.Test
import java.net.URI


/**
 * Created by sergey on 06.08.17.
 */
class PiggyTest {

    @Test
    fun testCanAddIncome() {
        val browser = DesiredCapabilities()
        browser.browserName = "chrome"
        browser.version = "59.0"

        val driver = RemoteWebDriver(
                URI.create("http://35.202.183.239:4444/wd/hub").toURL(),
                browser
        )

        drive(driver) {
            to("http://my-piggymetrics.rhcloud.com/")
            at(::Login).loginAs("qaswdefrgt", "123456")
            at(::MainPage) {
                addIncome("Salary", 1000)
                incomeItem.shouldHave(text("1 000 $ / PER MONTH"))
            }
        }
    }
}

class Login(browser: Browser) : Page(browser) {

    fun loginAs(name: String, pass: String) {
        element("#frontloginform").setValue(name).pressEnter()
        element("#frontpasswordform").setValue(pass).pressEnter()
        element("#plusborder").click()

    }
}

class MainPage(browser: Browser) : Page(browser) {

    val incomeItem = element(".title9museo300")

    fun addIncome(name: String, value: Int) {
        element("#noincomes .majorplusitem").click()
        element("#add-modal [name='modalvalue']").setValue(value.toString())
        element(".modaltitle").setValue(name).pressEnter()
    }

}