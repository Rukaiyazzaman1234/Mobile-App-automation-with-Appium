//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.Test;
//
//public class CalcTestRunner extends Setup {
//    @Test(priority = 1)
//    public void doSum(){
//        CalcScreen calcScreen=new CalcScreen(driver);
//        int actualRes= calcScreen.doSum(100,10,5,10,60);
//        System.out.println(actualRes);
//        int expectedResult=100;
//        Assert.assertEquals(actualRes,expectedResult);
//    }
//
//     @Test(priority = 2)
//    @AfterMethod
//    public void clear(){
//        CalcScreen calcScreen=new CalcScreen(driver);
//        calcScreen.btnClear.click();
//    }
//}
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CalcTestRunner extends Setup {
   // @Test(priority = 1)
    public void doSum() {
        CalcScreen calcScreen = new CalcScreen(driver);
        int actualRes = calcScreen.doSum(100, 10, 5, 10, 60);
        System.out.println(actualRes);
        int expectedResult = 100; // Adjust this as needed based on your logic
        Assert.assertEquals(actualRes, expectedResult);
    }

    @Test(priority = 2)
    public void doSeries() {
        CalcScreen calcScreen = new CalcScreen(driver);
        String expression = "100/10*5-10+60"; // Example series
        double actualRes = calcScreen.calculateSeries(expression);
        System.out.println(actualRes);

        double expectedResult = 100.0; // Replace with the actual expected result after manual calculation
        Assert.assertEquals(actualRes, expectedResult, 0.001); // Use delta for comparison
    }

    @AfterMethod
    public void clear() {
        CalcScreen calcScreen = new CalcScreen(driver);
        calcScreen.btnClear.click(); // Clear the calculator after each test
    }
}