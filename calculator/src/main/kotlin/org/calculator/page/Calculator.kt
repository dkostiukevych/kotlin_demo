package org.calculator.page

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page

/**
 * Created by sepi on 26.08.17.
 */
class Calculator(browser: Browser) : Page(browser) {

    override val url = "/"

    val first = element("input[ng-model='first']")
    val second = element("input[ng-model='second']")
    val goBtn = element("#gobutton")
    val result = element("h2.ng-binding")

    val select = select("select[ng-model='operator']")
}