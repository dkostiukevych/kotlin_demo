package demo.tests

import com.automation.remarks.kirk.AbstractKirkEventListener
import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.text
import com.automation.remarks.kirk.core.Select
import com.automation.remarks.kirk.core.getLatestScreenshot
import com.google.common.io.Files
import io.qameta.allure.Attachment
import org.testng.annotations.Test
import java.io.IOException


/**
 * Created by sergey on 30.07.17.
 */
class DevToolsDemo {

    @Test fun testCanAddTwoNumbersListener() {
        val chrome = Browser(listener = DevTools())
        chrome.to(::Calculator) {
            first value 1
            second value 2
            select option "+"
            goBtn.click()
            result shouldHave text("4")
        }
    }
}

class DevTools : AbstractKirkEventListener() {
    override fun onStart() {
        openDevTools()
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

infix fun Select.option(option: String) {
    this.selectOption(option)
}