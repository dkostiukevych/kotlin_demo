package com.todo.page

import com.automation.remarks.kirk.AbstractKirkEventListener
import com.automation.remarks.kirk.core.getLatestScreenshot
import com.google.common.io.Files
import io.qameta.allure.Attachment
import org.openqa.selenium.WebDriver
import java.io.IOException

/**
 * Created by sepi on 26.08.17.
 */

@Attachment(value = "Screenshot", type = "image/png")
fun attachScreenshot(): ByteArray {
    return try {
        Files.toByteArray(getLatestScreenshot())
    } catch (e: IOException) {
        ByteArray(0)
    }
}

class FailListener : AbstractKirkEventListener() {
    override fun beforeNavigation(url: String, driver: WebDriver) {
        print("Navigate to $url")
    }

    override fun onFail(exception: Exception) {
        attachScreenshot()
    }
}