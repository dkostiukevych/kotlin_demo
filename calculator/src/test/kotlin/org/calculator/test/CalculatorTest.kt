package org.calculator.test

import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.text
import org.calculator.page.Calculator
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 13.07.17.
 */

infix fun KElement.value(value: Any) {
    setValue(value.toString())
}

class CalculatorTest {

    @Test
    fun testCanDivide() {
        Kirk.open(::Calculator) {
            first value 10
            second value 2
            select.selectOption("/")
            goBtn.click()
            result.shouldHave(text("3"))
        }
    }
}