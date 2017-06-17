package pages

import selenide.Selenide.Companion.s

/**
 * Created by sergey on 17.06.17.
 */
class MainPage : Page() {
    val logo = s("a.navbar-brand")
    val uploadInput = s("#filestyle-0")
    val uploadBtn = s("#upload_submit > button")
}