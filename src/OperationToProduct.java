import java.io.*;
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
    private MyStack myStack;
    private MyQueue myQueue;
    private PrintStream outFilePS;
    private PrintStream outConsolePS;

    /**
     * Constructor of Operation to Product
     *
     * @param sc:      Scanner
     * @param myList:  MyList
     * @param myStack: Stack
     * @param myQueue: Queue
     */
    public OperationToProduct(Scanner sc, MyList myList, MyStack myStack, MyQueue myQueue, PrintStream outFilePS, PrintStream outConsolePS) {
        this.sc = sc.useDelimiter("\n");
        this.myList = myList;
        this.myStack = myStack;
        this.myQueue = myQueue;
        this.outFilePS = outFilePS;
        this.outConsolePS = outConsolePS;
    }

    /**
     * Menu hien thi ban dau
     */
    public void printMenu() {
        outConsolePS.println("Choose one of this options:");
        outConsolePS.println("Product list:");
        outConsolePS.println("1. Load data from file and display");
        outConsolePS.println("2. Input & add to the end.");
        outConsolePS.println("3. Display data");
        outConsolePS.println("4. Save product list to file.");
        outConsolePS.println("5. Search by ID");
        outConsolePS.println("6. Delete by ID");
        outConsolePS.println("7. Sort by ID.");
        outConsolePS.println("8. Convert to Binary");
        outConsolePS.println("9. Load to stack and display");
        outConsolePS.println("10. Load to queue and display.");
        outConsolePS.println("0. Exit");
        outConsolePS.println("Your selection: ");


        outFilePS.println("Choose one of this options:");
        outFilePS.println("Product list:");
        outFilePS.println("1. Load data from file and display");
        outFilePS.println("2. Input & add to the end.");
        outFilePS.println("3. Display data");
        outFilePS.println("4. Save product list to file.");
        outFilePS.println("5. Search by ID");
        outFilePS.println("6. Delete by ID");
        outFilePS.println("7. Sort by ID.");
        outFilePS.println("8. Convert to Binary");
        outFilePS.println("9. Load to stack and display");
        outFilePS.println("10. Load to queue and display.");
        outFilePS.println("0. Exit");
        outFilePS.println("Your selection: ");
    }

    /**
     * Ham chay chuong trinh
     *
     * @throws IOException
     */
    public void run() throws IOException {
        String selectionS;
        do {
            printMenu();
            try {
                selectionS = sc.next();
                isSelectionValid(selectionS);
            } catch (IllegalArgumentException e) {
                outConsolePS.println(e.getMessage());
                outFilePS.println(e.getMessage());
                continue;
            }
            selection = Integer.parseInt(selectionS);
            outFilePS.println(selection);
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

    /**
     * Ham kiem tra lua chon
     *
     * @param selectionS Lua chon cua user o dang String
     * @return True neu lua chon la hop le, false neu khong hop le
     */
    public boolean isSelectionValid(String selectionS) {
        if (selectionS.length() > 2) {
            throw new IllegalArgumentException("Chi nhap 1 so tu 0 den 10.");
        }
        return true;
    }

    /**
     * Ham chay lua chon 1: Load du lieu
     * Ham se doc file, bo qua 2 dong dau, cat va trim cac doan String chua thong tin san pham
     * Sau do kiem tra thong tin san pham
     * Neu hop le, luu vao product, dua product vao Node, dua tung Node vao myList
     */
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
            outConsolePS.println("Cannot find the file.");
            outFilePS.println("Cannot find the file.");
        }
        outConsolePS.println("Doc du lieu tu file hoan tat.");
        outFilePS.println("Doc du lieu tu file hoan tat.");
    }

    /**
     * Ham them du lieu
     * Yeu cau user nhap thong tin san pham
     * Neu thong tin hop le => Tao san pham moi va add vao Node
     * add Node da tao vao myList => Thong bao nhap san pham thanh cong
     */
    public void runSelectionInputAndAdd() {
        //input san pham
        String bCode;
        String title;
        int quantity = 0;
        double price = 0;

        outConsolePS.println("Nhap thong tin san pham muon them vao: ");
        outFilePS.println("Nhap thong tin san pham muon them vao: ");
        // Nhap va kiem tra dieu kien Id
        do {
            outConsolePS.println("Ma san pham: ");
            outFilePS.println("Ma san pham: ");
            bCode = sc.next();
            isBCodeValid(bCode);
            if (!isBCodeValid(bCode)) {
                outFilePS.println("Ma san pham: P + so thu tu. Yeu cau nhap lai.");
                outConsolePS.println("Ma san pham: P + so thu tu. Yeu cau nhap lai.");
            }
            if (isBCodeExisted(bCode)) {
                outFilePS.println("San pham da ton tai. Vui long thu lai.");
                outConsolePS.println("San pham da ton tai. Vui long thu lai.");
            }
        } while (isBCodeExisted(bCode) && isBCodeValid(bCode));

        outConsolePS.println("Loai san pham: ");
        outFilePS.println("Loai san pham: ");
        title = sc.next();

        // Nhap va kiem tra dieu kien quantity
        do {
            String quantityS = null;
            outConsolePS.println("So luong san pham: ");
            outFilePS.println("So luong san pham: ");
            try {
                quantityS = sc.next();
                isQuantityValid(quantityS);
            } catch (IllegalArgumentException e) {
                outConsolePS.println("Yeu cau nhap lai. Li do: " + e.getMessage());
                outFilePS.println("Yeu cau nhap lai. Li do: " + e.getMessage());
                continue;
            }
            quantity = Integer.parseInt(quantityS);
            if (!isQValid(quantity)) {
                outConsolePS.println("Nhap sai so luong. Yeu cau nhap lai.");
                outFilePS.println("Nhap sai so luong. Yeu cau nhap lai.");
            }
        } while (!isQValid(quantity));

        // Nhap va kiem tra dieu kien price
        do {
            String priceS = null;
            outConsolePS.println("Gia tien san pham: ");
            outFilePS.println("Gia tien san pham: ");
            try {
                priceS = sc.next();
                isPriceValid(priceS);
            } catch (IllegalArgumentException e) {
                outFilePS.println(priceS);
                outConsolePS.println(priceS);
                outFilePS.println("Yeu cau nhap lai. Li do: " + e.getMessage());
                outConsolePS.println("Yeu cau nhap lai. Li do: " + e.getMessage());
                continue;
            }
            price = Double.parseDouble(priceS);
            if (!isPValid(price)) {
                outFilePS.println("Nhap sai gia san pham. Yeu cau nhap lai.");
                outConsolePS.println("Nhap sai gia san pham. Yeu cau nhap lai.");
            }
        } while (!isPValid(price));

        // Thoa man tat ca dieu kien => Tao san pham va dua vao cuoi danh sach
        Product newProduct = new Product(bCode, title, quantity, price);
        Node node = new Node(newProduct);
        myList.add(node);
        outFilePS.println("San pham da duoc nhap thanh cong!");
        outConsolePS.println("San pham da duoc nhap thanh cong!");
    }

    /**
     * Ham kiem tra bCode co hop le hay khong
     *
     * @param bCode dang String
     * @return: True neu bCode hop le, False neu khong hop le
     */
    public boolean isBCodeValid(String bCode) {
        if (bCode == null) {
            return false;
        }
        return true;
    }

    /**
     * Ham kiem tra su ton tai cua bCode trong danh sach
     *
     * @param bCode dang String
     * @return: True neu bCode co ton tai trong danh sach, False neu khong ton tai
     */
    private boolean isBCodeExisted(String bCode) {
        Node result = myList.search(bCode);
        return result != null;
    }

    /**
     * Ham kiem tra gia tien san pham hop le hay khong
     *
     * @param priceS: Dang String
     * @return: True neu gia tien hop le, false neu khong hop le
     */
    private boolean isPriceValid(String priceS) {
        boolean result = false;
        if (Integer.parseInt(priceS) < 0) {
            outFilePS.println("Chi nhap so tien tren 0 dong");
            outConsolePS.println("Chi nhap so tien tren 0 dong");
        } else {
            result = true;
        }

        return result;
    }

    /**
     * Ham kiem tra gia tien san pham dang double hop le
     *
     * @param price dang double
     * @return: True neu gia tien hop le, false neu khong hop le
     */
    private boolean isPValid(double price) {
        boolean result = false;
        if (price > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Ham kiem tra so luong san pham co hop le hay khong
     *
     * @param quantityS dang String
     * @return: True neu so luong hop le, false neu khong hop le
     */
    private boolean isQuantityValid(String quantityS) {
        boolean result = false;
        if (Integer.parseInt(quantityS) < 1) {
            outFilePS.println("Chi nhap so luong san pham tren 1 chiec.");
            outConsolePS.println("Chi nhap so luong san pham tren 1 chiec.");
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Ham kiem tra so luong san pham dang integer co hop le hay khong
     *
     * @param quantity dang integer
     * @return: True neu hop le, false neu khong hop le
     */
    private boolean isQValid(int quantity) {
        boolean result = false;
        if (quantity > 1) {
            result = true;
        }
        return result;
    }

    /**
     * Ham hien thong tin danh sach san pham ra man hinh
     */
    public void runSelectionDisplayData() {
        System.out.println("Thong tin san pham trong danh sach: ");
        // Duyet tung phan tu va in thong tin san pham ra man hinh
        for (int i = 0; i < myList.length(); i++) {
            Node current = myList.getNode(i);
            String info = current.getInfo().toString();
            outFilePS.println("Info trong Node thu " + (i + 1) + " la: " + info);
            outConsolePS.println("Info trong Node thu " + (i + 1) + " la: " + info);
        }
    }

    /**
     * Ham luu danh sach san pham vao file
     *
     * @throws IOException
     */
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
            outFilePS.println("Successfully!");
            outConsolePS.println("Successfully!");
        } catch (IOException e) {
            outFilePS.println("An error occurred.");
            outConsolePS.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Ham tim kiem san pham theo so bCode
     * Kiem tra bCode co ton tai trong danh sach hay khong
     * Neu ton tai, in san pham ra man hinh
     * Neu khong ton tai, yeu cau nhap lai
     */
    public void runSelectionSearchId() {
        String bCode = null;
        Node target = new Node();
        do {
            outFilePS.println("Nhap so ID (bCode) can tim: ");
            outConsolePS.println("Nhap so ID (bCode) can tim: ");
            bCode = sc.next();
            if (!isBCodeExisted(bCode)) {
                outFilePS.println("So BCode khong ton tai. Yeu cau nhap lai.");
                outConsolePS.println("So BCode khong ton tai. Yeu cau nhap lai.");
            } else {
                target = myList.search(bCode);
                outFilePS.println("San pham can tim: ");
                outConsolePS.println("San pham can tim: ");
                outFilePS.println(target.getInfo().toString());
                outConsolePS.println(target.getInfo().toString());
            }
        } while (!isBCodeExisted(bCode));
    }

    /**
     * Ham xoa san pham dua theo so bCode
     * Neu ton tai, hien thong tin san pham va xoa khoi danh sach
     * Neu khong ton tai, yeu cau nhap lai
     */
    public void runSelectionDeleteId() {
        String bCode = null;
        Node target = new Node();
        // Tim so bCode can xoa
        do {
            outFilePS.println("Input the bcode to delete = ");
            outConsolePS.println("Input the bcode to delete = ");
            bCode = sc.next();
            if (!isBCodeExisted(bCode)) {
                outFilePS.println("So BCode khong ton tai. Yeu cau nhap lai.");
                outConsolePS.println("So BCode khong ton tai. Yeu cau nhap lai.");
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
        outFilePS.println("San pham can xoa: ");
        outConsolePS.println("San pham can xoa: ");
        outFilePS.println(target.getInfo().toString());
        outConsolePS.println(target.getInfo().toString());
        myList.delete(position);
        outFilePS.println("Deleted!");
        outConsolePS.println("Deleted!");
    }

    /**
     * Ham sort san pham theo bCode
     * Loc bCode vao Array String va sort bang Insertion Sort theo yeu cau
     * Tim cac Node san pham dua theo Array String, sau do tach cac Node ra khoi myList cu
     * Dua cac Node vao 1 sortedList moi
     * Bien sortedList nay thanh myList
     */
    public void runSelectionSortId() {
        // Tao Array
        outFilePS.println("Sort san pham theo ID.");
        outConsolePS.println("Sort san pham theo ID.");
        String[] bcodeArr = new String[myList.length()];
        // Loc bCode dua vao Array String va sort
        for (int i = 0; i < myList.length(); i++) {
            bcodeArr[i] = myList.getNode(i).getInfo().getbcode();
        }
        //swap

        if (bcodeArr[0].charAt(0) == 'P' && bcodeArr[1].charAt(0) == 'P') {
            if (Integer.parseInt(bcodeArr[0].substring(1, 3)) < Integer.parseInt(bcodeArr[1].substring(1, 3))) {
                String tem = bcodeArr[0];
                bcodeArr[0] = bcodeArr[1];
                bcodeArr[1] = tem;
            }
        } else if (bcodeArr[0].charAt(0) == 'P' && bcodeArr[1].charAt(0) != 'P'
                || bcodeArr[0].charAt(0) != 'P' && bcodeArr[1].charAt(0) == 'P') {
            if (bcodeArr[0].charAt(0) < bcodeArr[1].charAt(0)) {
                String tem = bcodeArr[0];
                bcodeArr[0] = bcodeArr[1];
                bcodeArr[1] = tem;
            }
        } else {
            if (Integer.parseInt(bcodeArr[0]) < Integer.parseInt(bcodeArr[1])) {
                String tem = bcodeArr[0];
                bcodeArr[0] = bcodeArr[1];
                bcodeArr[1] = tem;
            }
        }
        //insertion sort
        for (int i = 2; i < bcodeArr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (bcodeArr[j].charAt(0) == 'P' && bcodeArr[j - 1].charAt(0) == 'P') {
                    if (Integer.parseInt(bcodeArr[j].substring(1, 3)) < Integer.parseInt(bcodeArr[j - 1].substring(1, 3))) {
                        String tem = bcodeArr[j];
                        bcodeArr[j] = bcodeArr[j - 1];
                        bcodeArr[j - 1] = tem;
                    }
                } else if (bcodeArr[j].charAt(0) == 'P' && bcodeArr[j - 1].charAt(0) != 'P'
                        || bcodeArr[j].charAt(0) != 'P' && bcodeArr[j - 1].charAt(0) == 'P') {
                    if (bcodeArr[j].charAt(0) < bcodeArr[j - 1].charAt(0)) {
                        String tem = bcodeArr[j];
                        bcodeArr[j] = bcodeArr[j - 1];
                        bcodeArr[j - 1] = tem;
                    }
                } else {
                    if (Integer.parseInt(bcodeArr[j]) < Integer.parseInt(bcodeArr[j - 1])) {
                        String tem = bcodeArr[j];
                        bcodeArr[j] = bcodeArr[j - 1];
                        bcodeArr[j - 1] = tem;
                    }
                }
            }
        }

        //Tao sorted List moi
        MyList sortedList = new MyList();

        // Dua thong tin String vao sortedList de tim Node trong myList
        for (int i = 0; i < bcodeArr.length; i++) {
            Node result2 = myList.search(bcodeArr[i]);
            // xoa Node result khoi myList de result2 thanh Node rieng biet
            myList.deleteNode(result2);
            // Add Node result2 vao Sorted List
            sortedList.add(result2);
        }
        outFilePS.println("Sorted list hoan chinh: ");
        outConsolePS.println("Sorted list hoan chinh: ");
        sortedList.printLL();
        // bien myList thanh sortedList
        myList = sortedList;
    }

    /**
     * Ham doc so luong san pham theo he dem nhi phan
     */
    public void runSelectionConvertBinary() {
        outFilePS.println("So luong san pham: " + myList.length());
        outConsolePS.println("So luong san pham: " + myList.length());
        outFilePS.println("So luong san pham trong list theo he dem nhi phan: " + Util.stringConvertVersion2(myList.length()));
        outConsolePS.println("So luong san pham trong list theo he dem nhi phan: " + Util.stringConvertVersion2(myList.length()));
    }


    /**
     * Ham doc du lieu tu file va luu vao Stack
     * Kiem tra file:
     * Neu ton tai thi doc du lieu tu file
     * Neu khong ton tai thi huy
     * Sau khi doc du lieu, kiem tra dieu kien:
     * Neu hop le, luu vao product, luu product vao Node va dua Node vao myStack
     * Neu khong hop le, khong luu
     * Khi hoan tat, hien ket qua ra man hinh
     */
    public void runSelectionLoadStack() {
        outFilePS.println("Doc du lieu tu file data va luu vao Stack: ");
        outConsolePS.println("Doc du lieu tu file data va luu vao Stack: ");
        try {
            // Doc file
            File file = new File("data.txt");

            Scanner fileSc = new Scanner(file);
            fileSc.nextLine();
            fileSc.nextLine();
            while (fileSc.hasNextLine()) {
                // Luu thong tin san pham vao Stack list
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
                    // Dua node vao stacked List
                    myStack.push(node);
                }
            }
        } catch (FileNotFoundException e) {
            outFilePS.println("Cannot find the file.");
            outConsolePS.println("Cannot find the file.");
        }
        outFilePS.println("Doc du lieu tu file hoan tat.");
        outConsolePS.println("Doc du lieu tu file hoan tat.");
        outFilePS.println("Thong tin trong stack: ");
        outConsolePS.println("Thong tin trong stack: ");
        myStack.print();
    }

    /**
     * Ham doc du lieu tu file va luu vao Queue
     * Kiem tra file:
     * Neu ton tai thi doc du lieu tu file
     * Neu khong ton tai thi huy
     * Sau khi doc du lieu, kiem tra dieu kien:
     * Neu hop le, luu vao product, luu product vao Node va dua Node vao myQueue
     * Neu khong hop le, khong luu
     * Khi hoan tat, hien ket qua ra man hinh
     */
    public void runSelectionLoadQueue() {
        outFilePS.println("Doc du lieu tu file data va luu vao Queue: ");
        outConsolePS.println("Doc du lieu tu file data va luu vao Queue: ");
        try {
            // Doc file
            File file = new File("data.txt");

            Scanner fileSc = new Scanner(file);
            fileSc.nextLine();
            fileSc.nextLine();
            while (fileSc.hasNextLine()) {
                // Luu thong tin san pham vao Stack list
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
                    // Dua node vao stacked List
                    myQueue.enqueue(node);
                }
            }
        } catch (FileNotFoundException e) {
            outConsolePS.println("Cannot find the file.");
            outFilePS.println("Cannot find the file.");
        }
        outConsolePS.println("Doc du lieu tu file hoan tat.");
        outFilePS.println("Doc du lieu tu file hoan tat.");
        outConsolePS.println("Thong tin trong Queue: ");
        outFilePS.println("Thong tin trong Queue: ");
        myQueue.print();
    }
}
