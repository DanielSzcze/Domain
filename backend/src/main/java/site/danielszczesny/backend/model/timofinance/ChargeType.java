package site.danielszczesny.backend.model.timofinance;

public enum ChargeType implements Type{
    EMPTY(0),
    BILL(1),
    FOOD(2),
    GOODS(3),
    CREDIT(4),
    SERVICES(5),
    RECREATION(6);

    private final int number;

    ChargeType(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
