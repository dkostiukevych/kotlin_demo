package com.service.page;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.KElement;
import com.automation.remarks.kirk.Page;

/**
 * Created by sepi on 26.08.17.
 */
public class MainPage extends Page {

  public KElement logo = element("a.navbar-brand");

  public MainPage(Browser browser) {
    super(browser);
  }

  public void uploadVideo(String path) {
    element("#filestyle-0").setValue(path);
    element("#upload_submit > button").click();
  }
}
