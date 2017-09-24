package org.calculator.test

import com.automation.remarks.kirk.core.getLatestScreenshot
import com.google.common.io.Files
import io.qameta.allure.Attachment
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult
import java.io.File
import java.io.IOException

class HarListener : ITestListener{
    override fun onFinish(context: ITestContext?) {
        attachHar()
    }

    override fun onTestSkipped(result: ITestResult?) {
    }

    override fun onTestSuccess(result: ITestResult?) {
    }

    override fun onTestFailure(result: ITestResult?) {
    }

    override fun onTestFailedButWithinSuccessPercentage(result: ITestResult?) {
    }

    override fun onTestStart(result: ITestResult?) {
    }

    override fun onStart(context: ITestContext?) {
    }

    @Attachment(fileExtension = ".har",value = "harFile",type = "application/json")
    private fun attachHar(): ByteArray {
        return try {
            Files.toByteArray(File("/home/sergey/Github/kotlin_demo/calculator/harFile.har"))
        } catch (e: IOException) {
            ByteArray(0)
        }
    }
}