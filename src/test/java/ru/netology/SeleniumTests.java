package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {


    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }


    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));

        driver.get("http://localhost:7777");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestOrderCart() {


        driver.findElement(By.cssSelector("input[type='text'][name='name']")).sendKeys("Алеша Попович");
        driver.findElement(By.cssSelector("input[type='tel'][name='phone']")).sendKeys("+79990000000");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();

        String actualMessage = driver.findElement(By.cssSelector(".paragraph_theme_alfa-on-white")).getText().trim();
        String expectedMessege = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expectedMessege, actualMessage);
    }


    @Test
    void shouldTestOrderCartInvalidName() {


        driver.findElement(By.cssSelector("input[type='text'][name='name']")).sendKeys("Alesha Popovich");
        driver.findElement(By.cssSelector("input[type='tel'][name='phone']")).sendKeys("+79990000000");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();


        String actualMessage = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub")).getText().trim();
        String expectedMessege = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expectedMessege, actualMessage);
    }

    @Test
    void shouldTestOrderCartInvalidPhone() {


        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Алеша Попович");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+799900000000000");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();


        String actualMessage = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub")).getText().trim();
        String expectedMessege = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expectedMessege, actualMessage);



        }
    @Test
    void shouldTestEmptyFieldNameAndPhoneCart () {
        driver.findElement(By.cssSelector("[role=button]")).click();

        String actualMessage = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub")).getText().trim();
        String expectedMessege = "Поле обязательно для заполнения";
        assertEquals(expectedMessege, actualMessage);
    }
    @Test
    void shouldTestEmptyFieldPhoneCart () {

        driver.findElement(By.cssSelector("input[type='text'][name='name']")).sendKeys("Алеша Попович");
        driver.findElement(By.cssSelector("[role=button]")).click();

        String actualMessage = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub")).getText().trim();
        String expectedMessege = "Поле обязательно для заполнения";
        assertEquals(expectedMessege, actualMessage);
    }
    @Test
    void shouldTestEmptyCheckboxCart () {

        driver.findElement(By.cssSelector("input[type='text'][name='name']")).sendKeys("Алеша Попович");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+799900000000000");
        driver.findElement(By.cssSelector("[role=button]")).click();

        String actualMessage = driver.findElement(By.cssSelector(".checkbox__text")).getText().trim();
        String expectedMessege = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        assertEquals(expectedMessege, actualMessage);
    }
}