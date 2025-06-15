package bank.service.impl;

import java.util.*;

import bank.domain.model.BankAccount;
import bank.domain.factory.BankAccountFactory;
import bank.service.api.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {
    private final Map<String, BankAccount> accounts = new HashMap<>();
    private final BankAccountFactory factory;

    public BankAccountServiceImpl(BankAccountFactory factory) {
        this.factory = factory;
    }

    @Override
    public BankAccount createAccount(String name, double balance) {
        BankAccount account = factory.create(name, balance);
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public void deleteAccount(String id) {
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Счет с таким ID не найден");
        }
        accounts.remove(id);
    }

    @Override
    public BankAccount getAccountById(String id) {
        BankAccount account = accounts.get(id);
        if (account == null) {
            throw new IllegalArgumentException("Счет с таким ID не найден");
        }
        return account;
    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public void updateBalance(String id, double amount) {
        BankAccount account = getAccountById(id);
        account.updateBalance(amount);
    }
}

