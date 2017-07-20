package com.test;

import com.pages.ExtKt;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Created by sergey on 18.07.17.
 */
public class ExtDriver {

  @Test
  public void testName() throws Exception {
    WebDriver driver = new ChromeDriver();
    ExtKt.open(driver, "", true);
    ExtKt.find(driver,"");
  }
}
