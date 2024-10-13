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
     * Menu: First Menu
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
     * Function: Run program
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
     * Function: Examine selection
     *
     * @param selectionS User's Selection in String
     * @return True if legit, false if not
     */
    public boolean isSelectionValid(String selectionS) {
        if (selectionS.length() > 2) {
            throw new IllegalArgumentException("Only type number from 0 to 10.");
        }
        return true;
    }

    /**
     * Function: Run first selection: Load Data
     * Function will read file, ignore first 2 sentences, cut and trim the String containing product's information
     * Then check the product's information
     * If legit, save to product, bring product inside Node, bring Node in myList
     */
    public void runSelectionLoadData() {
        try {
            // Read file
            File file = new File("data.txt");

            Scanner fileSc = new Scanner(file);
            fileSc.nextLine();
            fileSc.nextLine();
            while (fileSc.hasNextLine()) {
                // Save product's information in linked list
                // Cut and trim the desired String contains product's information

                String data = fileSc.nextLine();
                String[] part = data.split("\\|");
                String bCode = part[0].trim();
                String title = part[1].trim();
                int quantity = Integer.parseInt(part[2].trim());
                double price = Double.parseDouble(part[3].trim());
                // Check conditions of each elements, if legit all then add to product
                if (isBCodeValid(bCode)) {
                    // Want: Add product's information in Product
                    Product product = new Product(bCode, title, quantity, price);
                    // Add Product in Node
                    Node node = new Node(product);
                    // Add Node in myList
                    myList.add(node);
                }
            }
        } catch (FileNotFoundException e) {
            outConsolePS.println("Cannot find the file.");
            outFilePS.println("Cannot find the file.");
        }
        outConsolePS.println("Reading data from file completed.");
        outFilePS.println("Reading data from file completed.");
    }

    /**
     * Function: Add data
     * Require user to input product's information
     * If info is legit => Create new product and add to Node
     * Add created Node in myList => Notice add new product successful
     */
    public void runSelectionInputAndAdd() {
        //input product
        String bCode;
        String title;
        int quantity = 0;
        double price = 0;

        outConsolePS.println("Input product's information: ");
        outFilePS.println("Input product's information: ");
        // Add and check ID's condition
        do {
            outConsolePS.println("bCode: ");
            outFilePS.println("bCode: ");
            bCode = sc.next();
            isBCodeValid(bCode);
            if (!isBCodeValid(bCode)) {
                outFilePS.println("bCode: P + number. Please input again.");
                outConsolePS.println("bCode: P + number. Please input again.");
            }
            if (isBCodeExisted(bCode)) {
                outFilePS.println("Product is existed in the list. Please try again.");
                outConsolePS.println("Product is existed in the list. Please try again.");
            }
        } while (isBCodeExisted(bCode) && isBCodeValid(bCode));

        outConsolePS.println("Product type: ");
        outFilePS.println("Product type: ");
        title = sc.next();

        // Input and check quantity's condition
        do {
            String quantityS = null;
            outConsolePS.println("Product's quantity: ");
            outFilePS.println("Product's quantity: ");
            try {
                quantityS = sc.next();
                isQuantityValid(quantityS);
            } catch (IllegalArgumentException e) {
                outConsolePS.println("Please input again. Reason: " + e.getMessage());
                outFilePS.println("Please input again. Reason" + e.getMessage());
                continue;
            }
            quantity = Integer.parseInt(quantityS);
            if (!isQValid(quantity)) {
                outConsolePS.println("Wrong quantity. Please input again.");
                outFilePS.println("Wrong quantity. Please input again.");
            }
        } while (!isQValid(quantity));

        // Input and check price's condition
        do {
            String priceS = null;
            outConsolePS.println("Product's price: ");
            outFilePS.println("Product's price: ");
            try {
                priceS = sc.next();
                isPriceValid(priceS);
            } catch (IllegalArgumentException e) {
                outFilePS.println(priceS);
                outConsolePS.println(priceS);
                outFilePS.println("Please input again. Reason: " + e.getMessage());
                outConsolePS.println("Please input again. Reason: " + e.getMessage());
                continue;
            }
            price = Double.parseDouble(priceS);
            if (!isPValid(price)) {
                outFilePS.println("Wrong product's price. Please input again.");
                outConsolePS.println("Wrong product's price. Please input again.");
            }
        } while (!isPValid(price));

        // If satisfied all conditions => Create product and add to the end of the list
        Product newProduct = new Product(bCode, title, quantity, price);
        Node node = new Node(newProduct);
        myList.add(node);
        outFilePS.println("Product created successful!");
        outConsolePS.println("Product created successful!");
    }

    /**
     * Function: Check if bCode is legit or not
     *
     * @param bCode String
     * @return: True if bCode is legit, False if not
     */
    public boolean isBCodeValid(String bCode) {
        if (bCode == null) {
            return false;
        }
        return true;
    }

    /**
     * Function: Check if bCode is existed or not
     *
     * @param bCode String
     * @return: True if bCode existed in the list, False if not
     */
    private boolean isBCodeExisted(String bCode) {
        Node result = myList.search(bCode);
        return result != null;
    }

    /**
     * Function: Check if product's price is legit or not
     *
     * @param priceS: String
     * @return: True if price's legit, false if not
     */
    private boolean isPriceValid(String priceS) {
        boolean result = false;
        if (Integer.parseInt(priceS) < 0) {
            outFilePS.println("The amount needs to be more than 0d.");
            outConsolePS.println("The amount needs to be more than 0d");
        } else {
            result = true;
        }

        return result;
    }

    /**
     * Function: Check if product's price (Double type) is legit or not
     *
     * @param price  double
     * @return: True if product's price is legit, false if not
     */
    private boolean isPValid(double price) {
        boolean result = false;
        if (price > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Function: Check if product's quantity is legit or not
     *
     * @param quantityS String
     * @return: True if quantity is legit, false if not
     */
    private boolean isQuantityValid(String quantityS) {
        boolean result = false;
        if (Integer.parseInt(quantityS) < 1) {
            outFilePS.println("The quantity needs to be more than 1.");
            outConsolePS.println("The quantity needs to be more than 1.");
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Function: Check if quantity (integer type) is legit or not
     *
     * @param quantity integer
     * @return: True if quantity is legit, false if not
     */
    private boolean isQValid(int quantity) {
        boolean result = false;
        if (quantity > 1) {
            result = true;
        }
        return result;
    }

    /**
     * Function: Print product's information on the screen
     */
    public void runSelectionDisplayData() {
        System.out.println("Product's information in the list: ");
        // Check each element and print product's information on the screen
        for (int i = 0; i < myList.length(); i++) {
            Node current = myList.getNode(i);
            String info = current.getInfo().toString();
            outFilePS.println("Info in Node number " + (i + 1) + " is: " + info);
            outConsolePS.println("Info in Node number " + (i + 1) + " is: " + info);
        }
    }

    /**
     * Function: Save the product list in File
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
                // Examine bCode
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
     * Function: Search product according to bCode
     * Check if bCode is existed in the list or not
     * If yes, print it out on the screen
     * If not, request to input again
     */
    public void runSelectionSearchId() {
        String bCode = null;
        Node target = new Node();
        do {
            outFilePS.println("Input desired ID number (bCode): ");
            outConsolePS.println("Input desired Id number (bCode): ");
            bCode = sc.next();
            if (!isBCodeExisted(bCode)) {
                outFilePS.println("This BCode is not existed. Please input again.");
                outConsolePS.println("This BCode is not existed. Please input again.");
            } else {
                target = myList.search(bCode);
                outFilePS.println("The product you are looking for: ");
                outConsolePS.println("The product you are looking for: ");
                outFilePS.println(target.getInfo().toString());
                outConsolePS.println(target.getInfo().toString());
            }
        } while (!isBCodeExisted(bCode));
    }

    /**
     * Function: Delete product according to bCode
     * If product existed, show product's information and delete from the list
     * If not, request to input again
     */
    public void runSelectionDeleteId() {
        String bCode = null;
        Node target = new Node();
        // Find bCode of product that needed to be deleted
        do {
            outFilePS.println("Input the bCode to delete = ");
            outConsolePS.println("Input the bCode to delete = ");
            bCode = sc.next();
            if (!isBCodeExisted(bCode)) {
                outFilePS.println("BCode is not existed. Please input again.");
                outConsolePS.println("BCode is not existed. Please input again.");
            }
        } while (!isBCodeExisted(bCode));

        // Find the position of Product in linked List to delete
        target = myList.search(bCode);
        int position = -1;
        for (int i = 0; i < myList.length(); i++) {
            if (myList.getNode(i) == target) {
                position = i;
            }
        }
        outFilePS.println("Product needed to be deleted: ");
        outConsolePS.println("Product needed to be deleted: ");
        outFilePS.println(target.getInfo().toString());
        outConsolePS.println(target.getInfo().toString());
        myList.delete(position);
        outFilePS.println("Deleted!");
        outConsolePS.println("Deleted!");
    }

    /**
     * Function: Sort product according to bCode
     * Sort bCode in Array String and use Insertion Sort
     * Find product's Node according to Array String -> Separate Node from old myList
     * Bring Node in new sortedList
     * Change sortedList to myList
     */
    public void runSelectionSortId() {
        outFilePS.println("Sort product according to ID.");
        outConsolePS.println("Sort product according to ID.");
        // Create Array
        String[] bcodeArr = new String[myList.length()];
        // Bring bCode in Array String and sort
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

        //Create new sorted List
        MyList sortedList = new MyList();

        // Bring String information in sortedList to find Node in myList
        for (int i = 0; i < bcodeArr.length; i++) {
            Node result2 = myList.search(bcodeArr[i]);
            //Delete Node result from myList for result2 becoming separate Node
            myList.deleteNode(result2);
            // Add Node result2 into Sorted List
            sortedList.add(result2);
        }
        outFilePS.println("Completed Sorted list: ");
        outConsolePS.println("Completed Sorted list: ");
        sortedList.printLL();
        // Turn myList to sortedList
        myList = sortedList;
    }

    /**
     * Function: Convert the quantity to Binary
     */
    public void runSelectionConvertBinary() {
        outFilePS.println("Product's quantity: " + myList.length());
        outConsolePS.println("Product's quantity: " + myList.length());
        outFilePS.println("Product's quantity in Binary: " + Util.stringConvertVersion2(myList.length()));
        outConsolePS.println("Product's quantity in Binary: " + Util.stringConvertVersion2(myList.length()));
    }


    /**
     * Function: Read data from file and save in Stack
     * Examine file:
     * If existed, read data from file
     * If not, cancel
     * After finished reading, check condition:
     * If legit, save to Product, save Product in Node, save Node in myStack
     * If not, do not save
     * After finished, print results on the screen
     */
    public void runSelectionLoadStack() {
        outFilePS.println("Read data from file and save to Stack: ");
        outConsolePS.println("Read data from file and save to Stack: ");
        try {
            // Read file
            File file = new File("data.txt");

            Scanner fileSc = new Scanner(file);
            fileSc.nextLine();
            fileSc.nextLine();
            while (fileSc.hasNextLine()) {
                // Save product's information in Stack List
                // Cut and trim desired String contains product's information

                String data = fileSc.nextLine();
                String[] part = data.split("\\|");
                String bCode = part[0].trim();
                String title = part[1].trim();
                int quantity = Integer.parseInt(part[2].trim());
                double price = Double.parseDouble(part[3].trim());
                // Examine element's condition, if all are legit -> save in product
                if (isBCodeValid(bCode)) {
                    // Want: Put product's information in Product
                    Product product = new Product(bCode, title, quantity, price);
                    // Put product in Node
                    Node node = new Node(product);
                    // Put Node in stacked List
                    myStack.push(node);
                }
            }
        } catch (FileNotFoundException e) {
            outFilePS.println("Cannot find the file.");
            outConsolePS.println("Cannot find the file.");
        }
        outFilePS.println("Read data from file completed.");
        outConsolePS.println("Read data from file completed.");
        outFilePS.println("Information in Stack: ");
        outConsolePS.println("Information in stack: ");
        myStack.print();
    }

    /**
     * Function: Read data from File and save in Queue
     * Examine file:
     * If File existed, read data from File
     * If not, cancel
     * After finished reading data, check condition:
     * If legit, save in product, save product in Node, save Node in myQueue
     * If not, do not save
     * After finished, print results on the screen
     */
    public void runSelectionLoadQueue() {
        outFilePS.println("Read data from File and save in Queue: ");
        outConsolePS.println("Read data from File and save in Queue: ");
        try {
            // Read file
            File file = new File("data.txt");

            Scanner fileSc = new Scanner(file);
            fileSc.nextLine();
            fileSc.nextLine();
            while (fileSc.hasNextLine()) {
                // Save product's information in Stack list
                // Cut and trim desired String contains product's information

                String data = fileSc.nextLine();
                String[] part = data.split("\\|");
                String bCode = part[0].trim();
                String title = part[1].trim();
                int quantity = Integer.parseInt(part[2].trim());
                double price = Double.parseDouble(part[3].trim());
                // Examine condition of each element, if all are legit => save in product
                if (isBCodeValid(bCode)) {
                    // Want: Put product's information in product
                    Product product = new Product(bCode, title, quantity, price);
                    // Put product in Node
                    Node node = new Node(product);
                    // Put node in stacked List
                    myQueue.enqueue(node);
                }
            }
        } catch (FileNotFoundException e) {
            outConsolePS.println("Cannot find the file.");
            outFilePS.println("Cannot find the file.");
        }
        outConsolePS.println("Read data from file completed.");
        outFilePS.println("Read data from file completed.");
        outConsolePS.println("Information in Queue: ");
        outFilePS.println("Information in Queue: ");
        myQueue.print();
    }
}
