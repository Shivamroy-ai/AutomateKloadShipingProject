package org.example.testes;

import org.example.addpackage.AddNewPackage;
import org.example.deleatpackage.DeleatePackage;
import org.example.webdriver.BrowserDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class DeleatPackageTest {

    @BeforeTest
    public void login()
    {
        AddNewPackage.login();
    }
    @Test(testName = "gotoPackage")
    public void gotoPackage()
    {
        AddNewPackage.gotoPackage();
    }
    @Test(testName = "deleatpackageTest")
    public void deleatpackageTest()
    {
        DeleatePackage.deleatePackagefun();
    }

    @Test(dependsOnMethods = "deleatpackageTest")
    public void logoutTestCase()
    {
        AddNewPackage.logoutLocater();
    }


}
