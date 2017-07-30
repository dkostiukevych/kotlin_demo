package demo.tests

import com.automation.remarks.kirk.AbstractKirkEventListener
import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.text
import com.automation.remarks.kirk.core.getLatestScreenshot
import com.automation.remarks.kirk.ext.select
import com.google.common.io.Files
import io.qameta.allure.Attachment
import org.openqa.selenium.WebDriver
import org.testng.annotations.Test
import java.io.IOException


/**
 * Created by sergey on 30.07.17.
 */
class DevToolsDemo {

    @Test fun testCanAddTwoNumbersListener() {
        val chrome = Browser(listener = DevTools())
        chrome.to("http://juliemr.github.io/protractor-demo/") {
            element("input[ng-model='first']") value "1"
            element("input[ng-model='second']") value "2"
            select("select[ng-model='operator']").selectOption("+")
            element("#gobutton").click()
            element("h2.ng-binding").shouldHave(text("4"))
        }
    }
}

class DevTools : AbstractKirkEventListener() {
    override fun onStart() {
        openDevTools()
    }

    override fun beforeNavigation(url: String, driver: WebDriver) {
        super.beforeNavigation(url, driver)
    }

    override fun afterNavigation(url: String, driver: WebDriver) {
        super.afterNavigation(url, driver)
    }

    override fun onFail(exception: Exception) {
        attachScreenshot()
    }


    @Attachment(value = "Screenshot", type = "image/png")
    fun attachScreenshot(): ByteArray {
        try {
            return Files.toByteArray(getLatestScreenshot())
        } catch (e: IOException) {
            e.printStackTrace()
            return ByteArray(0)
        }
    }
}