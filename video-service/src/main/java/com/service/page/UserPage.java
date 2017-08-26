package com.service.page;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.Page;

/**
 * Created by sepi on 26.08.17.
 */
public class UserPage extends Page {
  public UserPage(Browser browser) {
    super(browser);
  }

  public void addNewUser(String name, String password, String email) {
    element("#inputNewUserName").setValue(name);
    element("#inputNewUserPassword").setValue(password);
    element("#newUserEmail").setValue(email);
    element("#add_new_user_btn").click();
  }
}
