package org.example.deleatpackage;

import com.beust.ah.A;
import org.example.addpackage.AddNewPackage;
import org.example.constant.ConstantData;
import org.example.webdriver.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DeleatePackage {

    public static void deleatePackagefun()
    {
        AddNewPackage.gotoPackage();
        List<WebElement> packagebord  = BrowserDriver.driver.findElements(By.xpath("//mat-card"));
        WebDriverWait webDriverWait=new WebDriverWait(BrowserDriver.driver, Duration.ofSeconds(10));

        for (int i=1;i<=packagebord.size();i++ )
        {
            WebElement item=packagebord.get(i);

            Actions actions = new Actions(BrowserDriver.driver);
            By packg=By.xpath("(//div[@class='padding list-text'])["+i+"]");
            WebElement packageitem=null;
            try
            {
                packageitem=item.findElement(packg);
                packageitem.click();
            }catch (Exception e){e.getMessage();}


            if (packageitem!=null&&packageitem.getText().contains(ConstantData.nameInput))
            {
                try {
                    actions.moveToElement(item).perform();
                    WebElement deleatitem=  item.findElement(By.xpath("(//mat-icon[text()='delete'])["+i+"]"));
                    if (deleatitem.isDisplayed())
                    {
                        deleatitem.click();
                        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //mat-card[@class='mat-card mat-focus-indicator']/div")));
                        BrowserDriver.driver.findElement(By.xpath("//button/span[text()=' Delete Package Type']")).click();
                        break;
                    }
                }catch (Exception e){e.getMessage();}
            }
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }

    }
}
