/**
 * Contain information and behaviours of linked list
 */
public class MyList {
    private Node head;
    private Node tail;

    public MyList() {
    }

    /**
     * Constructor creates linked list
     * @param head: First Node
     * @param tail: Last Node
     */
    public MyList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
     * Function: adding node
     * @param a: Node is added here
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
     * Function: Check the length of linked list
     * @return the length of list in integer
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
     * Function: Add node in first position
     * @param item: Node need to be added
     * @return Node is added in first position
     */
    public Node prepend(Node item) {
        Node newHead = item;
        newHead.setNext(head);
        head = newHead;
        return newHead;
    }

    /**
     * Function: Add node in last position
     * @param item: Node need to be added
     * @return: Node which is added
     */
    public Node append(Node item) {
        add(item);
        return tail;
    }

    /**
     * Function: Search for Node in linked list
     * @param bCode: String bCode of product in Node
     * @return: Searching Node
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
     * Function: Search Node according to position's number
     * @param position: Integer
     * @return: Searching Node
     */
    public Node getNode(int position) {
        if (position < 0 || position > length()) {
            System.out.println("Out of List.");
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
     * Function: Delete Node in list
     * @param a: Node needed to delete
     *  Void Function
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
     * Function: Print information of elements  linked list
     * Void function
     */
    public void printLL() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " " + "\n");
            current = current.getNext();
        }
    }

    /**
     * Function: Take the first element in list
     * @return Taken element
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
     * Function:Take the last element in list
     * @return Taken element
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
     * Function: Remove the first Node
     * Void function
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
     * Function: Delete Node based on desired position
     * @param position: Integer
     * Void function
     */
    public void delete(int position) {
        if (position < 0 || position > length()) {
            System.out.println("Out of List.");
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
