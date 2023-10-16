package org.example.addpackage;

import org.example.constant.ConstantData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddNewPackage {

    private static WebDriver driver;
    private static WebDriverWait driverWait;

    public static void login() {
        driver = ConstantData.driver;
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(ConstantData.username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConstantData.password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();


    }

    public static void addNewPackage() {
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));


        gotoPackage();
        driverWait.until(ExpectedConditions.titleContains("KloudShip - eCommerce & Shipping Solutions"));
        driver.findElement(By.xpath("(//mat-icon[text()='add'])[position()=2]")).click();
        driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='New  Package']"))));

        // Input fields
        WebElement nameInput = driver.findElement(By.xpath("//input[@id='mat-input-1']"));
        WebElement lengthInput = driver.findElement(By.xpath("//input[@id='mat-input-2']"));
        WebElement widthInput = driver.findElement(By.xpath("//input[@id='mat-input-3']"));
        WebElement heightInput = driver.findElement(By.xpath("//input[@id='mat-input-4']"));

        nameInput.sendKeys(ConstantData.nameInput);
        lengthInput.sendKeys(ConstantData.lengthInput);
        widthInput.sendKeys(ConstantData.widthInput);
        heightInput.sendKeys(ConstantData.heightInput);

        carrierSelection(ConstantData.carriertype);
        selectType(ConstantData.selecttype);
        selectStatus();
        WebElement locationDropdown = driver.findElement(By.xpath("//div[@id='mat-select-value-9']/span"));
        selectLocation(locationDropdown, By.xpath("//span[@class='mat-option-text']"));
        WebElement checkIcon = driver.findElement(By.xpath("//mat-icon[text()='check']"));
        createNewPackage(checkIcon);

    }

    public static void gotoPackage() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-Card[contains(@class,'mat-card')]//span[text()='Packages']")));
            driver.findElement(By.xpath("//mat-Card[contains(@class,'mat-card')]//span[text()='Packages']")).click();
        }
        catch (Exception e){e.getMessage();}

    }

    private static void carrierSelection(String carrer) {
        WebElement carrierDropdown = driver.findElement(By.xpath("//div[@id='mat-select-value-5']"));
        carrierDropdown.click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option[@role='option']")));
        List<WebElement> carrierList = driver.findElements(By.xpath("//mat-option[@role='option']"));

        for (WebElement carrierItem : carrierList) {
            if (carrierItem.getText().contains(carrer)) {
                carrierItem.click();
                break;
            }
        }
    }

    private static void selectType(String select) {
        WebElement typeDropdown = driver.findElement(By.xpath("//div[@id='mat-select-value-7']"));
        typeDropdown.click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));
        List<WebElement> typeList = driver.findElements(By.xpath("//mat-option[@role='option']"));

        for (WebElement listItem : typeList) {
            try {
                if (listItem.getText().contains(select)) {
                    listItem.click();
                    break;
                }
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }
    }

    private static void selectStatus() {
        WebElement statusCheckbox = driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-1']"));
        System.out.println(statusCheckbox.isSelected()+"Is Select method ");
        if (statusCheckbox.isSelected()) {
            statusCheckbox.click();
        }
    }

    private static void selectLocation(WebElement dropdownElement, By optionLocator) {
        dropdownElement.click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        WebElement optionElement = driver.findElement(optionLocator);
        optionElement.click();
    }

    public static void logoutLocater() {
            logout();
    }

    private static void logout() {

        WebElement logIcon = driver.findElement(By.xpath("//mat-icon[contains(text(),'more_vert')]"));
        logIcon.click();
        By elt=By.xpath("//div[@class='mat-menu-content ng-tns-c95-1']");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(elt));
        WebElement logoutButton = driver.findElement(By.xpath("//button[text()=' Logout ']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeAsyncScript("arguments[0].click();", logoutButton);
        }
        catch (Exception e){e.getMessage();}

    }

    private static void createNewPackage(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
}
