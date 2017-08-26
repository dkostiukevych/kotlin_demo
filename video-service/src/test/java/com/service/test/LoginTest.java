package com.service.test;

import com.service.page.LoginPage;
import com.service.page.MainPage;
import org.testng.annotations.Test;

import static com.automation.remarks.kirk.Kirk.at;
import static com.automation.remarks.kirk.Kirk.open;
import static com.automation.remarks.kirk.conditions.Have.text;

/**
 * Created by sepi on 26.08.17.
 */
public class LoginTest {

  @Test
  public void testCanLogin() {
    open(LoginPage::new)
            .loginAs("admin", "admin");
    at(MainPage::new)
            .logo
            .shouldHave(text("Video Service"));
  }

}
