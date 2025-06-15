package bank.domain.factory;

import java.util.*;
import java.time.*;

import bank.domain.OperationType;
import bank.domain.model.Operation;

public class OperationFactory {
    public Operation create(OperationType type, String bankAccountId, double amount,
                            LocalDate date, String description, String categoryId) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма операции должна быть положительной");
        }
        return new Operation(UUID.randomUUID().toString(), type, bankAccountId, amount,
                             date, description, categoryId);
    }
    
    public Operation create(OperationType type, String bankAccountId, double amount,
                            LocalDate date, String categoryId) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма операции должна быть положительной");
        }
        return new Operation(UUID.randomUUID().toString(), type, bankAccountId, amount, date, "", categoryId);
    }
}

