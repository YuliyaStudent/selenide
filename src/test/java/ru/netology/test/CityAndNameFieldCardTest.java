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


public class CityAndNameFieldCardTest {
    String deliveryDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
            new Locale("ru")));

    @BeforeMethod
    public void openMethod() {
        open("http://localhost:9999/");
    }

    //общий тест на отправку формы
    @Test
    void happyPathTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    //тесты на поле "город"
    @Test
    void englishLettersInCityTest() {
        $("[data-test-id=city] input").sendKeys("Murmansk");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void numberInCityTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск 1");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void symbolInCityTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск!");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void oneEngLetterInCityTest() {
        $("[data-test-id=city] input").sendKeys("Мурмансk");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void mistakeInCityTest() {
        $("[data-test-id=city] input").sendKeys("Мурмoнск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void emptyFieldCityTest() {
        $("[data-test-id=city] input").sendKeys("");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    //тесты для поля "фамилия и имя"
    @Test
    void englishLettersInNameTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иваnов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void symbolInNameTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван @");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void numberInNameTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван 2");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void emptyFieldNameTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void haveOnlyNamePathTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Мадху");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }
}

