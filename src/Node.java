/**
 * Manage information and behaviours of each node in the list
 */
public class Node {
    private Product info;
    private Node next;

    /**
     * First Constructor of Node
     */
    public Node() {}

    /**
     * Second Constructor of the list
     * @param info: Product's information, Product type
     */
    public Node(Product info) {
        this.info = info;
        next = null;
    }

    /**
     * Function: Get information of the Product
     * @return Product's Information
     */
    public Product getInfo() {
        return info;
    }

    /**
     * Function: get Node next
     * @return: Node next
     */
    public Node getNext() {
        return next;
    }

    /**
     * Function: Set Node next
     * @param next: Node that needed to be set
     * Void Function
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Function: Change info to String
     * @return: String
     */
    @Override
    public String toString() {
        return  info + "";
    }
}
