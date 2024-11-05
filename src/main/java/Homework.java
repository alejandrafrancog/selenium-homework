import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class Homework {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.applitools.com/");

        System.out.println(driver.getTitle().toUpperCase()+
                "\n-------------------------------" +
                "\n");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            WebElement userNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]")));
            userNameInput.sendKeys("admin");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")));
        passwordInput.sendKeys("password123");

        WebElement loginButton = driver.findElement(By.id("log-in"));
        loginButton.click();

        WebElement totalBalance = driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div[2]/span[1]"));
        String balance = totalBalance.getText();
        System.out.print("Total balance: "+balance+"\n");

        WebElement creditAvailable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > div.layout-w > div.content-w > div > div > div.element-wrapper.compact.pt-4 > div.element-box-tp > div > div > div > div:nth-child(2) > div.balance-value")));
        String credit = creditAvailable.getText();
        System.out.print("Credit Available: "+credit+"\n");
        System.out.print("\n------------------------------- \n");
        WebElement tabla = driver.findElement(By.tagName("table"));

        ArrayList<WebElement> rows = (ArrayList<WebElement>) tabla.findElements(By.tagName("tr"));

        ArrayList<WebElement> headers = (ArrayList<WebElement>) rows.get(0).findElements(By.tagName("th"));
        int indexDescription = -1;
        int indexStatus = -1;
        int indexAmount = -1;

        for (int i = 0; i < headers.size(); i++) {
            String headerText = headers .get(i).getText();
            if (headerText.equalsIgnoreCase("Description")) {
                indexDescription = i;
            } else if (headerText.equalsIgnoreCase("Status")) {
                indexStatus = i;
            } else if (headerText.equalsIgnoreCase("Amount")) {
                indexAmount = i;
            }
        }

        for (int i = 1; i < rows.size(); i++) {
            ArrayList<WebElement> cells = (ArrayList<WebElement>) rows.get(i).findElements(By.tagName("td"));

            String description = cells.get(indexDescription).getText();
            String status = cells.get(indexStatus).getText();
            String amount = cells.get(indexAmount).getText();

            System.out.println("Description: " +description + "\nStatus: " + status + "\nAmount: " + amount);
            System.out.println("-----------------------------------------------------------------------------");
        }

        driver.close();

    }

}
