package site.danielszczesny.backend.model.timofinance;

public enum IncomeType implements Type{
    EMPTY(0),
    SALARY(1),
    OVERTIME(2),
    INVESTMENTS(3),
    LOAN(4),
    OTHER(5);

    private int number;

    IncomeType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }
}
