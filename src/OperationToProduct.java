import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Contain methods to perform every tasks
 */
public class OperationToProduct {
    final static int SELECTION_LOAD_DATA = 1;
    final static int SELECTION_INPUT_AND_ADD = 2;
    final static int SELECTION_DISPLAY_DATA = 3;
    final static int SELECTION_SAVE_PRODUCT_LIST = 4;
    final static int SELECTION_SEARCH_ID = 5;
    final static int SELECTION_DELETE_ID = 6;
    final static int SELECTION_SORT_ID = 7;
    final static int SELECTION_CONVERT_BINARY = 8;
    final static int SELECTION_LOAD_STACK = 9;
    final static int SELECTION_LOAD_QUEUE = 10;
    final static int SELECTION_EXIT = 0;

    private int selection;
    private Scanner sc;
    private MyList myList;

    public OperationToProduct(Scanner sc, MyList myList) {
        this.sc = sc.useDelimiter("\n");
        this.myList = myList;
    }

    public void printMenu() {
        System.out.println("Choose one of this options:");
        System.out.println("Product list:");
        System.out.println("1. Load data from file and display");
        System.out.println("2. Input & add to the end.");
        System.out.println("3. Display data");
        System.out.println("4. Save product list to file.");
        System.out.println("5. Search by ID");
        System.out.println("6. Delete by ID");
        System.out.println("7. Sort by ID.");
        System.out.println("8. Convert to Binary");
        System.out.println("9. Load to stack and display");
        System.out.println("10. Load to queue and display.");
        System.out.println("0. Exit");
        System.out.println("Your selection: ");
    }

