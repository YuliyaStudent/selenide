package ru.netology.test;

import org.testng.annotations.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.DELETE;


public class NotSuccessfulTest {
    String deliveryDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
            new Locale("ru")));

    @BeforeMethod
    public void openMethod() {
        open("http://localhost:9999/");
    }

    //тесты на поле "город", которые должны упасть

    @Test
    void englishLettersInCityTest() {
        $("[data-test-id=city] input").sendKeys("Мурмансk");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    @Test
    void numberInCityTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск 2");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    @Test
    void mistakeInCityTest() {
        $("[data-test-id=city] input").sendKeys("Мурмoнск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    @Test
    void symbolInCityTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск!");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }
//тест на слитное  написание имени и фамилии не должен упасть?
    @Test
    void nameAndSurnameWithoutSpaceTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Ивановиван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    //тесты для поля "мобильный телефон"
    @Test
    void ruLetterInMobilePhoneTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7и099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    @Test
    void engLetterInMobilePhoneTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7r099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    //тесты для поля "дата"
    @Test
    void symbolInDateTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, "20.10.202$");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    @Test
    void letterInDateTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, "20.10.202r");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
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
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));
    }

    // тест для чек-бокса
    @Test
    void noAgreementTest() {
        $("[data-test-id=city] input").sendKeys("Мурманск");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE, deliveryDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79099999999");
        $("button.button").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).
                should(exactText("Встреча успешно забронирована на " + deliveryDate));

    }


}
