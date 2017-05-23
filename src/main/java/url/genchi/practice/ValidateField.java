package url.genchi.practice;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ValidateField {

    final private static Pattern ipWhileList = Pattern.compile(
        "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    final private static Pattern ipBlackList = Pattern.compile(
        "^(?:(?:127|172.16|0|255|169|224|192.168).).*");
    public static boolean validateIP(String ip) {
        if (ipWhileList.matcher(ip).matches()) {
            if(ipBlackList.matcher(ip).matches()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        String ip = "192.168.010.010";
        System.out.println(validateIP(ip));
    }
}
