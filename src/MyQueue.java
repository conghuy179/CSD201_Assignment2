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
     * Function: Add node in the last position of the list
     * @param node: Node
     * Void function
     */
    public void enqueue(Node node) {
        queueLink.append(node);
    }

    /**
     * Function: Print elements in queue link on screen
     * Void function
     */
    public void print() {
        queueLink.printLL();
    }
}
