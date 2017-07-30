package com.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created by sergey on 17.07.17.
 */
fun WebDriver.open(url: String, autoClose: Boolean? = true) {
    autoClose(autoClose)
    get(url)
}

fun WebDriver.autoClose(enabled: Boolean? = true) {
    if (enabled!!) {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() = quit()
        })
    }
}

fun WebDriver.find(cssSelector: String): WebElement {
    return findElement(By.cssSelector(cssSelector))
}

fun WebElement.setValue(value: Any) {
    clear()
    sendKeys(value.toString())
}

fun WebDriver.wait(timeout: Long = 4): WebDriverWait {
    return WebDriverWait(this, timeout)
}

fun WebDriver.select(cssSelector: String): Select = Select(find(cssSelector))