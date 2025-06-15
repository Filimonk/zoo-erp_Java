package bank.domain.factory;

import java.util.*;

import bank.domain.model.BankAccount;

public class BankAccountFactory {
    public BankAccount create(String name, double balance) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя счета не может быть пустым");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Первоначальный баланс не может быть отрицательным");
        }
        return new BankAccount(UUID.randomUUID().toString(), name, balance);
    }
}

