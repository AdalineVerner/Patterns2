package ru.netology.javaqa.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.data.DataGenerator;

import static com.codeborne.selenide.Selenide.*;

public class PostTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldPostUser() {
        DataGenerator.RegistrationDto registeredUser = DataGenerator.Registration.generateRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("button.button").click();
        $("h2.heading").shouldBe(Condition.visible).shouldHave(Condition.exactText("Личный кабинет"));
    }

    @Test
    public void invalidLogin() {
        DataGenerator.RegistrationDto registeredUser = DataGenerator.Registration.generateRegisteredUser("active");
        String login = DataGenerator.Registration.generateUser("active").getLogin();
        $("[data-test-id='login'] input").setValue(login);
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ошибка!"));
    }

    @Test
    public void invalidPassword() {
        DataGenerator.RegistrationDto registeredUser = DataGenerator.Registration.generateRegisteredUser("active");
        String password = "joke457";
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(password);
        $("button.button").click();
        $("[data-test-id='error-notification']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ошибка!"));
    }

    @Test
    public void blockedUser() {
        DataGenerator.RegistrationDto registeredUser = DataGenerator.Registration.generateRegisteredUser("blocked");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ошибка"));

    }

}
