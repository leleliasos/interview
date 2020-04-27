package Core;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class AfterClassAlwaysTrue {

    @Test (priority = 1)
    public void thisShouldExecuteFirst(){
        System.out.println("This Should execute first");

    }

    @Test (priority = 2)
    public void thisShouldExecuteSecond(){
        System.out.println("This Should execute second");
        boolean blackIsNotWhite = true;
        Assert.assertFalse(blackIsNotWhite);
    }

    @Test (priority = 3, dependsOnMethods = "thisShouldExecuteSecond" )
    public void thisShouldExecuteThird(){
        System.out.println("This Should execute thrird");

    }

   @AfterClass(alwaysRun = true)
    public void thisShouldAlwaysExecute() {
        System.out.println("This Should always execute");
    }

}




















