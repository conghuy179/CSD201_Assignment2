/**
 * Contain information and behaviours of queue
 */
public class MyQueue {
    private MyList queueLink;

    public MyQueue() {
        queueLink = new MyList();
    }

    public void enqueue(Node node) {
        queueLink.append(node);
    }

    public Node dequeue() {
        return queueLink.takeLast();
    }

    public void print() {
        queueLink.printLL();
    }

    public void printLL() {
        Node current = queueLink.getNode(0);
        while (current != null) {
            System.out.print(current.getInfo() + " " + "\n");
            current = current.getNext();
        }
    }
//    public static void main(String[] args) {
//        MyQueue mq = new MyQueue();
//        Product a = new Product("a", "banhgio", 2, 10000);
//        Product b = new Product("b", "garan", 200, 30000);
//        Product c = new Product("c", "Pizza", 2, 100000);
//        Node nA = new Node(a);
//        Node nB = new Node(b);
//        Node nC = new Node(c);
//        mq.enqueue(nA);
//        mq.enqueue(nB);
//        mq.enqueue(nC);
//
//        mq.print();
//        System.out.printf("\n");
//        System.out.println("Lay ra: " + mq.dequeue());
//        mq.print();
//        System.out.printf("\n");
//        System.out.println("Lay ra: " + mq.dequeue());
//        mq.print();
//        System.out.printf("\n");
//        System.out.println("Lay ra: " + mq.dequeue());
//        mq.print();
//        System.out.printf("\n");
//        mq.dequeue();
//        mq.print();
//        System.out.println("Lay ra: " + mq.dequeue());
//
//    }
}
