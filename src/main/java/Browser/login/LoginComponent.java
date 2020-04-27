package Browser.login;

import Browser.IBrowserElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginComponent {

    public LoginComponent(WebDriver driver){
        this.localDriver=driver;
        System.out.println("This is from LoginComp. localDriver is..:"+localDriver);
    }

    IBrowserElements browserElements  = new IBrowserElements();
    private static final By GOOGLES_SEARCH_BOX = By.xpath("//input[@name='q']");
    private WebDriver localDriver;

    public void getGooglesBoxAndTypeSomething() throws InterruptedException {
        Thread.sleep(3000);
        WebElement element = browserElements.waitAndFetchElement(this.localDriver, GOOGLES_SEARCH_BOX);
        element.click();
        browserElements.type(element, "AEK IS THE BEST!");
        Thread.sleep(3000);
    }
}
