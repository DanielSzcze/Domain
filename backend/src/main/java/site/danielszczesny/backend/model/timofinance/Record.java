package site.danielszczesny.backend.model.timofinance;

import org.springframework.stereotype.Component;
import site.danielszczesny.backend.model.Account;

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
    private IncomeType income;

    @Column
    private ChargeType charge;

    @Column(nullable = false, precision = 2)
    private float amount;

    @Column(nullable = false)
    private TimePeriods period;


    public Record() {}

    public Record(long userId, IncomeType income, float amount, TimePeriods period) {
        this.userId = userId;
        this.income = income;
        this.amount = amount;
        this.period = period;
    }

    public Record(long userId, ChargeType charge, float amount, TimePeriods period) {
        this.userId = userId;
        this.charge = charge;
        this.amount = amount;
        this.period = period;
    }

    public Record(long id, long userId,
                  IncomeType income, ChargeType charge, float amount, TimePeriods period) {
        this.id = id;
        this.userId = userId;
        this.income = income;
        this.charge = charge;
        this.amount = amount;
        this.period = period;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public TimePeriods getPeriod() {
        return period;
    }

    public void setPeriod(TimePeriods period) {
        this.period = period;
    }
}
