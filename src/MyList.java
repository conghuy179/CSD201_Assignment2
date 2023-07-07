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

    /**
     * Checking if this list is empty
     * @return true if list is empty
     */
    public boolean isEmpty() {
        if (head.getNext() == null) {
            return true;
        } else {
            return false;
        }
    }

    public int length() {
        int count = 0;
        if (!isEmpty()) {
            Node current = head;
            while (current != null) {
                count++;
                current = current.getNext();
            }
        }
        return count;
    }

    public Node prepend(Node item) {
        Node newHead = item;
        newHead.setNext(head);
        return newHead;
    }

    public Node append(Node item) {
        Node last = item;
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(last);
        return last;
    }

    public Node insert(int position, Node item) {
        if (position < 0 || position > length()) {
            System.out.println("Nam ngoai day.");
            return null;
        }
        Node bodyPart = item;
        int x = 1;
        Node current = head;
        while (current.getNext() != null && x != position) {
            x++;
            current = current.getNext();
        }
        bodyPart.setNext(current.getNext());
        current.setNext(bodyPart);

        return bodyPart;
    }
    public Node search(String bCode) {
        Node current = head;
        while (current != null && current.getInfo().getbcode() != bCode) {
            current = current.getNext();
        }
        return current;
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

}
