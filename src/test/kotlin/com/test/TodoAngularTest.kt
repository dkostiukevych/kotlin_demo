package com.automation.remarks.kirk.test.example.todo

import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.conditions.exactText
import com.automation.remarks.kirk.conditions.size
import com.automation.remarks.kirk.conditions.text
import com.automation.remarks.kirk.conditions.visible
import org.testng.annotations.Test

/**
 * Created by sergey on 09.07.17.
 */

class TodoAngularTest {

//    @BeforeClass fun setUp() {
//        val browser = DesiredCapabilities()
//        browser.browserName = "chrome"
//        browser.version = "59.0"
//        browser.setCapability("enableVNC", true)
//
//        val driver = RemoteWebDriver(
//                URI.create("http://35.202.183.239:4444/wd/hub").toURL(),
//                browser
//        )
//
//        driverFactory.setWebDriver(driver)
//    }



    @Test fun testCanAddNewTaskAndDelete() {
        open(::TodoPage) {
            addTasks("Item0")
            taskList.shouldHave(size(1))
            deleteTask("Item0")
            taskList.should(size(0))
        }
    }

    @Test fun testCanDeactivateTask() {
        open(::TodoPage) {
            addTasks("A", "B", "C")
            deactivateTask("A")
            counter.shouldHave(text("2"))
            goToCompletedTab()
            taskList.shouldHave(exactText("A"))
        }
    }

    @Test fun testCanDeactivateTaskStale() {
        open(::TodoPage) {
            addTasks("A", "B", "C")
            val taskA = tasks[0].waitUntil(visible)
            deleteTask("A")
            taskA.shouldHave(text("B"))
        }
    }
}