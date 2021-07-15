package site.danielszczesny.backend.model.timofinance;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "timofinance_data")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long userId;

    @Column
    private boolean type; //false = income ; true = charge

    @Column
    private IncomeType income;

    @Column
    private ChargeType charge;

    @Column(nullable = false, precision = 2)
    private float amount;

    public Record() {}

    public Record(boolean type, long userId, IncomeType income, float amount) {
        this.type = type;
        this.userId = userId;
        this.income = income;
        this.amount = amount;
    }

    public Record(boolean type, long userId, ChargeType charge, float amount) {
        this.type = type;
        this.userId = userId;
        this.charge = charge;
        this.amount = amount;
    }

    public Record(long id, boolean type, long userId, IncomeType income, ChargeType charge, float amount) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.income = income;
        this.charge = charge;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public IncomeType getIncome() {
        return income;
    }

    public void setIncome(IncomeType income) {
        this.income = income;
    }

    public ChargeType getCharge() {
        return charge;
    }

    public void setCharge(ChargeType charge) {
        this.charge = charge;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
