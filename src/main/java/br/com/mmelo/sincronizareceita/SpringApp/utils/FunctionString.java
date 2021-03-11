package br.com.mmelo.sincronizareceita.SpringApp.utils;

public class FunctionString {

    public static String onlyNumbers(String str) {
        if (str.length() != 0) {
            return str.replaceAll("[^0123456789]", "");
        } else {
            return "";
        }
    }

    public static Boolean validateString(String str){
        if (str.length() == 0){
            return false;
        } else {
            if (str == null || str.isBlank() || str.isEmpty()){
                return false;
            }
        }

        return true;
    }

    public static Boolean isNumbers(String str){
        return str.matches("[0-9]+");
    }
}
