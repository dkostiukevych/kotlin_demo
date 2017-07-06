package pages

import com.automation.remarks.kirk.Page
import domain.User
import io.qameta.allure.Step

/**
 * Created by sergey on 17.06.17.
 */
class LoginPage : Page() {

    override val url: String?
        get() = "http://localhost:8086"

    val username = element("#inputEmail3")
    val password = element("#inputPassword3")
    val loginBtn = element("#parent > form > div:nth-child(3) > div > button")

    @Step fun loginAs(user: User) {
        username.setVal(user.name)
        password.setVal(user.password)
        loginBtn.click()
    }
}