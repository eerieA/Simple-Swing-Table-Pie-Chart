package model;

// Represents a stock investment entry that has:
//   stock name
//   price per share, in dollars
//   invested money, in dollars
//   number of shares (calculated from price and invested money, rounded int)
//   time bought, year and month
public class Stock {
    private String name;
    private int unitPrice;
    private int investedAmount;
    private int shares;
    private int timeYear;
    private int timeMonth;
    private String time;

    // REQUIRES: name is not empty;
    //           unit price > 0;
    //           invested amount > 0;
    //           year of purchase is in [1900, 9999];
    //           month of purchase is in [1, 12].
    // EFFECTS: instantiate a stock with given info:
    //          name, unit price, invested amount, year of purchase, month of purchase.
    public Stock(String n, int up, int ia, int year, int month) {
        // stub
    }

    // REQUIRES: unit price > 0;
    //           invested amount > 0;
    //           year of purchase is in [1900, 9999];
    //           month of purchase is in [1, 12].
    // MODIFIES: this
    // EFFECTS: update an existing stock with given info:
    //          name, unit price, invested amount, year of purchase, month of purchase.
    //          If any field of the given inputs is a non-allowed value, don't change the existing
    //          value of that field. (For UI) Return a summary of what are changed.
    public String update(String n, int up, int ia, int year, int month) {
        return "stub";//stub
    }

    // REQUIRES: name is not empty.
    // MODIFIES: this
    // EFFECTS: change the name of this investment item to the given string.
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: unit price > 0.
    // MODIFIES: this
    // EFFECTS: change the unit price of this investment item to the given int.
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    // REQUIRES: invested amount > 0.
    // MODIFIES: this
    // EFFECTS: change the invested amount of this investment item to the given int.
    public void setInvestedAmount(int investedAmount) {
        this.investedAmount = investedAmount;
    }

    // REQUIRES: year of purchase is in [1900, 9999];
    //           month of purchase is in [1, 12].
    // MODIFIES: this
    // EFFECTS: change the time of this investment item to the given int numbers.
    public void setTime(int timeYear, int timeMonth) {
        this.timeYear = timeYear;
        this.timeMonth = timeMonth;
        //TODO: convert and format these int into a string and store it in this.time
    }

    // EFFECTS: return the name of this investment item.
    public String getName() {
        return name;
    }

    // EFFECTS: return the unit price of this investment item.
    public int getUnitPrice() {
        return unitPrice;
    }

    // EFFECTS: return the invested amount of this investment item.
    public int getInvestedAmount() {
        return investedAmount;
    }

    // EFFECTS: return the number of shares of this investment item.
    public int getShares() {
        //TODO: get calculated shares based on unit price and invested amount
        return 0; //stub
    }

    // EFFECTS: return the time of purchase of this investment item.
    public String getTime() {
        return time;
    }
}
