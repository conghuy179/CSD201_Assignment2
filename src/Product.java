/**
 * Contain information and behaviours of the product
 */
public class Product {
    private String bCode;
    private String title;
    private int quantity;
    private double price;

    public Product() {}
    public Product(String bCode, String title, int quantity, double price) {
        this.bCode = bCode;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "bCode='" + bCode + '\'' +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
