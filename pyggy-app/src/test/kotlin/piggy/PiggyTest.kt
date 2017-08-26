package piggy

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.text
import my.piggy.pages.LoginPage
import org.testng.annotations.Test


/**
 * Created by sergey on 06.08.17.
 */

class PiggyTest {

    val browser = Browser(listener = FailListener())

    @Test
    fun testCanAddIncome() {
        browser.to(::LoginPage)
                .loginAs("qaswdefrgt", "123456")
                .apply {
                    addIncome("Salary", 1000)
                    incomeItem.shouldHave(text("1 000 $ / PER MONTH"))
                }
    }

    @Test
    fun testCatDeleteIncome() {
        browser.to(::LoginPage)
                .loginAs("qaswdefrgt", "123456")
                .deleteIncome()
    }
}