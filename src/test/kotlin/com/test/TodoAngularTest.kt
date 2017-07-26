package com.automation.remarks.kirk.test.example.todo

import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.conditions.have
import org.testng.annotations.Test

/**
 * Created by sergey on 09.07.17.
 */
// tag::TodoAngularTest[]
class TodoAngularTest {
    @Test fun testCanAddNewTaskAndDelete() {
        open(::TodoPage) {
            addTasks("Item0")
            taskList.should(have.size(1))
            deleteTask("Item0")
            taskList.should(have.size(0))
        }
    }

    @Test fun testCanDeactivateTask() {
        open(::TodoPage) {
            addTasks("A", "B", "C")
            deactivateTask("A")
            counter.should(have.text("2"))
            goToCompletedTab()
            taskList.should(have.exactText("A"))
        }
    }
}
// end::TodoAngularTest[]