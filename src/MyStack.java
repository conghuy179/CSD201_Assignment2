/**
 * Contain information and behaviours of stack
 */
public class MyStack {
    private MyList stackedList;

    /**
     * Constructor cua stack
     */

    public MyStack() {
        stackedList = new MyList();
    }

    /**
     * Function: Push Node in the list
     * @param node: Node needed to be added
     * Void function
     */

    public void push(Node node) {
        stackedList.prepend(node);
    }

    /**
     * Function: Print elements on screen
     */
    public void print() {
        stackedList.printLL();
    }

}
