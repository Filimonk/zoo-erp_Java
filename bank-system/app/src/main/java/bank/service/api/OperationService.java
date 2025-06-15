package bank.service.api;

import java.time.*;
import java.util.*;

import bank.domain.model.Operation;
import bank.domain.OperationType;

public interface OperationService {
    Operation createOperation(OperationType type, String bankAccountId, double amount,
                              LocalDate date, String description, String categoryId);
    Operation createOperation(OperationType type, String bankAccountId, double amount,
                              LocalDate date, String categoryId);
    void deleteOperation(String id);
    Operation getOperationById(String id);
    List<Operation> getAllOperations();
}

