package com.automation.remarks.kirk.test.example.todo

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.KElementCollection
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.ext.s
import com.automation.remarks.kirk.ext.ss
import domain.hover
import io.qameta.allure.Step

/**
 * Created by sergey on 09.07.17.
 */
// tag::TodoPage[]
class TodoPage(browser: Browser) : Page(browser) {
    override val url = "http://todomvc.com/examples/angularjs/"

    val counter
        @Step
        get() = s("#todo-count strong")
    val taskList = ss("label.ng-binding")

    val tasks = ss("#todo-list li label")

    @Step
    fun addTasks(vararg tasks: String) {
        for (task in tasks) {
            s("#new-todo")
                    .setValue(task)
                    .pressEnter()
        }
    }

    @Step
    fun deleteTask(name: String) {
        browser.interact { hover(s("#todo-list li div input")) }
        s("//label[text()='$name']/following-sibling::button")
                .click()
    }

    @Step
    fun deactivateTask(vararg tasks: String) {
        for (task in tasks) {
            s("//label[text()='$task']/preceding-sibling::input").click()
        }
    }

    @Step
    fun goToCompletedTab() {
        s("#filters li:nth-child(3) a").click()
    }
}
// end::TodoPage[]

fun Page.s(locator: String): KElement {
    return browser.s(locator)
}

fun Page.ss(locator: String): KElementCollection {
    return browser.ss(locator)
}