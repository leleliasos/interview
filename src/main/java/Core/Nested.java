package Core;

import java.util.ArrayList;
import java.util.List;

public class Nested {

    public void haveAllTheExpectedAppsLoaded() {
        List<String> allWindowTitles = new ArrayList<>();
        int maxAttempts = 3;
        boolean haveAllTheAppsLoaded;
        int expectedNumberOfOpenApps = 4;

        while (allWindowTitles.size() < expectedNumberOfOpenApps) {
            log.info("Current apps open {}, expected {}", allWindowTitles.size(), expectedNumberOfOpenApps);
            allWindowTitles = getAllOpenWindowTitles();
            if (allWindowTitles.size() == expectedNumberOfOpenApps) {
                haveAllTheAppsLoaded = true;
                break;
            }
            maxAttempts--;
            if (maxAttempts == 0) {
                haveAllTheAppsLoaded = false;
                break;
            }
            driver.wait(1sec);
        }

        assert (haveAllTheAppsLoaded ,true);
    }

}
