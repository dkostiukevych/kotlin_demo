package piggy

import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.conditions.text
import my.piggy.pages.Login
import org.testng.annotations.Test


/**
 * Created by sergey on 06.08.17.
 */
class PiggyTest {

    @Test
    fun testCanAddIncome() {
        open(::Login)
                .loginAs("qaswdefrgt", "123456")
                .apply {
                    addIncome("Salary", 1000)
                    incomeItem.shouldHave(text("1 000 $ / PER MONTH"))
                }
    }


    @Test
    fun testCatDeleteIncome() {
        open(::Login)
                .loginAs("qaswdefrgt", "123456")
                .deleteIncome()
    }
}