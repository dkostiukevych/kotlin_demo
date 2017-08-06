package piggy

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.conditions.text
import org.testng.annotations.Test

/**
 * Created by sergey on 06.08.17.
 */
class PiggyTest {

    @Test
    fun testCanAddIncome() {
        drive {
            to("http://my-piggymetrics.rhcloud.com/")
            element("#frontloginform").setValue("qaswdefrgt").pressEnter()
            element("#frontpasswordform").setValue("123456").pressEnter()
            element("#lefttitle").shouldHave(text("QASWDEFRGT METRICS"))
            element("#plusborder").click()
            element("#noincomes .majorplusitem").click()
            element("#add-modal [name='modalvalue']").setValue("1000")
            element(".modaltitle").setValue("Salary").pressEnter()
            element(".title9museo300").shouldHave(text("1 000 $ / PER MONTH"))
        }
    }
}