package bank.domain.model;

import java.time.*;

import bank.domain.OperationType;

public class Operation {
    private final String id;
    private final OperationType type;
    private final String bankAccountId;
    private final double amount;
    private final LocalDate date;
    private final String description;
    private final String categoryId;

    public Operation(String id, OperationType type, String bankAccountId, double amount,
                     LocalDate date, String description, String categoryId) {
        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public OperationType getType() {
        return type;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryId() {
        return categoryId;
    }
}

