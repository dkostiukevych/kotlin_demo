package demo.tests

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.drive
import com.automation.remarks.kirk.core.driverFactory
import io.github.bonigarcia.wdm.ChromeDriverManager
import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.Select
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 13.07.17.
 */
class CalculatorTest {

    @BeforeClass
    fun setUp() {
        System.setProperty("kirk.baseUrl", "http://juliemr.github.io/protractor-demo/")
    }

    @Test fun testCanAddTwoNumbers() {
        ChromeDriverManager.getInstance().setup()
        val ops = ChromeOptions()
        ops.setBinary("/home/sergey/Github/kotlin-demo/devtools/chrome-wrapper.sh")
        ops.addArguments("--devtools-proxy-binary=/home/sergey/Github/kotlin-demo/devtools/devtools-proxy")

        val caps = DesiredCapabilities()

        caps.setCapability(ChromeOptions.CAPABILITY, ops)
        driverFactory.setWebDriver(ChromeDriver(caps))

        

        Browser.drive {
            to("http://juliemr.github.io/protractor-demo/")
            element("input[ng-model='first']").setValue("1")
            element("input[ng-model='second']").setValue("2")
            select("select[ng-model='operator']").selectByVisibleText("+")
            element("#gobutton").click()
            element("h2.ng-binding").should(have.text("3"))
        }
    }

    @Test fun testCanDivide() {
        Browser.drive {
            to(::Calculator) {
                first.setValue(10)
                second.setValue(2)
                select.selectByVisibleText("/")
                goBtn.click()
                result.shouldBe(5)
            }
        }
    }
}

class Calculator(browser: Browser) : Page(browser) {

    override val url: String?
        get() = "/"

    val first = element("input[ng-model='first']")
    val second = element("input[ng-model='second']")
    val goBtn = element("#gobutton")
    val result = element("h2.ng-binding")

    val select by lazy { browser.select("select[ng-model='operator']") }
}

fun ngModel(model: String): By {
    return By.xpath("[ng-model='$model']")
}

@Step
fun Browser.select(by: String): Select {
    return Select(element(by).webElement)
}

@Step
fun KElement.setValue(value: Any) {
    this.setValue(value.toString())
}

@Step
fun KElement.shouldBe(value: Any) {
    this.should(com.automation.remarks.kirk.conditions.have.text(value.toString()))
}