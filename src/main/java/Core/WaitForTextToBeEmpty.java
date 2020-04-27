package Core;

public class WaitForTextToBeEmpty {


    public void waitForElementTextToBeEmpty(WebElement element) {
        String text;
        try {
            text = element.getText();
            int maxRetries = 3;
            int retry = 0;
            while ((text.length() >= 1) || (retry < maxRetries)) {
                retry++;
                element.getText();
            }
        } catch (Exception e) {
            LOG.error("The element " + element.toString() + " text was not cleared");
        }
    }

}
