package com.todo.page

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.core.byXpath
import com.automation.remarks.kirk.ext.hover
import io.qameta.allure.Step

/**
 * Created by sergey on 09.07.17.
 */
// tag::TodoPage[]
class TodoPage(browser: Browser) : Page(browser) {
    override val url = "/"

    val counter = element("#todo-count strong")
    val taskList = all("label.ng-binding")

    @Step
    fun addTasks(vararg tasks: String) {
        for (task in tasks) {
            element("#new-todo")
                    .setValue(task)
                    .pressEnter()
        }
    }

    @Step
    fun deleteTask(name: String) {
        browser.interact { hover(element("#todo-list li div input")) }
        element(byXpath("//label[text()='$name']/following-sibling::button"))
                .click()
    }

    @Step
    fun deactivateTask(vararg tasks: String) {
        for (task in tasks) {
            element(byXpath("//label[text()='$task']/preceding-sibling::input")).click()
        }
    }

    @Step
    fun goToCompletedTab() {
        element("#filters li:nth-child(3) a").click()
    }
}
// end::TodoPage[]