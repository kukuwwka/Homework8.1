package Test;

import Main.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@Slf4j
public class BaseTest {
    WebDriver webDriver;
    MainPage mainPage;

    @Test
    public void checkboxes() throws InterruptedException {
        webDriver = new ChromeDriver();
        webDriver.get("http://the-internet.herokuapp.com/checkboxes");

        WebElement checkInput = webDriver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        checkInput.click();
    }

    @Test
    public void positiveFormAuthentication() throws InterruptedException {
        webDriver = new ChromeDriver();
        webDriver.get("http://the-internet.herokuapp.com/login");

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));

        login.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword!");

        WebElement button = webDriver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        button.click();

        String succes = webDriver.findElement(By.xpath("//*[@id=\"flash\"]")).getText();
        System.out.println("Успешная авторизация! " + succes);


    }

    @Test
    public void negativeFormAuthentication() throws InterruptedException {
        webDriver = new ChromeDriver();
        webDriver.get("http://the-internet.herokuapp.com/login");

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));

        login.sendKeys("tf3weg");
        password.sendKeys("1221!");

        WebElement button = webDriver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        button.click();

        String mistake = webDriver.findElement(By.xpath("//*[@id=\"flash\"]")).getText();
        System.out.println("Неверный логин! " + mistake);
    }

    @Test
    public void hovers() throws InterruptedException {
        webDriver = new ChromeDriver();
        webDriver.get("http://the-internet.herokuapp.com/hovers");

        WebElement clickable = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
        new Actions(webDriver)
                .moveToElement(clickable)
                .perform();
    }

    @Test
    public void dynamicallyLoadedPageElements() throws InterruptedException {
        webDriver = new ChromeDriver();
        webDriver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement button = webDriver.findElement(By.xpath("//*[@id=\"start\"]/button"));
        button.click();

        String feedMessage = webDriver.findElement(By.xpath("//*[@id=\"finish\"]/h4")).getText();
        System.out.println("Дождались: " + feedMessage);
    }

    @Test
    public void keyPresses() throws InterruptedException {
        webDriver = new ChromeDriver();
        webDriver.get("http://the-internet.herokuapp.com/key_presses");

        WebElement enterText = webDriver.findElement(By.xpath("//*[@id=\"target\"]"));

        WebElement button = webDriver.findElement(By.xpath("//*[@id=\"target\"]"));
        button.click();

        new Actions(webDriver)
                .sendKeys("e")
                .perform();
    }

    @Test
    public void addRemoveElements() throws InterruptedException {
        webDriver = new ChromeDriver();
        webDriver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        for (int i = 0; i < 10; i++) {
            webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
        }
    }
}
