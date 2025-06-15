package bank.service.impl;

import java.util.*;
import java.time.*;

import bank.domain.model.Operation;
import bank.domain.factory.OperationFactory;
import bank.service.api.OperationService;
import bank.domain.OperationType;

public class OperationServiceImpl implements OperationService {
    private final Map<String, Operation> operations = new HashMap<>();
    private final OperationFactory factory;

    public OperationServiceImpl(OperationFactory factory) {
        this.factory = factory;
    }

    @Override
    public Operation createOperation(OperationType type, String bankAccountId, double amount,
                                     LocalDate date, String description, String categoryId) {
        Operation operation = factory.create(type, bankAccountId, amount, date, description, categoryId);
        operations.put(operation.getId(), operation);
        return operation;
    }

    @Override
    public Operation createOperation(OperationType type, String bankAccountId, double amount,
                                     LocalDate date, String categoryId) {
        Operation operation = factory.create(type, bankAccountId, amount, date, categoryId);
        operations.put(operation.getId(), operation);
        return operation;
    }

    @Override
    public void deleteOperation(String id) {
        if (!operations.containsKey(id)) {
            throw new NoSuchElementException("Операция с таким id не найдена");
        }
        operations.remove(id);
    }

    @Override
    public Operation getOperationById(String id) {
        Operation operation = operations.get(id);
        if (operation == null) {
            throw new NoSuchElementException("Операция с таким id не найдена");
        }
        return operation;
    }

    @Override
    public List<Operation> getAllOperations() {
        return new ArrayList<>(operations.values());
    }
}

