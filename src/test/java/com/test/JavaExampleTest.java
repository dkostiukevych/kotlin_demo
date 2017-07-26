package com.test;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.KElement;
import com.automation.remarks.kirk.Kirk;
import com.automation.remarks.kirk.Page;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import static com.automation.remarks.kirk.Kirk.at;
import static com.automation.remarks.kirk.conditions.ContainKt.contain;
import static com.automation.remarks.kirk.conditions.HaveKt.have;

/**
 * Created by sergey on 16.07.17.
 */
public class JavaExampleTest {

  @Test
  public void canLogin() {
    Kirk.open(LoginPageJ::new)
        .login("admin", "admin");
    MainPageJ mainPage = at(MainPageJ::new);
    mainPage.logo.should(have.text("Video service"));
    mainPage.uploadVideo("/home/sergey/Github/kotlin-demo/src/test/resources/shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi");
    mainPage.all("[data-parent='#accordion'] strong").should(contain.elementWithText("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi"));
  }
}

class LoginPageJ extends Page {

  public LoginPageJ(Browser browser) {
    super(browser);
  }

  @Nullable
  @Override
  public String getUrl() {
    return "/";
  }

  public void login(String name, String password) {
    element("#inputEmail3").setValue(name);
    element("#inputPassword3").setValue(password);
    element("#parent > form > div:nth-child(3) > div > button").click();
  }
}

class MainPageJ extends Page {

  public KElement logo = element("a.navbar-brand");

  public MainPageJ(Browser browser) {
    super(browser);
  }

  public void uploadVideo(String path) {
    element("#filestyle-0").setValue(path);
    element("#upload_submit > button").click();
  }
}