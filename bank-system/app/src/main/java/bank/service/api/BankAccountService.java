package bank.service.api;

import java.util.*;

import bank.domain.model.BankAccount;

public interface BankAccountService {
    BankAccount createAccount(String name, double balance);
    void deleteAccount(String id);
    BankAccount getAccountById(String id);
    List<BankAccount> getAllAccounts();
    void updateBalance(String id, double amount);
}

