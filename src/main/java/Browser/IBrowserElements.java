package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IBrowserElements implements  BrowserElements {

    public void gotoPage(String httpPage, WebDriver driver) {
        driver.get(httpPage);
    }

    public WebElement waitAndFetchElement(WebDriver driver, By elementXpath) {
       WebElement element = driver.findElement(elementXpath);
        return element;
    }

    public void type(WebElement element, String typeSometing){
        element.sendKeys(typeSometing);
    }


}
