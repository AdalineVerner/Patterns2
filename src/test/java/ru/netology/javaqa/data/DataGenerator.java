package ru.netology.javaqa.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("en"));
    private DataGenerator() {

    }

    public static String generateLogin() {

        return faker.name().username();
    }

    public static String generatePassword() {

        return faker.internet().password();
    }
    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto generateUser(String status) {
            return new RegistrationDto(generateLogin(), generatePassword(), status);
        }

        public static RegistrationDto generateRegisteredUser(String status) {
            return AuthTest.post(generateUser(status));
        }
    }

        @Value
        public static class RegistrationDto {
            String login;
            String password;
            String status;
        }


    }

