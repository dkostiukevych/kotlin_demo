package demo.tests

import com.codeborne.selenide.CollectionCondition.size
import com.codeborne.selenide.Configuration.browser
import com.codeborne.selenide.Selenide
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.assertj.core.api.SoftAssertions
import org.awaitility.Awaitility.await
import org.hamcrest.CoreMatchers.equalTo
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import selenide.Selenide.Companion.ss
import test.User

/**
 * Created by sergey on 07.06.17.
 */
class FirstTest {

    @BeforeClass fun setUp() {
        ChromeDriverManager.getInstance().setup()
        browser = "chrome"
    }

    @Test fun testDemo() {
        val user = User("Sergey", 27)

        SoftAssertions.assertSoftly {
            it.assertThat(user.age).isEqualTo(27)
            it.assertThat(user.name).isEqualTo("Sergey")
        }

        await().until({ 2 }, equalTo(3))
    }

    @Test fun testDemo2() {
        Selenide.open("http://automation-remarks.com")
        ss(".post").shouldHave(size(9))
    }
}