    public void run() throws IOException {
        String selectionS;
        do {
            printMenu();
            try {
                selectionS = sc.next();
                isSelectionValid(selectionS);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            selection = Integer.parseInt(selectionS);
            switch (selection) {
                case SELECTION_LOAD_DATA:
                    runSelectionLoadData();
                    break;
                case SELECTION_INPUT_AND_ADD:
                    runSelectionInputAndAdd();
                    break;
                case SELECTION_DISPLAY_DATA:
                    runSelectionDisplayData();
                    break;
                case SELECTION_SAVE_PRODUCT_LIST:
                    runSelectionSaveProductList();
                    break;
                case SELECTION_SEARCH_ID:
                    runSelectionSearchId();
                    break;
                case SELECTION_DELETE_ID:
                    runSelectionDeleteId();
                    break;
                case SELECTION_SORT_ID:
                    runSelectionSortId();
                    break;
                case SELECTION_CONVERT_BINARY:
                    runSelectionConvertBinary();
                    break;
                case SELECTION_LOAD_STACK:
                    runSelectionLoadStack();
                    break;
                case SELECTION_LOAD_QUEUE:
                    runSelectionLoadQueue();
                    break;
            }
        } while (selection != SELECTION_EXIT);
    }


    public void runSelectionLoadData() {
        try {
            // Doc file
            File file = new File("data.txt");

            Scanner fileSc = new Scanner(file);
            fileSc.nextLine();
            fileSc.nextLine();
            while (fileSc.hasNextLine()) {
                // Luu thong tin san pham vao linked list
                // Cat va trim cac doan String chua thong tin san pham

                String data = fileSc.nextLine();
                String[] part = data.split("\\|");
                String bCode = part[0].trim();
                String title = part[1].trim();
                int quantity = Integer.parseInt(part[2].trim());
                double price = Double.parseDouble(part[3].trim());
                // Kiem tra dieu kien tung thanh phan, neu hop le tat ca thi cho vao product
                if (isBCodeValid(bCode)) {
                    // Want: Dua thong tin product vao product
                    Product product = new Product(bCode, title, quantity, price);
                    // Dua product vao Node
                    Node node = new Node(product);
                    // Dua node vao myList
                    myList.add(node);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file.");
        }
        System.out.println("Doc du lieu tu file hoan tat.");
    }

    public void runSelectionInputAndAdd() {
        //input san pham
        String bCode;
        String title;
        int quantity = 0;
        double price = 0;

        System.out.println("Nhap thong tin san pham muon them vao: ");
        // Nhap va kiem tra dieu kien Id
        do {
            System.out.println("Ma san pham: ");
            bCode = sc.next();
            isBCodeValid(bCode);
            if (!isBCodeValid(bCode)) {
                System.out.println("Ma san pham: P + so thu tu. Yeu cau nhap lai.");
            }
            if (isBCodeExisted(bCode)) {
                System.out.println("San pham da ton tai. Vui long thu lai.");
            }
        } while (isBCodeExisted(bCode) && isBCodeValid(bCode));

        System.out.println("Loai san pham: ");
        title = sc.next();

        // Nhap va kiem tra dieu kien quantity
        do {
            String quantityS = null;
            System.out.println("So luong san pham: ");
            try {
                quantityS = sc.next();
                isQuantityValid(quantityS);
            } catch (IllegalArgumentException e) {
                System.out.println("Yeu cau nhap lai. Li do: " + e.getMessage());
                continue;
            }
            quantity = Integer.parseInt(quantityS);
            if (!isQValid(quantity)) {
                System.out.println("Nhap sai so luong. Yeu cau nhap lai.");
            }
        } while (!isQValid(quantity));

        // Nhap va kiem tra dieu kien price
        do {
            String priceS = null;
            System.out.println("Gia tien san pham: ");
            try {
                priceS = sc.next();
                isPriceValid(priceS);
            } catch (IllegalArgumentException e) {
                System.out.println(priceS);
                System.out.println("Yeu cau nhap lai. Li do: " + e.getMessage());
                continue;
            }
            price = Double.parseDouble(priceS);
            if (!isPValid(price)) {
                System.out.println("Nhap sai gia san pham. Yeu cau nhap lai.");
            }
        } while (!isPValid(price));

        // Thoa man tat ca dieu kien => Tao san pham va dua vao cuoi danh sach
        Product newProduct = new Product(bCode, title, quantity, price);
        Node node = new Node(newProduct);
        myList.add(node);
    }

    public boolean isBCodeValid(String bCode) {
        if (bCode.charAt(0) != 'P') {
            return false;
        }
        return true;
    }

    private boolean isBCodeExisted(String bCode) {
        Node result = myList.search(bCode);
        return result != null;
    }

    private boolean isPriceValid(String priceS) {
        boolean result = false;
        if (Integer.parseInt(priceS) < 0) {
            System.out.println("Chi nhap so tien tren 0 dong");
        } else {
            result = true;
        }

        return result;
    }

    private boolean isPValid(double price) {
        boolean result = false;
        if (price > 0) {
            result = true;
        }
        return result;
    }

    private boolean isQuantityValid(String quantityS) {
        boolean result = false;
        if (Integer.parseInt(quantityS) < 1) {
            System.out.println("Chi nhap so luong san pham tren 1 chiec.");
        } else {
            result = true;
        }
        return result;
    }

    private boolean isQValid(int quantity) {
        boolean result = false;
        if (quantity > 1) {
            result = true;
        }
        return result;
    }

    public void runSelectionDisplayData() {
        System.out.println("Thong tin san pham trong danh sach: ");
        // Duyet tung phan tu
        for (int i = 0; i < myList.length(); i++) {
            Node current = myList.getNode(i);
            // Kiem tra bCode
            String bCode = current.getInfo().getbcode();
            if (isBCodeValid(bCode)) {
                String info = current.getInfo().toString();
                System.out.println("Info trong Node thu " + (i + 1) + " la: " + info);
            }
        }
    }

    public void runSelectionSaveProductList() throws IOException {
        try {
            FileWriter myfile = new FileWriter("data.txt");
            myfile.write("ID |  Title   | Quantity | Price\n" +
                    "--------------------------------\n");
            for (int i = 0; i < myList.length(); i++) {
                Node current = myList.getNode(i);
                // Kiem tra bCode
                String bCode = current.getInfo().getbcode();
                if (isBCodeValid(bCode)) {
                    myfile.write(myList.getNode(i).toString());
                }
            }
            myfile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void runSelectionSearchId() {
        String bCode = null;
        Node target = new Node();
        do {
            System.out.println("Nhap so ID (bCode) can tim: ");
            bCode = sc.next();
            if (!isBCodeExisted(bCode)) {
                System.out.println("So BCode khong ton tai. Yeu cau nhap lai.");
            } else {
                target = myList.search(bCode);
                System.out.println("San pham can tim: ");
                System.out.println(target.getInfo().toString());
            }
        } while (!isBCodeExisted(bCode));
    }

    public void runSelectionDeleteId() {
        String bCode = null;
        Node target = new Node();
        // Tim so bCode can xoa
        do {
            System.out.println("Nhap so ID (bCode) can xoa: ");
            bCode = sc.next();
            if (!isBCodeExisted(bCode)) {
                System.out.println("So BCode khong ton tai. Yeu cau nhap lai.");
            }
        } while (!isBCodeExisted(bCode));

        // Tim position cua Product trong linked list de xoa
        target = myList.search(bCode);
        int position = -1;
        for (int i = 0; i < myList.length(); i++) {
            if (myList.getNode(i) == target) {
                position = i;
            }
        }
        System.out.println("San pham can xoa: ");
        System.out.println(target.getInfo().toString());
        myList.delete(position);
        System.out.println("Xoa thanh cong!");
    }

    public void runSelectionSortId() {
        // Tao Array
        System.out.println("Xep vat pham theo ID.");
        String[] bcodeArr = new String[myList.length()];
        // Loc bCode dua vao Array String va sort
        for (int i = 0; i < myList.length(); i++) {
            bcodeArr[i] = myList.getNode(i).getInfo().getbcode();
        }
        //swap
        if (Integer.parseInt(bcodeArr[0].substring(1, 3)) > Integer.parseInt(bcodeArr[1].substring(1, 3))) {
            String tem = bcodeArr[0];
            bcodeArr[0] = bcodeArr[1];
            bcodeArr[1] = tem;
        }
        //insertion sort
        for (int i = 2; i < bcodeArr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (Integer.parseInt(bcodeArr[j].substring(1, 3)) < Integer.parseInt(bcodeArr[j - 1].substring(1, 3))) {
                    String tem = bcodeArr[j];
                    bcodeArr[j] = bcodeArr[j - 1];
                    bcodeArr[j - 1] = tem;
                }
            }
        }
        //Tao sorted List moi
        MyList sortedList = new MyList();

        // Dua thong tin String vao sortedList
        for (int i = 0; i < bcodeArr.length; i++) {
            Node result2 = myList.search(bcodeArr[i]);
            // xoa result2 khoi myList de result2 thanh Node rieng biet
            myList.deleteNode(result2);
            // Add Node result2 vao Sorted List
            sortedList.add(result2);
        }
        System.out.println("Sorted list hoan chinh: ");
        sortedList.printLL();
    }

    public void runSelectionConvertBinary() {
    }


    public void runSelectionLoadStack() {
    }

    public void runSelectionLoadQueue() {
    }


    public boolean isSelectionValid(String selectionS) {
        if (selectionS.length() > 1) {
            throw new IllegalArgumentException("Chi nhap 1 so tu 0 den 9.");
        } else if (selectionS.charAt(0) < '0' || selectionS.charAt(0) > '9') {
            throw new IllegalArgumentException("Chi nhap 1 so trong khoang 0 den 9.");
        } else {
            return true;
        }
    }
}
