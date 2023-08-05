import java.io.IOException;
import java.util.Scanner;

/**
 *  operate the system
 *  Ham main de chay chuong trinh
 */
public class AS2_Main {
    private static final Scanner sc = new Scanner(System.in);
    private static MyList ml = new MyList();
    private static MyStack ms = new MyStack();
    private static MyQueue mq = new MyQueue();
    public static void main(String[] args) throws IOException {
        OperationToProduct operation = new OperationToProduct(sc, ml, ms, mq);
        operation.run();
    }
}