package pages

import com.codeborne.selenide.Selenide
import io.qameta.allure.Step
import selenide.Selenide.Companion.s
import test.User

/**
 * Created by sergey on 17.06.17.
 */
class LoginPage : Page() {

    val username = s("#inputEmail3")
    val password = s("#inputPassword3")
    val loginBtn = s("#parent > form > div:nth-child(3) > div > button")

    @Step fun open(): LoginPage {
        Selenide.open("/")
        return this
    }

    @Step fun loginAs(user: User): MainPage {
        username.value = user.name
        password.value = user.password
        loginBtn.click()
        return MainPage()
    }
}