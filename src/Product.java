/**
 * Contain information and behaviours of the product
 */
public class Product {
    private String bCode;
    private String title;
    private int quantity;
    private double price;

    /**
     * Constructor cua class Product
     * @param bCode: ID cua san pham, dang String
     * @param title: Loai san pham, dang String
     * @param quantity: So luong san pham, dang integer
     * @param price: Gia san pham, dang double
     */
    public Product(String bCode, String title, int quantity, double price) {
        this.bCode = bCode;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Ham get bCode
     * @return: bCode dang String
     */
    public String getbcode () {
        return bCode;
    }

    /**
     * Ham in thong tin san pham dang String
     * @return: Thong tin san pham dang String
     */
    @Override
    public String toString() {
        return bCode + "  " + "|" + "  " +
                title + "   " +  "|" + "   " +
                quantity + "   " +  "|" + "   " +
                price + "   " +  "|" + "   " +
                '\n';
    }
}
