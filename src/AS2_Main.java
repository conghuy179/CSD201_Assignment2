import java.io.IOException;
import java.util.Scanner;

/**
 *  operate the system
 */
public class AS2_Main {
    private static final Scanner sc = new Scanner(System.in);
    private static MyList ml = new MyList();
    public static void main(String[] args) throws IOException {
        OperationToProduct operation = new OperationToProduct(sc, ml);
        operation.run();
    }
}