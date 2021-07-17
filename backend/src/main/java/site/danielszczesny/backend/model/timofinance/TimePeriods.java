package site.danielszczesny.backend.model.timofinance;

public enum TimePeriods {
    EMPTY(0),
    ONCE(1),
    MONTHLY(2),
    BIMONTHLY(3),
    QUARTERLY(4),
    SEMIANNUALLY(5),
    ANNUALLY(6);

    private int number;

    TimePeriods(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
