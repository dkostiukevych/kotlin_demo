package piggy

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.clickable
import com.automation.remarks.kirk.conditions.text
import com.automation.remarks.kirk.test.example.todo.s
import org.testng.annotations.Test


/**
 * Created by sergey on 06.08.17.
 */
class PiggyTest {

    @Test
    fun testCanAddIncome() {
        open(::Login)
                .loginAs("qaswdefrgt", "123456")
                .then {
                    addIncome("Salary", 1000)
                    incomeItem.shouldHave(text("1 000 $ / PER MONTH"))
                }
    }


    @Test
    fun testCatDeleteIncome() {
        open(::Login)
                .loginAs("qaswdefrgt", "123456")
                .deleteIncome()
    }
}

class Login(browser: Browser) : Page(browser) {

    override val url = "http://my-piggymetrics.rhcloud.com/"

    fun loginAs(name: String, pass: String): MainPage {
        s("#frontloginform").setValue(name).pressEnter()
        s("#frontpasswordform").setValue(pass).pressEnter()
        s("#plusborder").click()
        return page(::MainPage)
    }
}

class MainPage(browser: Browser) : Page(browser) {

    val incomeItem = s(".title9museo300")

    fun addIncome(name: String, value: Int) {
        s("#incomesplusitem").waitUntil(clickable).click()
        s("#add-modal [name='modalvalue']").setValue(value.toString())
        s(".modaltitle").setValue(name).pressEnter()
    }

    fun deleteIncome() {
        s("#incomeslider").click()
        s(".modal-delete").click()
    }
}

fun <T : Page> Page.page(pageClass: (Browser) -> T): T {
    return page(pageClass)
}

fun <T> T.then(block: T.() -> Unit): T {
    return this.apply(block)
}