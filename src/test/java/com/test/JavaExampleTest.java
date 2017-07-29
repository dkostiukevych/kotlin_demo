package com.test;

import com.automation.remarks.kirk.*;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.automation.remarks.kirk.Kirk.at;
import static com.automation.remarks.kirk.conditions.Have.elementWithText;
import static com.automation.remarks.kirk.conditions.Have.text;

/**
 * Created by sergey on 16.07.17.
 */
public class JavaExampleTest {

  @Test
  public void canLogin() {
    Kirk.open(LoginPageJ::new)
        .login("admin", "admin");
    MainPageJ mainPage = at(MainPageJ::new);
    mainPage.logo.shouldHave(text("Video service"));
    mainPage.uploadVideo("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi");
    mainPage.all("[data-parent='#accordion'] strong").shouldHave(elementWithText("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi"));
  }
}

class LoginPageJ extends JavaPage {

  public LoginPageJ(Browser browser) {
    super(browser);
  }

  @Nullable
  @Override
  public String getUrl() {
    return "/";
  }

  public void login(String name, String password) {
    $("#inputEmail3").setValue(name).pressEnter();
    $("#inputPassword3").setValue(password);
    $("#parent > form > div:nth-child(3) > div > button").click();
  }
}

class JavaPage extends Page {
  public JavaPage(Browser browser) {
    super(browser);
  }

  public KElement $(String cssLocator) {
    return element(cssLocator);
  }

  public KElement $x(String xpath) {
    return element(By.xpath(xpath));
  }

  public KElementCollection $$(String cssLocator) {
    return all(cssLocator);
  }

  public KElementCollection $$x(String xpath) {
    return all(By.xpath(xpath));
  }
}

class MainPageJ extends JavaPage {

  public KElement logo = element("a.navbar-brand");

  public MainPageJ(Browser browser) {
    super(browser);
  }

  public void uploadVideo(String path) {
    $("#filestyle-0").setValue(path);
    $("#upload_submit > button").click();
  }
}