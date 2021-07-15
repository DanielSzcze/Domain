package site.danielszczesny.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.danielszczesny.backend.model.Account;
import site.danielszczesny.backend.model.timofinance.ChargeType;
import site.danielszczesny.backend.model.timofinance.IncomeType;
import site.danielszczesny.backend.model.timofinance.Record;
import site.danielszczesny.backend.repository.RecordRepository;

import java.util.Set;

@Service
public class RecordService {

    private RecordRepository recordRepository;
    private AccountService accountService;

    @Autowired
    public RecordService(RecordRepository recordRepository, AccountService accountService) {
        this.recordRepository = recordRepository;
        this.accountService = accountService;
    }

    public void save(Account account, boolean type, IncomeType incomeType, ChargeType chargeType, float amount) {
        Record recordToSave;
        if (!type) {
            recordToSave = new Record(type, account.getId(), incomeType, amount);
        } else {
            recordToSave = new Record(type, account.getId(), chargeType, amount);
        }

        recordRepository.save(recordToSave);
    }

    public long update(long id, Record record) {
        Record toUpdate = recordRepository.getOne(id);
        if (!record.isType()) {
            toUpdate.setId(record.getId());
            toUpdate.setAmount(record.getAmount());
            toUpdate.setType(record.isType());
            toUpdate.setIncome(record.getIncome());
            toUpdate.setCharge(null);
            toUpdate.setUserId(record.getUserId());
        } else {
            toUpdate.setId(record.getId());
            toUpdate.setAmount(record.getAmount());
            toUpdate.setType(record.isType());
            toUpdate.setIncome(null);
            toUpdate.setCharge(record.getCharge());
            toUpdate.setUserId(record.getUserId());
        }
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
