package com.todo.test

import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.conditions.exactText
import com.automation.remarks.kirk.conditions.size
import com.automation.remarks.kirk.conditions.text
import com.todo.page.TodoPage
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test

/**
 * Created by sepi on 26.08.17.
 */
class ToDoTest {

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
            counter shouldHaveValue 2
            goToCompletedTab()
            taskList.shouldHave(exactText("A"))
        }
    }
}

infix fun KElement.shouldHaveValue(value:Any){
    this.shouldHave(com.automation.remarks.kirk.conditions.text(value.toString()))
}