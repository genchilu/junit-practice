package url.genchi.practice;

import java.util.HashMap;
import java.util.regex.Pattern;

public class ValidateField {

    final private static Pattern ipWhitePat = Pattern.compile(
        "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    final private static Pattern ipBlackPat = Pattern.compile(
        "^(?:(?:127|172.16|0|255|169|224|192.168).).*");
    public static boolean validateIP(String ip) {
        if(ip == null) {
            return false;
        }
        boolean passWhite = ipWhitePat.matcher(ip).matches();
        boolean passBlack = !ipBlackPat.matcher(ip).matches();
        return (passWhite && passBlack);
    }

    //下面的 pattern 不能 match xxx@gmail.com
    //final private static Pattern mailWhilePat = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)(\\.[a-zA-Z]{2,})$");
    final private static Pattern mailWhilePat = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)?(?:\\.[a-zA-Z]{2,})$");
    final private static Pattern mailBlackPat = Pattern.compile(
        ".*(?:-\\.|\\.-).*");
    public static boolean validateMail(String mail) {
        if(mail == null) {
            return false;
        }
        boolean passLowerCase = mail.startsWith(mail.toLowerCase());
        boolean passWhite = mailWhilePat.matcher(mail).matches();
        boolean passBlack = !mailBlackPat.matcher(mail).matches();
        return (passLowerCase && passWhite && passBlack);
    }

    private static HashMap<Character, Integer> leter2ValidSum = new HashMap<Character, Integer>(){{
        put('A', 1);
        put('B', 10);
        put('C', 19);
        put('D', 28);
        put('E', 37);
        put('F', 46);
        put('G', 55);
        put('H', 64);
        put('J', 73);
        put('K', 82);
        put('L', 2);
        put('M', 11);
        put('N', 20);
        put('P', 29);
        put('Q', 38);
        put('R', 47);
        put('S', 56);
        put('T', 65);
        put('U', 74);
        put('V', 83);
        put('X', 3);
        put('Y', 12);
        put('W', 21);
        put('Z', 30);
        put('I', 39);
        put('O', 48);
    }};
    private static boolean isValidLocalIdNumber(String idNumber) {
        int validSum = leter2ValidSum.get(idNumber.charAt(0));
        for(int i = 0; i<8;i++){
            int digit = Character.getNumericValue(idNumber.charAt(i+1));
            validSum += digit*(8-i);
        }
        int lastDigit = Character.getNumericValue(idNumber.charAt(9));
        validSum += lastDigit;
        return ((validSum % 10) == 0);
    }

    final private static Pattern localIdNumberPat = Pattern.compile("^(?i)[a-hj-z][1-2][0-9]{8}$");
    final private static Pattern foreignIdNumberPat = Pattern.compile("^(?i)[i][0-9]{9}$");
    public static boolean validateIdNumber(String idNumber) {
        if(idNumber == null) {
            return false;
        }
        boolean passUpperCase = idNumber.equals(idNumber.toUpperCase());
        boolean passLocalIDNumberPat = localIdNumberPat.matcher(idNumber).matches() && ValidateField.isValidLocalIdNumber(idNumber);
        boolean passForeignIDNumberPat = foreignIdNumberPat.matcher(idNumber).matches();
        return passUpperCase && (passLocalIDNumberPat || passForeignIDNumberPat);
    }

    public static void main(String args[]) {
        String tmp = "A368829966";
        System.out.println(Character.getNumericValue('3'));
    }
}
