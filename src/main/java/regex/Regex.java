package regex;

import java.util.regex.Pattern;

public class Regex {
    public static boolean gmail(String gmail) {
        String regexPattern = "(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$";
        Pattern compile = Pattern.compile(regexPattern);
        if (compile.matcher(gmail).matches()) {
            return true;
        }
        return false;
    }

    public static boolean name(String name) {

        String regexPattern = "^([ \\u00c0-\\u01ffa-zA-Z'\\-]{5,})+$";
        Pattern compile = Pattern.compile(regexPattern);
        if (compile.matcher(name).matches()) {
            return true;
        }
        return false;
    }

    public static boolean phoneNumber(String phoneNumber) {
        String regexPattern = "^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{5}$";
        Pattern compile = Pattern.compile(regexPattern);
        if (compile.matcher(phoneNumber).matches()) {
            return true;
        }
        return false;
    }
}
