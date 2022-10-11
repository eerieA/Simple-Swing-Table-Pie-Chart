package model;

// Represents a stock investment entry that has:
//   stock name
//   price per share, in dollars
//   invested money, in dollars
//   number of shares (calculated from price and invested money, rounded int)
//   time bought, year and month
public class Stock {
    public static final int YEAR_MIN = 1900;
    public static final int YEAR_MAX = 9999;
    public static final int MONTH_MIN = 1;
    public static final int MONTH_MAX = 12;
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
    //          name, unit price, invested amount, year of purchase, month of purchase,
    //          and perform conversions to obtain:
    //          time in string, shares in int
    public Stock(String n, int up, int ia, int year, int month) {
        this.name = n;
        this.unitPrice = up;
        this.investedAmount = ia;
        this.timeYear = year;
        this.timeMonth = month;
        setFormattedTime();
        setShares();
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
    public int update(String n, int up, int ia, int year, int month) {
        Boolean resN;
        Boolean resUp;
        Boolean resIa;
        Boolean resTime;
        //resN = resUp = resIa = resTime = false;

        resN = setName(n);
        resUp = setUnitPrice(up);
        resIa = setInvestedAmount(ia);

        resTime = setTime(month, year);
        setShares();

        // Lastly, return 1 if all fields are updated
        if (resN && resUp && resIa && resTime) {
            return 1;
        } else {
            return 0;
        }
    }

    // REQUIRES: name is not empty.
    // MODIFIES: this
    // EFFECTS: change the name of this investment item to the given string.
    public Boolean setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: unit price > 0.
    // MODIFIES: this
    // EFFECTS: change the unit price of this investment item to the given int.
    public Boolean setUnitPrice(int up) {
        if (up > 0) {
            this.unitPrice = up;
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: invested amount > 0.
    // MODIFIES: this
    // EFFECTS: change the invested amount of this investment item to the given int.
    public Boolean setInvestedAmount(int ia) {
        if (ia > 0) {
            this.investedAmount = ia;
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: year of purchase is in [1900, 9999];
    //           month of purchase is in [1, 12].
    // MODIFIES: this
    // EFFECTS: change the time of this investment item to the given int numbers,
    //          and update time string
    public Boolean setTime(int month, int year) {
        Boolean ydiff = false;
        Boolean mdiff = false;

        if (isYearInRange(year)) {
            this.timeYear = year;
            ydiff = true;
        }

        if (isMonthInRange(month)) {
            this.timeMonth = month;
            mdiff = true;
        }

        setFormattedTime();

        if (ydiff && mdiff) {
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: reformat current timeYear and timeMonth to a more readable string
    private void setFormattedTime() {
        time = Integer.toString(timeMonth) + "/" + Integer.toString(timeYear);
    }

    // MODIFIES: this
    // EFFECTS: calculate units of shares for this stock
    public void setShares() {
        shares = (int) (Math.rint((double) investedAmount / (double) unitPrice));
    }

    // EFFECTS: check if a given year value is within the set range
    private Boolean isYearInRange(int year) {
        if (year >= YEAR_MIN && year <= YEAR_MAX) {
            timeYear = year;
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: check if a given month value is within the set range
    private Boolean isMonthInRange(int month) {
        if (month >= MONTH_MIN && month <= MONTH_MAX) {
            timeMonth = month;
            return true;
        } else {
            return false;
        }
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
        return shares; //stub
    }

    // EFFECTS: return the time of purchase of this investment item.
    public String getTime() {
        return time;
    }
}
