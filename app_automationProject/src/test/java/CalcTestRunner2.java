import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class CalcTestRunner2 extends Setup {
    @Test(priority = 3)
    public void calculateFromCsv() throws IOException {
        CalcScreen2 calcScreen2 = new CalcScreen2(driver);
        String filePath = System.getProperty("user.dir") + "/src/test/resources/data.csv"; // Absolute path to CSV
        calcScreen2.calculateFromCsv(filePath);
    }

    @AfterMethod
    public void clear() {
        CalcScreen2 calcScreen2 = new CalcScreen2(driver);
        calcScreen2.btnClear.click(); // Clear calculator after each test method
    }
}


