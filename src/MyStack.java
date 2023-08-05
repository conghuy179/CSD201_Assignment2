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
     * Ham push: dua node vao dau danh sach
     * @param node: Node duoc dua vao
     * Ham void
     */

    public void push(Node node) {
        stackedList.prepend(node);
    }

    /**
     * Ham in cac phan tu trong stack len man hinh
     */
    public void print() {
        stackedList.printLL();
    }

}
