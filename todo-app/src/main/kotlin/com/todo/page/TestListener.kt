package com.todo.page

import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult

class TestListener : ITestListener {
    override fun onFinish(context: ITestContext?) {
    }

    override fun onTestSkipped(result: ITestResult?) {
    }

    override fun onTestSuccess(result: ITestResult?) {
    }

    override fun onTestFailure(result: ITestResult?) {
        attachScreenshot()
    }

    override fun onTestFailedButWithinSuccessPercentage(result: ITestResult?) {
    }

    override fun onTestStart(result: ITestResult?) {
    }

    override fun onStart(context: ITestContext?) {
    }

}