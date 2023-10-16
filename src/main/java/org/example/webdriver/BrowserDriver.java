package org.example.webdriver;

import org.example.urls.URLS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BrowserDriver {
    public static Properties properties;
    public static WebDriver  driver;
    public  static WebDriver getDriver()  {

        try {
            File file=new File("src\\main\\java\\org\\example\\global.properties");
            FileReader reader =new FileReader (file.getAbsolutePath());
            properties=new Properties();
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
      String browser = properties.getProperty("browser");
        switch (browser)
        {
            case "chrome" :
            {
               driver=new ChromeDriver();
               driver.get(URLS.kloadsshipurl);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
               return driver;
            }
            case "edge" :
            {
                driver=new EdgeDriver();
                driver.get(URLS.kloadsshipurl);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                return  driver;
            }
        }
        return null;
    }
}
