public class Util {

    /**
     * Function: Print result in Binary
     * @param n: integer
     * @return: Result in Binary, String
     */
    public static String stringConvertVersion2(int n) {
        if ( n == 1) {
            return "1";
        } else if ( n == 0) {
            return "0";
        }
        return stringConvertVersion2(n/2) + String.valueOf(n % 2);
    }
}
