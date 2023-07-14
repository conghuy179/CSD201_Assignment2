/**
 * Contain information and behaviours of queue
 */
public class MyQueue {
    private MyList linkedList;

    public MyQueue() {
        linkedList = new MyList();
    }

    public void enqueue(Node node) {
        linkedList.append(node);
    }

    public Node dequeue() {
        return linkedList.takeLast();
    }

    public void print() {
        linkedList.printLL();
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
