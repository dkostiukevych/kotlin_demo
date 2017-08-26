package my.piggy.pages

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.clickable

/**
 * Created by sepi on 26.08.17.
 */

class MainPage(browser: Browser) : Page(browser) {

    val incomeItem = element(".title9museo300")

    fun addIncome(name: String, value: Int) {
        element("#incomesplusitem").waitUntil(clickable).click()
        element("#add-modal [name='modalvalue']").setValue(value.toString())
        element(".modaltitle").setValue(name).pressEnter()
    }

    fun deleteIncome() {
        element("#incomeslider").click()
        element(".modal-delete").click()
    }
}