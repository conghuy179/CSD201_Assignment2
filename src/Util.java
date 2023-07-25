public class Util {
    public static String stringConvert(int n, String s) {
        if (n == 1) {
            return "1" + s;
        } else {
            return stringConvert(n/2, String.valueOf(n%2) + s);
        }
    }

    public static String stringConvertVersion2(int n) {
        if ( n == 1) {
            return "1";
        } else if ( n == 0) {
            return "0";
        }
        return stringConvertVersion2(n/2) + String.valueOf(n % 2);
    }
    public static String toBinary(int n) {
        return stringConvert(n, "");
    }

    public static void main(String[] args) {
        int n = 0;
        System.out.println(stringConvertVersion2(n));
    }
}
