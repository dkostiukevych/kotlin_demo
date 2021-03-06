package com.automation.remarks.kirk.test.example.todo


import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.conditions.exactText
import com.automation.remarks.kirk.conditions.size
import com.automation.remarks.kirk.conditions.text
import com.todo.page.FailListener
import com.todo.page.TestListener
import com.todo.page.TodoPage
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

/**
 * Created by sergey on 09.07.17.
 */
@Listeners(TestListener::class)
class TodoAngularTestWithListener {

    @Test
    fun testCanAddNewTaskAndDelete() {
        open(::TodoPage) {
            addTasks("Item0")
            taskList.shouldHave(size(1))
            deleteTask("Item0")
            taskList.should(size(0))
        }
    }

    @Test
    fun testCanDeactivateTask() {
        open(::TodoPage) {
            addTasks("A", "B", "C")
            deactivateTask("A")
            counter.shouldHave(text("1"))
            goToCompletedTab()
            taskList.shouldHave(exactText("A"))
        }
    }
}