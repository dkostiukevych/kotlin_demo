package piggy

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.text
import org.testng.annotations.Test

/**
 * Created by sergey on 06.08.17.
 */
class PiggyTest {

    @Test
    fun testCanAddIncome() {
        drive {
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