package site.danielszczesny.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.danielszczesny.backend.model.Account;
import site.danielszczesny.backend.model.timofinance.ChargeType;
import site.danielszczesny.backend.model.timofinance.IncomeType;
import site.danielszczesny.backend.model.timofinance.TimePeriods;
import site.danielszczesny.backend.model.timofinance.Record;
import site.danielszczesny.backend.repository.RecordRepository;

@Service
public class RecordService {

    private RecordRepository recordRepository;
    private AccountService accountService;

    @Autowired
    public RecordService(RecordRepository recordRepository, AccountService accountService) {
        this.recordRepository = recordRepository;
        this.accountService = accountService;
    }

    public void save(Account account, IncomeType incomeType, float amount, TimePeriods period) {
        Record recordToSave;

        recordToSave = new Record(account.getId(), incomeType, amount, period);

        recordRepository.save(recordToSave);
    }

    public void save(Account account, ChargeType chargeType, float amount, TimePeriods period) {
        Record recordToSave;

        recordToSave = new Record(account.getId(), chargeType, amount, period);

        recordRepository.save(recordToSave);
    }

    public long update(long id, Record record) {
        Record toUpdate = recordRepository.getOne(id);

        toUpdate.setCharge(null);
        toUpdate.setIncome(null);
        toUpdate.setId(record.getId());
        toUpdate.setAmount(record.getAmount());
        toUpdate.setUserId(record.getUserId());
        toUpdate.setPeriod(record.getPeriod());
        toUpdate.setIncome(record.getIncome());
        toUpdate.setCharge(record.getCharge());

        recordRepository.save(toUpdate);
        return toUpdate.getId();
    }

    public long delete(long id) {
        recordRepository.delete(recordRepository.getOne(id));
        return id;
    }

    public Account getAccountByUsername(String username) {
        if (accountService.usernameExist(username)) {
            return accountService.getAccountByUsername(username);
        } else {
            accountService.save(username);
            getAccountByUsername(username);
            return new Account();
        }
    }
}
