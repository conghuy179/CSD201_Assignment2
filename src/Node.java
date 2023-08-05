/**
 * Manage information and behaviours of each node in the list
 */
public class Node {
    private Product info;
    private Node next;

    /**
     * Constructor so 1 cua Node
     */
    public Node() {}

    /**
     * Constructor so 2 cua day
     * @param info: Thong tin san pham, dang product
     */
    public Node(Product info) {
        this.info = info;
        next = null;
    }

    /**
     * Ham lay thong tin san pham
     * @return thong tin san pham dang Product
     */
    public Product getInfo() {
        return info;
    }

    /**
     * Ham get Node next
     * @return: Node next
     */
    public Node getNext() {
        return next;
    }

    /**
     * Ham set Node next
     * @param next: Dang node muon set
     * Ham void
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Ham chuyen doi info thanh String
     * @return: String
     */
    @Override
    public String toString() {
        return  info + "";
    }
}
