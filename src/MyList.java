/**
 * Contain information and behaviours of linked list
 */
public class MyList {
    private Node head;
    private Node tail;

    public MyList() {
    }

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
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public Node prepend(Node item) {
        Node newHead = item;
        newHead.setNext(head);
        head = newHead;
        return newHead;
    }

    public Node append(Node item) {
        add(item);
        return tail;
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
        while (current != null) {
            if (current.getInfo().getbcode().equals(bCode)) {
                break;
            } else {
                current = current.getNext();
            }
        }
        return current;
    }

    public Node getNode(int position) {
        if (position < 0 || position > length()) {
            System.out.println("Nam ngoai day.");
            return null;
        }
        int x = 0;
        Node current = head;
        while (current.getNext() != null && x != position) {
            x++;
            current = current.getNext();
        }

        return current;
    }

    public void printLL() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " " + "\n");
            current = current.getNext();
        }
    }

    public Node takeFirst() {
        if (head == null) {
            return null;
        } else if (head == tail) {
            Node result = head;
            head = null;
            tail = null;
            return result;
        } else {
            Node result = head;
            head = head.getNext();
            return result;
        }
    }

    public Node takeLast() {
        if (head == null) {
            return null;
        } else if (head == tail) {
            Node result = head;
            head = null;
            tail = null;
            return result;
        } else {
            Node current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            Node result = tail;
            current.setNext(null);
            tail = current;
            return result;
        }
    }

    public Node removeFirstNode() {
        if (head == null)
            return null;

        // Move the head pointer to the next node
        Node temp = head;
        head = head.getNext();

        return head;
    }

    public void delete(int position) {
        if (position < 0 || position > length()) {
            System.out.println("Nam ngoai day.");
        }
        int x = 0;
        Node current = head;
        if (position == 0) {
            removeFirstNode();
        } else {
            while (current.getNext() != null && x != position - 1) {
                x++;
                current = current.getNext();
            }
            Node a = current.getNext();
            current.setNext(a.getNext());
            a.setNext(null);
        }
    }

//    public static void main(String[] args) {
//        MyList ml = new MyList();
//        Product a = new Product("a", "banhgio", 2, 10000);
//        Product b = new Product("b", "garan", 200, 30000);
//        Product c = new Product("c", "Pizza", 2, 100000);
//        Node nA = new Node(a);
//        Node nB = new Node(b);
//        Node nC = new Node(c);
//        ml.add(nA);
//        ml.add(nB);
//        ml.add(nC);
//
//        ml.printLL();
//        Node pop = ml.takeFirst();
//        System.out.printf("\nPop ra: %s\n", pop);
//        ml.printLL();
//
//        Node dequeue = ml.takeLast();
//        System.out.printf("\nDequeue: %s\n", dequeue);
//        ml.printLL();
//    }
}
