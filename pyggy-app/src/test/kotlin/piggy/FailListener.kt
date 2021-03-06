package piggy

import com.automation.remarks.kirk.AbstractKirkEventListener
import com.automation.remarks.kirk.core.getLatestScreenshot
import com.google.common.io.Files
import io.qameta.allure.Attachment
import java.io.IOException

/**
 * Created by sepi on 26.08.17.
 */
class FailListener : AbstractKirkEventListener() {
    override fun onFail(exception: Exception) {
        attachScreenshot()
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private fun attachScreenshot(): ByteArray {
        return try {
            Files.toByteArray(getLatestScreenshot())
        } catch (e: IOException) {
            ByteArray(0)
        }
    }
}