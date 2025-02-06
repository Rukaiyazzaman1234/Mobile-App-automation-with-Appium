import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalcScreen2 {
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

    @FindBy(id = "com.google.android.calculator:id/const_pi")
    WebElement btnPi;

    @FindBy(id = "com.google.android.calculator:id/op_pow")
    WebElement btnPow;
    @FindBy(id = "com.google.android.calculator:id/dec_point")
    WebElement btnDot;

    AndroidDriver driver;

    public CalcScreen2(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public double calculateExpression(String expression) {
       // btnClear.click();

        // Replace pi and handle basic formatting
        expression = expression.replace("pi", String.valueOf(Math.PI));
        expression = expression.replace("^", "**");

        // Split numbers and operators
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");

        for (String token : tokens) {
            if (token.matches("\\d+\\.?\\d*")) {
                clickDigits(token);
            } else {
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

        btnEqual.click();
        return Double.parseDouble(btnResult.getText());
    }


        private void clickDigits(String numberStr) {
        for (char digit : numberStr.toCharArray()) {
            String digitId = "com.google.android.calculator:id/digit_" + digit;
            driver.findElement(By.id(digitId)).click();
        }
    }


    private String replacePowerOperators(String expression) {
        // This would require a more complex solution to parse and replace pow
        // But for simple cases, handling just for `a^b`
        StringBuilder result = new StringBuilder();
        String[] parts = expression.split("(?<=\\d)\\^(?=\\d)"); // Split on ^ only between numbers
        for (String part : parts) {
            if (part.contains("^")) {
                String[] nums = part.split("\\^");
                double base = Double.parseDouble(nums[0].trim());
                double exponent = Double.parseDouble(nums[1].trim());
                double powerResult = Math.pow(base, exponent);
                result.append(powerResult);
            } else {
                result.append(part);
            }
        }
        return result.toString(); // Returns the string without ^ operators
    }


    // New method for calculating expressions from the CSV file
    public void calculateFromCsv(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        reader.readLine(); // Skip header
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String expression = parts[0].trim();
                double expectedResult = Double.parseDouble(parts[1].trim());
                double actualResult = calculateExpression(expression);

                // Print and assert the result
                System.out.printf("Expression: %s | Expected: %.2f | Actual: %.2f\n", expression, expectedResult, actualResult);
                assert Math.abs(actualResult - expectedResult) < 0.01 :
                        String.format("Failed for %s: Expected %.2f, but got %.2f", expression, expectedResult, actualResult);
            }
        }
        reader.close();
    }
}











