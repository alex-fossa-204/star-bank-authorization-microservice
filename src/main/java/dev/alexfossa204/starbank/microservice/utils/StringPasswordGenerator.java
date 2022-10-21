package dev.alexfossa204.starbank.microservice.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringPasswordGenerator {

    private static final int UPPERCASE_PASSWORD_MEMBERS_COUNTER = 3;

    private static final int LOWERCASE_PASSWORD_MEMBERS_COUNTER = 3;

    private static final int SPECIAL_CHARACTER_PASSWORD_MEMBERS_COUNTER = 1;

    private static final int NUMERIC_PASSWORD_MEMBERS_COUNTER = 3;

    private static final int VERIFICATION_CODE_NUMERIC_QUANTITY = 6;

    private static final boolean IS_LETTER = true;

    private static final boolean IS_NUMBER = true;

    public static String generateRandomPassword() {
        String password = rawPasswordGenerator(UPPERCASE_PASSWORD_MEMBERS_COUNTER, LOWERCASE_PASSWORD_MEMBERS_COUNTER, SPECIAL_CHARACTER_PASSWORD_MEMBERS_COUNTER, NUMERIC_PASSWORD_MEMBERS_COUNTER);
        return passwordCharactersCombiner(password);
    }

    public static String generateRandomVerificationCode() {
        String code = numericsRandomStringGenerator(VERIFICATION_CODE_NUMERIC_QUANTITY);
        return passwordCharactersCombiner(code);
    }

    private static String passwordCharactersCombiner(String password) {
        List<Character> characters = password.chars()
                .mapToObj(character -> (char) character)
                .collect(Collectors.toList());
        Collections.shuffle(characters);
        return characters.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static String rawPasswordGenerator(int uppercaseCounter, int lowercaseCounter, int specialCharsCounter, int numericsCounter) {
        String password = uppercaseRandomStringGenerator(uppercaseCounter)
                .concat(lowercaseRandomStringGenerator(lowercaseCounter))
                .concat(specialCharactersRandomStringGenerator(specialCharsCounter))
                .concat(numericsRandomStringGenerator(numericsCounter));
        return password;
    }

    private static String uppercaseRandomStringGenerator(int count) {
        return RandomStringUtils.random(count, 65, 90, IS_LETTER, IS_NUMBER);
    }

    private static String lowercaseRandomStringGenerator(int count) {
        return RandomStringUtils.random(count, 97, 122, IS_LETTER, IS_NUMBER);
    }

    private static String specialCharactersRandomStringGenerator(int count) {
        return RandomStringUtils.random(count, 33, 47, !IS_LETTER, !IS_NUMBER);
    }

    private static String numericsRandomStringGenerator(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    public static void main(String[] args) {
        String pwd = generateRandomPassword();
        System.out.println(pwd);
    }


}
