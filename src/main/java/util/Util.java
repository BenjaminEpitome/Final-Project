package util;

public class Util {

    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] words = str.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            String w = words[i];

            if (w.length() > 0) {
                String first = w.substring(0, 1).toUpperCase();
                String rest = "";

                if (w.length() > 1) {
                    rest = w.substring(1).toLowerCase();
                }

                result += first + rest;
            }

            if (i < words.length - 1) {
                result += " ";
            }
        }

        return result;
    }
}
