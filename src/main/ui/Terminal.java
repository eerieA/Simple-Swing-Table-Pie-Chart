package ui;

import model.ListOfStocks;
import model.Stock;

import java.util.Scanner;

public class Terminal {
    private Scanner input;
    private ListOfStocks listOfStocks;

    public Terminal() {
        runTerminal();
    }

    private void runTerminal() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add a stock");
        System.out.println("\ts -> show recorded stocks");
        System.out.println("\tu -> update a stock");
        System.out.println("\td -> delete a stock");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddStock();
        } else if (command.equals("s")) {
            doShowAllStocks();
        } else if (command.equals("u")) {
            doUpdateAStock();
        } else if (command.equals("d")) {
            doDeleteAStock();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doAddStock() {
        Stock selected;

        System.out.print("Enter name:");
        String inputName = input.next();
        System.out.print("Enter unit price (>0):");
        int inputUnitPrice = input.nextInt();
        System.out.print("Enter invested amount (>0):");
        int inputInvestAmt = input.nextInt();
        System.out.print("Enter invested year (1900-9999):");
        int inputYear = input.nextInt();
        System.out.print("Enter invested month (1-12):");
        int inputMonth = input.nextInt();

        selected = new Stock(inputName, inputUnitPrice, inputInvestAmt, inputYear, inputMonth);
        listOfStocks.addStock(selected);

        System.out.println("Congratulations! You recorded a new investment item:");
        printAStock(selected);
    }

    private void doShowAllStocks() {
        System.out.println("");
        System.out.println("Here are your currently recorded investment items:");
        for (Stock st : listOfStocks.getStocks()) {
            printAStock(st);
        }
    }

    private void doUpdateAStock() {
        Stock selected;
        // Make displayed upper limit to index 0, if getStocks().size() is 0
        int maxIndex = ((listOfStocks.getStocks().size() - 1) < 0) ? 0 : (listOfStocks.getStocks().size() - 1);

        System.out.println("");
        System.out.println("Please select the index of the item needing update (current index range is 0 - "
                + maxIndex + "):");
        int index = input.nextInt();

        if ((index >= 0) && (index <= (listOfStocks.getStocks().size() - 1))) {
            selected = listOfStocks.getStocks().get(index);

            System.out.print("Enter name (press Enter if no change):");
            String inputName = input.next();
            System.out.print("Enter unit price (input -1 if no change):");
            int inputUnitPrice = input.nextInt();
            System.out.print("Enter invested amount (input -1 if no change):");
            int inputInvestAmt = input.nextInt();
            System.out.print("Enter invested year (input -1 if no change):");
            int inputYear = input.nextInt();
            System.out.print("Enter invested month (input -1 if no change):");
            int inputMonth = input.nextInt();
            selected.update(inputName, inputUnitPrice, inputInvestAmt, inputYear, inputMonth);

            printAStock(selected);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doDeleteAStock() {
        Stock selected;
        // Make displayed upper limit to index 0, if getStocks().size() is 0
        int maxIndex = ((listOfStocks.getStocks().size() - 1) < 0) ? 0 : (listOfStocks.getStocks().size() - 1);

        System.out.println("");
        if ((listOfStocks.getStocks().size() > 0)) {
            System.out.println("Please select the index of the item to delete (current index range is 0 - "
                    + maxIndex + "):");
            int index = input.nextInt();
            if ((index >= 0) && (index <= (listOfStocks.getStocks().size() - 1))) {
                selected = listOfStocks.getStocks().get(index);
                listOfStocks.deleteStock(index);
                System.out.println(selected.getName() + "has been deleted.");
            } else {
                System.out.println("Selected index is not valid...");
            }
        } else {
            System.out.println("Sorry, there is no item to delete in the list.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner and an empty ListOfStock
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        listOfStocks = new ListOfStocks();
    }

    private void printAStock(Stock st) {
        String str = "Name: " + st.getName() + ", "
                + "price $: " + st.getUnitPrice() + ", "
                + "invested $: " + st.getInvestedAmount() + ", "
                + "purchase time: " + st.getTime() + ", "
                + "shares: " + st.getShares() + ".";
        System.out.println(str);
    }
}
