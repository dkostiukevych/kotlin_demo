package org.calculator.test

import com.automation.remarks.kirk.AbstractKirkEventListener
import com.automation.remarks.kirk.Kirk.Companion.drive
import org.openqa.selenium.JavascriptException
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.testng.annotations.Test

class NavigationApiTest{

    @Test
    fun testCanGetNavigationApiData() {
        drive{
            to("http://google.com")
            to("http://automation-remarks.com/")
            to("http://automation-remarks.com/author/")
        }
    }
}

class Navigation : AbstractKirkEventListener() {
    override fun afterNavigation(url: String, driver: WebDriver) {
        driver as JavascriptExecutor
        val loadEventEnd = driver.executeScript("return window.performance.timing.loadEventEnd;") as Long
        val navigationStart = driver.executeScript("return window.performance.timing.navigationStart;") as Long
        println("Page $url Load Time is ${(loadEventEnd - navigationStart)/1000.0} seconds.")
    }
}