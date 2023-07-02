/**
 * Manage information and behaviours of each node in the list
 */
public class Node {
    private Product info;
    private Node next;

    public Node() {}
    public Node(Product info) {
        this.info = info;
        next = null;
    }

    public Product getInfo() {
        return info;
    }

    public void setInfo(Product info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
