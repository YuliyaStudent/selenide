package ru.netology.test;

import org.testng.annotations.Test;
import org.testng.annotations.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static org.openqa.selenium.Keys.DELETE;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;


public class CardTest {
    String deliveryDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
            new Locale("ru")));

    @BeforeMethod
    public void openMethod() {
        open("http://localhost:9999/");
    }

//общий тест на отправку формы
    @Test
    void happyPathTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }
    //тесты на поле "город"
    @Test
    void englishLettersInCityTest(){
        $("[data-test-id=city] input").sendKeys("Murmansk");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }
    @Test
    void numberInCityTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск 1");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }
    @Test
    void symbolInCityTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск!");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }
    //тесты для поля "фамилия и имя"
    @Test
    void englishLettersInNameTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иваnов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
    @Test
    void symbolInNameTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван @");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void numberInNameTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван 2");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
    //тесты для поля "мобильный телефон"
    @Test
    void missPlusInMobileNumberTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void tenNumbersInMobileNumberTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7909999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void twelveNumbersInMobileNumberTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE,deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+779099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //тест для поля "дата"
    @Test
    void pastDateTest(){
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, "20.10.2000");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=date] .input__sub").should(exactText("Заказ на выбранную дату невозможен"));
    }
}

