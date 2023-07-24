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
        int x = 1;
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

    public void delete(int position) {
        if (position < 0 || position > length()) {
            System.out.println("Nam ngoai day.");
        }
        int x = 1;
        Node current = head;
        while (current.getNext() != null && x != position - 1) {
            x++;
            current = current.getNext();
        }
        Node a = current.getNext();
        current.setNext(a.getNext());
        a.setNext(null);
    }

    public void swap(int first, int second) {
        Node x = getNode(first);
        Node y = getNode(second);

        Node temp = y.getNext();
        y.setNext(x);
        x.setNext(temp);
    }

    public void insertionSort(MyList myList) {
        MyList sortedMl = new MyList();


        Node current = myList.head;
        String bCodeFirst = null;
        String bCodeSecond = null;
        for (int i = 0; i < myList.length(); i++) {
            for (int j = i; j > 0 && Integer.parseInt(getNode(j).getInfo().getbcode().substring(1)) < Integer.parseInt(getNode(j - 1).getInfo().getbcode().substring(1)); j--) {
                System.out.println(Integer.parseInt(getNode(j).getInfo().getbcode().substring(1)));
                System.out.println(Integer.parseInt(getNode(j - 1).getInfo().getbcode().substring(1)));
                swap(j, j - 1);
                System.out.println(Integer.parseInt(getNode(j).getInfo().getbcode().substring(1)));
                System.out.println(Integer.parseInt(getNode(j - 1).getInfo().getbcode().substring(1)));
            }
        }
    }


//        if (current.getInfo().getbcode().equals(current.getNext().getInfo()) ) {
//            swap(a, 0, 1);
//        }
//
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
//                swap(arr, j, j - 1);
//            }
//        }
//        for (int i = 2; i < a.length; i++) {
//            for (int j = i; j > 0; j--) {
//                if (a[j] < a[j - 1]) {
//                    swap(a, j, j - 1);
//                }
//            }
//        }

    public static void main(String[] args) {
        MyList ml = new MyList();
        Product a = new Product("P01", "banhgio", 2, 10000);
        Product b = new Product("P02", "garan", 200, 30000);
        Product c = new Product("P03", "Pizza", 2, 100000);
        Product d = new Product("P04", "BanhBo", 3, 120000);
        Product e = new Product("P05", "Friec", 4, 130000);
        Node nB = new Node(b);
        Node nD = new Node(d);
        Node nA = new Node(a);
        Node nC = new Node(c);
        Node nE = new Node(e);
        ml.add(nB);
        ml.add(nD);
        ml.add(nA);
        ml.add(nC);
        ml.add(nE);
        ml.printLL();
        System.out.println("Swap");
        ml.swap(0,1);
//        ml.insertionSort(ml);
        ml.printLL();
    }
}
