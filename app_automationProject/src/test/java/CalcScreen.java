//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class CalcScreen {
//    @FindBy(id = "com.google.android.calculator:id/op_add")
//    WebElement btnPlus;
//
//    @FindBy(id = "com.google.android.calculator:id/op_sub")
//    WebElement btnMinus;
//
//    @FindBy(id = "com.google.android.calculator:id/eq")
//    WebElement btnEqual;
//
//
//    @FindBy(id = "com.google.android.calculator:id/op_div")
//    WebElement btnDiv;
//
//    @FindBy(id = "com.google.android.calculator:id/op_mul")
//    WebElement btnMul;
//
//    @FindBy(id = "com.google.android.calculator:id/result_final")
//    WebElement btnResult;
//
//    @FindBy(id = "com.google.android.calculator:id/clr")
//    WebElement btnClear;
//
//    AndroidDriver driver;
//
//    public CalcScreen(AndroidDriver driver) {
//        this.driver = driver; // for load the android driver there use this.driver
//        PageFactory.initElements(new AppiumFieldDecorator(driver), this); // AppiumFieldDecorator
//        // make elements applicable for android device
//    }
//
//    public int doSum(int num1, int num2, int num3,int num4,int num5) {
//        // Convert the numbers to strings to easily access each digit
//        String num1Str = String.valueOf(num1);
//        String num2Str = String.valueOf(num2);
//        String num3Str = String.valueOf(num3);
//         String num4Str = String.valueOf(num4);
//         String num5Str = String.valueOf(num5);
//
//        // Click each digit of the first number
//        for (char digit : num1Str.toCharArray()) {
//            driver.findElement(By.id("com.google.android.calculator:id/digit_" + digit)).click();
//        }
//
//        // Click the addition button (assuming `btnDiv` is an addition button)
//        btnDiv.click();
//
//        // Click each digit of the second number
//        for (char digit : num2Str.toCharArray()) {
//            driver.findElement(By.id("com.google.android.calculator:id/digit_" + digit)).click();
//
//        }
//        btnMul.click();
//        for (char digit : num3Str.toCharArray()) {
//            driver.findElement(By.id("com.google.android.calculator:id/digit_" + digit)).click();
//        }
//        btnMinus.click();
//        for (char digit : num4Str.toCharArray()) {
//            driver.findElement(By.id("com.google.android.calculator:id/digit_" + digit)).click();
//        }
//        btnPlus.click();
//        for (char digit : num5Str.toCharArray()) {
//            driver.findElement(By.id("com.google.android.calculator:id/digit_" + digit)).click();
//        }
//
//
//        // Click the equals button
//        btnEqual.click();
//
//        // Get the result and return it as an integer
//        return Integer.parseInt(btnResult.getText());
//    }
//
//}
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalcScreen {
    @FindBy(id = "com.google.android.calculator:id/op_add")
    WebElement btnPlus;

    @FindBy(id = "com.google.android.calculator:id/op_sub")
    WebElement btnMinus;

    @FindBy(id = "com.google.android.calculator:id/eq")
    WebElement btnEqual;

    @FindBy(id = "com.google.android.calculator:id/op_div")
    WebElement btnDiv;

    @FindBy(id = "com.google.android.calculator:id/op_mul")
    WebElement btnMul;

    @FindBy(id = "com.google.android.calculator:id/result_final")
    WebElement btnResult;

    @FindBy(id = "com.google.android.calculator:id/clr")
    WebElement btnClear;

    AndroidDriver driver;

    public CalcScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public int doSum(int num1, int num2, int num3, int num4, int num5) {
        // Convert the numbers to strings to easily access each digit
        String num1Str = String.valueOf(num1);
        String num2Str = String.valueOf(num2);
        String num3Str = String.valueOf(num3);
        String num4Str = String.valueOf(num4);
        String num5Str = String.valueOf(num5);

        // Click each digit of the first number
        clickDigits(num1Str);

        btnPlus.click(); // Click addition button
        clickDigits(num2Str);

        btnMul.click(); // Click multiplication button
        clickDigits(num3Str);

        btnMinus.click(); // Click subtraction button
        clickDigits(num4Str);

        btnPlus.click(); // Click addition button again
        clickDigits(num5Str);

        btnEqual.click(); // Click equals button

        // Get the result and return it as an integer
        return Integer.parseInt(btnResult.getText());
    }

    // New method to calculate a series expression
    public double calculateSeries(String expression) {
//        btnClear.click(); // Clear any previous calculation

        // Split the expression into numbers and operators
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");

        for (String token : tokens) {
            if (token.matches("\\d+")) { // Check if the token is a number
                clickDigits(token); // Click each digit of the number
            } else { // Handle arithmetic operators
                switch (token) {
                    case "+":
                        btnPlus.click();
                        break;
                    case "-":
                        btnMinus.click();
                        break;
                    case "*":
                        btnMul.click();
                        break;
                    case "/":
                        btnDiv.click();
                        break;
                }
            }
        }

        btnEqual.click(); // Click the equals button
        return Double.parseDouble(btnResult.getText()); // Return the result as a double
    }

    // Helper method to click digits of a number
    private void clickDigits(String numberStr) {
        for (char digit : numberStr.toCharArray()) {
            driver.findElement(By.id("com.google.android.calculator:id/digit_" + digit)).click();
        }
    }
}