/**
 * Contain information and behaviours of linked list
 */
public class MyList {
    private Node head;
    private Node tail;

    public MyList() {
    }

    /**
     * Constructor tao linked list
     * @param head: Node dau tien trong day
     * @param tail: Node cuoi cung trong day
     */
    public MyList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
     * Ham them node vao day
     * @param a: Node duoc them vao day
     */
    public void add(Node a) {
        if (head == null) {
            head = a;
            tail = a;
        } else {
            tail.setNext(a);
            tail = a;
            if (a != null) {
                a.setNext(null);
            }
        }
    }

    /**
     * Ham kiem tra do dai cua linked list
     * @return tra ve do dai cua list dang integer
     */

    public int length() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    /**
     * Ham them Node vao vi tri dau tien cua day
     * @param item: Node duoc them vao
     * @return Node duoc them vao o vi tri dau tien
     */
    public Node prepend(Node item) {
        Node newHead = item;
        newHead.setNext(head);
        head = newHead;
        return newHead;
    }

    /**
     * Ham them vao vi tri cuoi day
     * @param item: Node can them
     * @return: Node da duoc them vao
     */
    public Node append(Node item) {
        add(item);
        return tail;
    }

    /**
     * Ham tim kiem Node trong linked list
     * @param bCode: String bCode cua san pham trong Node
     * @return: Node dang can tim kiem
     */
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

    /**
     * Ham tim kiem Node dua theo so vi tri
     * @param position: dang integer
     * @return: Node dang can tim kiem
     */
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

    /**
     * Ham xoa Node trong list
     * @param a: Node can xoa
     * Ham void
     */
    public void deleteNode(Node a) {
       Node current = head;
       if (head == a) {
           removeFirstNode();
       } else {
           while (current.getNext() != null && current.getNext() != a) {
               current = current.getNext();
           }
           if (current.getNext() == a) {
               current.setNext(a.getNext());
               a.setNext(null);
           }
       }
    }

    /**
     * Ham in thong tin cac phan tu trong linked list ra man hinhf
     * Ham void
     */
    public void printLL() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " " + "\n");
            current = current.getNext();
        }
    }

    /**
     * Ham lay phan tu dau tien trong day
     * @return Phan tu da lay
     */
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

    /**
     * Ham lay phan tu cuoi cung trong day
     * @return phan tu da lay
     */
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

    /**
     * Ham loai bo Node dau tien trong day
     * Ham void
     */
    public void removeFirstNode() {
        // Move the head pointer to the next node
        if (head != null) {
            Node tem = head;
            head = head.getNext();
            tem.setNext(null);
        }
    }

    /**
     * Ham xoa Node dua theo vi tri
     * @param position: dang integer
     * Ham void
     */
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
}
