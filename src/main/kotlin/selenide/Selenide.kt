package selenide

import com.codeborne.selenide.ElementsCollection

/**
 * Created by sergey on 07.06.17.
 */
class Selenide {

    companion object {
        fun ss(locator: String): ElementsCollection {
            return com.codeborne.selenide.Selenide.`$$`(locator)
        }
    }
}