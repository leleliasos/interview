package Browser;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface BrowserSetup{

    WebDriver setUpBrowser(String browser) throws Exception;
}
