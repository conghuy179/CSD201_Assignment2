/**
 * Contain information and behaviours of stack
 */
public class MyStack {
    private MyList stackedList;

    public MyStack() {
        stackedList = new MyList();
    }

    public void push(Node node) {
        stackedList.prepend(node);
    }

    public Node pop() {
        return stackedList.takeFirst();
    }

    public void printLL() {
        Node current = stackedList.getNode(0);
        while (current != null) {
            System.out.print(current.getInfo() + " " + "\n");
            current = current.getNext();
        }
    }
}
