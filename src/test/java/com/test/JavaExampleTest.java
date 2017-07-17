package com.test;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.KElement;
import com.automation.remarks.kirk.Page;
import com.automation.remarks.kirk.conditions.CollectionContainText;
import com.automation.remarks.kirk.conditions.Have;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

/**
 * Created by sergey on 16.07.17.
 */
public class JavaExampleTest {

//  @Before
//  public void setUp() throws Exception {
//    ChromeDriverManager.getInstance().setup();
//  }

  @Test
  public void canLogin() {
    Browser chrome = new Browser();
    chrome
        .to(LoginPageJ::new)
        .login("admin", "admin");
    MainPageJ mainPage = chrome
        .at(MainPageJ::new);

    mainPage.logo.should(new Have().text("Video service"));
    mainPage.uploadVideo("/home/sergey/Github/kotlin-demo/src/test/resources/shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi");
    mainPage.all("[data-parent='#accordion'] strong").should(new CollectionContainText("shouldBeCustomFolderForVideo_recording_2017_09_01_19_37_10.avi"));
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
  public MainPageJ(Browser browser) {
    super(browser);
  }

  public KElement logo = element("a.navbar-brand");

  public void uploadVideo(String path){
    element("#filestyle-0").sendKeys(path);
    element("#upload_submit > button").click();
  }
}