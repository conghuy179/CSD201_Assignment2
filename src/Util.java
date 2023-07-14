public class Util {
    public static String stringConvert(int n, String s) {
        if (n == 1) {
            return "1" + s;
        } else {
            return stringConvert(n/2, String.valueOf(n%2) + s);
        }
    }

    public static String toBinary(int n) {
        return stringConvert(n, "");
    }
}
