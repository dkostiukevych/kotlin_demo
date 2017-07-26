package demo.tests

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.ext.select
import io.qameta.allure.Step
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
//        ChromeDriverManager.getInstance().setup()
//        val ops = ChromeOptions()
//        ops.setBinary("/home/sergey/Github/kotlin-demo/devtools/chrome-wrapper.sh")
//        ops.addArguments("--devtools-proxy-binary=/home/sergey/Github/kotlin-demo/devtools/devtools-proxy")
//
//        val caps = DesiredCapabilities()
//
//        caps.setCapability(ChromeOptions.CAPABILITY, ops)

        drive {
            to("http://juliemr.github.io/protractor-demo/")
            element("input[ng-model='first']") value "1"
            element("input[ng-model='second']") value "2"
            select("select[ng-model='operator']").selectOption("+")
            element("#gobutton").click()
            element("h2.ng-binding").should(have.text("3"))
        }
    }

    @Test fun testCanDivide() {
        open(::Calculator) {
            first value 10
            second value 2
            select.selectOption("/")
            goBtn.click()
            result shouldBe 5
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

    val select = select("select[ng-model='operator']")
}


@Step
infix fun KElement.value(value: Any) {
    this.setValue(value.toString())
}

@Step
infix fun KElement.shouldBe(value: Any) {
    this.should(com.automation.remarks.kirk.conditions.have.text(value.toString()))
}