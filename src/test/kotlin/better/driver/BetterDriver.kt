package better.driver

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

fun WebDriver.open(url: String, autoClose: Boolean = true) {
    autoclose(autoClose)
    get(url)
}

fun WebDriver.autoclose(enabled: Boolean = true) {
    if (enabled) {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() = quit()
        })
    }
}

fun WebDriver.all(cssselector: String): MutableList<WebElement>? {
    return findElements(By.cssSelector(cssselector))
}

fun List<WebElement>.shouldHave(size: Int) {
    assert(this.size == size)
}

fun main(arg: Array<String>) {
    ChromeDriver().apply {
        open("http://automation-remarks.com")
        all(".post").shouldHave(size = 9)
    }

    val admin = User(name = "Dima",
            email = "dima@ukr.net",
            password = "admin123")
}

data class User(val name: String,
                val email: String,
                val password: String)

