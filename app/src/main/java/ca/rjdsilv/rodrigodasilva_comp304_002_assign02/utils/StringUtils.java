package ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils;

import android.util.Patterns;

/**
 * Util class created to deal with strings on the application.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class StringUtils {
    public static final String EMPTY = "";

    public static boolean isBlank(String value) {
        return value == null || EMPTY.equals(value.trim());
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static boolean isValidEmail(String value) {
        return isNotBlank(value) && Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

    public static boolean isValidPhone(String value) {
        return isNotBlank(value) && Patterns.PHONE.matcher(value).matches();
    }

    /**
     * This method uses the Luhn algorithm for validating the a credit card number.
     *
     * @param value the value to be validated
     * @return <b>true</b> if it's a valid credit card number. <b>false</b> otherwise.
     */
    public static boolean isCreditCard(String value) {
        value = value.replace(" ", EMPTY);

        if (value.length() < 16) {
            return false;
        }

        int[] ints = new int[value.length()];
        for (int i = 0; i < value.length(); i++) {
            ints[i] = Integer.parseInt(value.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }

        return sum % 10 == 0;
    }

    public static boolean isCvv(String value) {
        return isNotBlank(value) && value.length() >= 3 && value.length() <= 4;
    }

    public static boolean isMonth(String value) {
        if (isBlank(value) || value.length() != 2) {
            return false;
        }

        try {
            int month = Integer.parseInt(value);
            return month >= 1 && month <= 12;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isYearInRange(String value, int min, int max) {
        if (isBlank(value) || value.length() != 2) {
            return false;
        }

        try {
            int year = Integer.parseInt(value);
            return year + 2000 >= min && year + 2000 <= max;
        } catch (Exception ex) {
            return false;
        }
    }
}
