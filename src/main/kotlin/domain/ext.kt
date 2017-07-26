package domain

import com.automation.remarks.kirk.KElement
import org.openqa.selenium.interactions.Actions

/**
 * Created by sergey on 26.07.17.
 */
fun Actions.hover(element: KElement) {
    this.moveToElement(element.webElement)
}

fun Actions.click(element: KElement) {
    this.click(element.webElement)
}