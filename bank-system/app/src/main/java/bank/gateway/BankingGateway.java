package bank.gateway;

import java.time.LocalDate;
import java.util.*;

import bank.service.api.*;
import bank.domain.model.*;
import bank.domain.OperationType;

public class BankingGateway {
    private final BankAccountService bankAccountService;
    private final CategoryService categoryService;
    private final OperationService operationService;

    public BankingGateway(BankAccountService bankAccountService,
                          CategoryService categoryService,
                          OperationService operationService) {
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;
    }

    // Работа с банковскими счетами
    public BankAccount createBankAccount(String name, double balance) {
        return bankAccountService.createAccount(name, balance);
    }

    public BankAccount createBankAccount(String name) {
        return bankAccountService.createAccount(name, 0);
    }

    public void deleteBankAccount(String id) {
        bankAccountService.deleteAccount(id);
    }

    public BankAccount getBankAccountById(String id) {
        return bankAccountService.getAccountById(id);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllAccounts();
    }

    // Работа с категориями
    public Category createCategory(OperationType type, String name) {
        return categoryService.createCategory(type, name);
    }

    public void deleteCategory(String id) {
        categoryService.deleteCategory(id);
    }

    public Category getCategoryById(String id) {
        return categoryService.getCategoryById(id);
    }

    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Работа с операциями
    public Operation createOperation(String accountId, double amount, LocalDate date,
                                     String description, String categoryId) {
        BankAccount account = bankAccountService.getAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Счет с таким ID не найден");
        }

        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            throw new IllegalArgumentException("Категория с таким ID не найдена");
        }

        OperationType type = category.getType();

        Operation operation = operationService.createOperation(type, accountId, amount, date,
                                                               description, categoryId);
        double delta = (type == OperationType.INCOME? amount : -amount);
        account.updateBalance(delta);

        return operation;
    }

    public Operation createOperation(String accountId, double amount, LocalDate date, String categoryId) {
        return createOperation(accountId, amount, date, "", categoryId);
    }

    public void deleteOperation(String id) {
        operationService.deleteOperation(id);
    }

    public Operation getOperationById(String id) {
        return operationService.getOperationById(id);
    }

    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }
}

