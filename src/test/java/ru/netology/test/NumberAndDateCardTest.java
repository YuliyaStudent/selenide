package ru.netology.test;

import org.testng.annotations.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.DELETE;


public class NumberAndDateCardTest {
    String deliveryDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
            new Locale("ru")));

    @BeforeMethod
    public void openMethod() {
        open("http://localhost:9999/");
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

    @Test
    void ruLetterInMobilePhoneTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7и099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void engLetterInMobilePhoneTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7r099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void emptyPhoneTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void symbolInMobilePhoneTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7@099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //тесты для поля "дата"
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
    @Test
    void symbolInDateTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, "20.10.202$");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=date] .input__sub").should(exactText("Неверно введена дата"));
    }

    @Test
    void letterInDateTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, "20.10.202r");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=date] .input__sub").should(exactText("Неверно введена дата"));
    }

    //тест для поля "дата" который не "упал" (хотя по логике должен был)
    @Test
    void featureDateTest() {
        String deliveryDate = LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
                new Locale("ru")));
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=date] .input__sub").should(exactText("Заказ на выбранную дату невозможен"));
    }

    // тест для чек-бокса
    @Test
    void noAgreementTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("button.button").click();
        $(".input_invalid[data-test-id=agreement]").should(exist);

    }


}
