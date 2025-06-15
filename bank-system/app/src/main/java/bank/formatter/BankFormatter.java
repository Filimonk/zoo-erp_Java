package bank.formatter;

import bank.domain.model.*;

public class BankFormatter {
    public static String format(BankAccount account) {
        return String.format("ID: %s | Название: %s | Баланс: %.2f",
                account.getId(), account.getName(), account.getBalance());
    }

    public static String format(Category category) {
        return String.format("ID: %s | Тип: %s | Название: %s",
                category.getId(), category.getType(), category.getName());
    }

    public static String format(Operation operation) {
        return String.format("ID: %s | Тип: %s | Счет: %s | Сумма: %.2f | Дата: %s | Описание: %s | Категория: %s",
                operation.getId(), operation.getType(), operation.getBankAccountId(),
                operation.getAmount(), operation.getDate(), operation.getDescription(), operation.getCategoryId());
    }
}

