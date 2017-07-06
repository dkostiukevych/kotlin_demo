package pages

import com.automation.remarks.kirk.Page

/**
 * Created by sergey on 17.06.17.
 */
class MainPage : Page() {
    val logo = element("a.navbar-brand")
    val uploadInput = element("#filestyle-0")
    val uploadBtn = element("#upload_submit > button")
}