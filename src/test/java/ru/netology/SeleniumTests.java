package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {


    private WebDriver driver;
    @BeforeAll
    static void setUpAll(){
        System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
    }


    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestOrderCart() {

        driver.get("http://localhost:7777");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Алеша Попович");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79990000000");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();

        String actualMessage = driver.findElement(By.cssSelector(".paragraph_theme_alfa-on-white")).getText().trim();
        String expectedMessege = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expectedMessege,actualMessage);
    }
}
