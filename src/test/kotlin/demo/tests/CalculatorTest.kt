package demo.tests

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.drive
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import org.testng.annotations.Test

/**
 * Created by sergey on 13.07.17.
 */
class CalculatorTest {

    @Test fun testCanAddTwoNumbers() {
        Browser.drive {
            to("http://juliemr.github.io/protractor-demo/")
            element("input[ng-model='first']").setValue("1")
            element("input[ng-model='second']").setValue("2")
            select("select[ng-model='operator']").selectByVisibleText("+")
            element("#gobutton").click()
            element("h2.ng-binding").should(have.text("4"))
        }
    }
}

fun ngModel(model: String): By {
    return By.xpath("[ng-model='$model']")
}

fun Browser.select(by:String): Select {
    return Select(element(by).webElement)
}