package org.calculator.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.text
import io.github.bonigarcia.wdm.ChromeDriverManager
import net.lightbody.bmp.BrowserMobProxyServer
import net.lightbody.bmp.client.ClientUtil
import net.lightbody.bmp.proxy.CaptureType
import org.calculator.page.Calculator
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import net.lightbody.bmp.core.har.Har
import java.io.File


class BrowserMobProxyExample {


    @Test
    fun testName() {
        ChromeDriverManager.getInstance().setup()
        val proxy = BrowserMobProxyServer()
        proxy.start(0)

        // get the Selenium proxy object
        val seleniumProxy = ClientUtil.createSeleniumProxy(proxy)

        val capabilities = DesiredCapabilities()
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        val driver = ChromeDriver(capabilities)

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT)

        // create a new HAR with the label "yahoo.com"
        proxy.newHar("yahoo.com")

        val browser = Browser(driver).apply {
            to(::Calculator) {
                first.setValue("1")
                second.setValue("2")
                select.selectOption("+")
                goBtn.click()
                result.shouldHave(text("3"))
            }
        }

        val har = proxy.har
        val harFile = File("harFile.har")
        har.writeTo(harFile)

        proxy.stop()
        browser.quit()

    }
}