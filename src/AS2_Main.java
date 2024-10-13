import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *  operate the system
 *  Main to run the program
 */
public class AS2_Main {
    private static final Scanner sc = new Scanner(System.in);
    private static MyList ml = new MyList();
    private static MyStack ms = new MyStack();
    private static MyQueue mq = new MyQueue();
    public static void main(String[] args) throws IOException {
        //save output to file
        File fw = new File("console_output.txt");
        PrintStream filePS = new PrintStream(fw);

        PrintStream consolePS = System.out;

        OperationToProduct operation = new OperationToProduct(sc, ml, ms, mq, filePS, consolePS);
        operation.run();
    }
}