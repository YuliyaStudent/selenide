package ru.netology.test;

import org.testng.annotations.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;


public class NotSuccessfulTest {
    String deliveryDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
            new Locale("ru")));

    @BeforeMethod
    public void openMethod() {
        open("http://localhost:9999/");
    }



}
