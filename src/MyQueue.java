/**
 * Contain information and behaviours of queue
 */
public class MyQueue {
    private MyList queueLink;

    /**
     * Constructor
     */
    public MyQueue() {
        queueLink = new MyList();
    }

    /**
     * Ham dua Node vao cuoi danh sach
     * @param node: Dang node
     * Ham void
     */
    public void enqueue(Node node) {
        queueLink.append(node);
    }

    /**
     * Ham in cac phan tu trong queue link ra man hinh
     * Ham void
     */
    public void print() {
        queueLink.printLL();
    }
}
