package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface BrowserElements {

    void gotoPage(String httpPage, WebDriver driver);
    WebElement waitAndFetchElement (WebDriver driver, By elementName);
    void type(WebElement element, String typeSometing);
}
