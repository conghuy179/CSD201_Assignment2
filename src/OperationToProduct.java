import java.io.*;
import java.util.Arrays;
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
    final static int SELECTION_LOAD_QUEUE = 9;
    final static int SELECTION_EXIT = 0;

    private int selection;
    private Scanner sc;
    private MyList myList;

    public OperationToProduct(Scanner sc, MyList myList) {
        this.sc = sc;
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

    public void run() {
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

                String data = fileSc.nextLine();
                String[] part = data.split("\\|");
                String id = part[0].trim();
                String title = part[1].trim();
                int quantity = Integer.parseInt(part[2].trim());
                double price = Double.parseDouble(part[3].trim());
                System.out.println(id + " " + title + " " + quantity + " " + price);
                // Want: Dua thong tin product vao tung Node cua myList
                Product product = new Product(id, title, quantity, price);
                Node node = new Node(product);
                myList.add(node);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file.");
        }
        myList.printLL();
    }

    public void runSelectionInputAndAdd() {
    }

    public void runSelectionDisplayData() {
    }

    public void runSelectionSaveProductList() {
    }

    public void runSelectionSearchId() {
    }

    public void runSelectionDeleteId() {
    }

    public void runSelectionSortId() {
    }

    public void runSelectionConvertBinary() {
    }

    public void runSelectionLoadQueue() {
    }


    public boolean isSelectionValid(String selectionS) {
        if (selectionS.length() > 1) {
            throw new IllegalArgumentException("Chi nhap 1 so tu 0 den 9.");
        } else if (selectionS.charAt(0) < '0' || selectionS.charAt(0) > '9') {
            throw new IllegalArgumentException("CHi nhap 1 so trong khoang 0 den 9.");
        } else {
            return true;
        }
    }
}
