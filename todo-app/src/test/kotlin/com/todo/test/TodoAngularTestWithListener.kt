package com.automation.remarks.kirk.test.example.todo


import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.conditions.exactText
import com.automation.remarks.kirk.conditions.size
import com.automation.remarks.kirk.conditions.text
import com.todo.page.FailListener
import com.todo.page.TodoPage
import org.testng.annotations.Test

/**
 * Created by sergey on 09.07.17.
 */

class TodoAngularTestWithListener {

    private val chrome = Browser(listener = FailListener())

    @Test
    fun testCanAddNewTaskAndDelete() {
        chrome.to(::TodoPage) {
            addTasks("Item0")
            taskList.shouldHave(size(1))
            deleteTask("Item0")
            taskList.should(size(0))
        }
    }

    @Test
    fun testCanDeactivateTask() {
        chrome.to(::TodoPage) {
            addTasks("A", "B", "C")
            deactivateTask("A")
            counter.shouldHave(text("1"))
            goToCompletedTab()
            taskList.shouldHave(exactText("A"))
        }
    }
}