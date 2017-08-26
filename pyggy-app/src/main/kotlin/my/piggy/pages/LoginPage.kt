package my.piggy.pages

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page

/**
 * Created by sepi on 26.08.17.
 */
class Login(browser: Browser) : Page(browser) {

    override val url = "http://my-piggymetrics.rhcloud.com/"

    fun loginAs(name: String, pass: String): MainPage {
        element("#frontloginform").setValue(name).pressEnter()
        element("#frontpasswordform").setValue(pass).pressEnter()
        element("#plusborder").click()
        return page(::MainPage)
    }
}