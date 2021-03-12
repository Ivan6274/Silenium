package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {


    private WebDriver driver;
    @BeforeAll
    static void setUpAll(){
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:7777");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestOrderCart() {


        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Алеша Попович");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79990000000");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();

        String actualMessage = driver.findElement(By.cssSelector(".paragraph_theme_alfa-on-white")).getText().trim();
        String expectedMessege = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expectedMessege,actualMessage);
    }


    @Test
    void shouldTestOrderCartInvalidName() {

        driver.get("http://localhost:7777");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Alesha Popovich");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79990000000");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector(".input__sub"));

        String actualMessage = elements.get(0).getText().trim();
        String expectedMessege = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expectedMessege,actualMessage);
    }

    @Test
    void shouldTestOrderCartInvalidPhone() {

        driver.get("http://localhost:7777");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Алеша Попович");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+799900000000000");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector(".input__sub"));

        String actualMessage = elements.get(1).getText().trim();
        String expectedMessege = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expectedMessege,actualMessage);
    }
}