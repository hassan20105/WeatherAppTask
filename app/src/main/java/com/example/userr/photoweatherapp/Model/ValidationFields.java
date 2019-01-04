package com.example.userr.photoweatherapp.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationFields {

    public static boolean isValidPassword(CharSequence target) {
        if (!(target.length() < 6))
            return true;
        else
            return false;
    }

    public static boolean isValidConfirmPassword(String pass, String conPass) {
        if (pass.equals(conPass))
            return true;
        else
            return false;

    }

    public static boolean isEmpty(String e) {

        if (e.length() == 0) {
            return true;
        } else
            return false;
    }

    public static boolean isValidEmail(String email) {

        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern p = Pattern.compile(email_pattern);
        Matcher m = p.matcher(email);

        if (m.matches()) {
            return true;
        }

        return false;
    }


    public static boolean isPhoneValid(String phone) {
        String firstDigit = phone.substring(0, 1);
        if (firstDigit.equals("0") && phone.length() == 11) {
            return true;
        }
        else
            return false;
    }
}
