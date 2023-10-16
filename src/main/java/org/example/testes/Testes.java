package org.example.testes;

import org.example.addpackage.AddNewPackage;
import org.example.constant.ConstantData;
import org.example.webdriver.BrowserDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Hello world!
 *
 */
public class Testes
{
   @BeforeSuite
   public void openBrowser()
   {
     ConstantData.driver= BrowserDriver.getDriver();
   }
   @Test
   public void loginTestcase()
   {
      AddNewPackage.login();
   }
   @Test(dependsOnMethods = "loginTestcase")
   public void addPackageTestcase()
   {
      AddNewPackage.addNewPackage();
   }
   @Test(dependsOnMethods = "addPackageTestcase")
   public void logoutTestCase()
   {
      AddNewPackage.logoutLocater();
   }
}
