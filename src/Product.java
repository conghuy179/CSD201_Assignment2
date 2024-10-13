/**
 * Contain information and behaviours of the product
 */
public class Product {
    private String bCode;
    private String title;
    private int quantity;
    private double price;

    /**
     * Constructor of Product class
     * @param bCode: product's ID, String
     * @param title: product's type, String
     * @param quantity: product's quantity, integer
     * @param price: product's price, double
     */
    public Product(String bCode, String title, int quantity, double price) {
        this.bCode = bCode;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Function: get bCode
     * @return: bCode String
     */
    public String getbcode () {
        return bCode;
    }

    /**
     * Function: Print product's information in String
     * @return: Product's information in String
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
