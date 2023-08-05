public class Util {

    /**
     * Ham in ket qua theo he nhi phan
     * @param n: Dang integer
     * @return: Ket qua theo he nhi phan dang String
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
