/**
 * Contain information and behaviours of linked list
 */
public class MyList {
    private Node head;
    private Node tail;
    public MyList() {}
    public MyList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public void add(Node a) {
        if (head == null) {
            head = a;
            tail = a;
        } else {
            tail.setNext(a);
            tail = a;
        }
    }

    public void printLL() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " ");
            current = current.getNext();
        }
    }

    /**
     * Checking if the list is empty
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return true;
    }

}
