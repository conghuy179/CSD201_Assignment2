/**
 * Contain information and behaviours of stack
 */
public class MyStack {
    private MyList linkedList;

    public MyStack() {
        linkedList = new MyList();
    }

    public void push(Node node) {
        linkedList.prepend(node);
    }

    public Node pop() {
        return linkedList.takeFirst();
    }
}
