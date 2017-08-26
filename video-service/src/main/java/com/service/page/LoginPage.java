package com.service.page;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.Page;

/**
 * Created by sepi on 26.08.17.
 */
public class LoginPage extends Page {
  public LoginPage(Browser browser) {
    super(browser);
  }

  @Override
  public String getUrl() {
    return "/";
  }

  public void loginAs(String name, String password) {
    element("#inputEmail3").setValue(name).pressEnter();
    element("#inputPassword3").setValue(password);
    element("#parent > form > div:nth-child(3) > div > button").click();
  }
}
