package site.danielszczesny.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.danielszczesny.backend.model.Account;
import site.danielszczesny.backend.repository.AccountRepository;

import java.util.Arrays;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean usernameExist(String username) {
        return accountRepository.getAccountByUsername(username) != null;
    }

    public void save(String username) {
        Account accountToSave = new Account();
        accountToSave.setUsername(username);
        accountRepository.save(accountToSave);
    }

    public String[] getChestArrayByUsername(String username) {
        Account account = accountRepository.getAccountByUsername(username);
        String array = account.getChestArray();
        array = array.substring(1, array.length() - 1);
        return array.split(", ");
    }

    public int changeChestState(String username, int id) {
        String[] chestArray = getChestArrayByUsername(username);

        String actualValue = chestArray[id - 1];

        if (actualValue.equals("0")) chestArray[id - 1] = "1";
        else chestArray[id - 1] = "0";

        update(username, chestArray);

        return Integer.parseInt(chestArray[id - 1]);
    }

    private void update(String username, String[] chestArray) {
        Account account = accountRepository.getAccountByUsername(username);

        account.setChestArray(Arrays.toString(chestArray));
        accountRepository.save(account);
    }
}
