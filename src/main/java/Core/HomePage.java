package Core;


import org.openqa.selenium.WebDriver;

public class HomePage extends CommonUtils {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.get(Urls.BASE_URL.getUrl());

        if (!(driver.getTitle()).equals("Low prices at Amazon on digital cameras, MP3, sports, books, music, DVDs, video games, home & garden")) {
            throw new IllegalStateException("This is not the home page!");
        }
    }


}
