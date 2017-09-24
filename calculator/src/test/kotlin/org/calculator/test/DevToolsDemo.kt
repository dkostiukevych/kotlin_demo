package org.calculator.test

import com.automation.remarks.kirk.AbstractKirkEventListener
import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.KirkEventListener
import com.automation.remarks.kirk.conditions.text
import com.automation.remarks.kirk.core.Select
import org.calculator.page.Calculator
import org.calculator.utils.openDevTools
import org.testng.annotations.Test

/**
 * Created by sergey on 30.07.17.
 */
class DevToolsDemo {

    @Test
    fun testCanAddTwoNumbersListener() {
        drive{
            to(::Calculator) {
                first value 1
                second value 2
                select option "+"
                goBtn.click()
                result shouldHave text("4")
            }
        }
    }
}

class DevTools : AbstractKirkEventListener() {
    override fun onStart() {
        openDevTools()
    }
}

infix fun Select.option(option: String) {
    this.selectOption(option)
}