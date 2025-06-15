package bank;

import java.time.*;
import java.util.*;

/*
import bank.domain.factory.*;
import bank.domain.model.*;
import bank.service.api.*;
import bank.service.impl.*;
import bank.gateway.*;
import bank.formatter.*;
*/

import bank.domain.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import bank.config.AppConfig;
import bank.gateway.BankingGateway;
import bank.formatter.BankFormatter;
import bank.domain.OperationType;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BankingGateway gateway = context.getBean(BankingGateway.class);
        BankFormatter formatter = context.getBean(BankFormatter.class);
        
        /*
        BankAccountFactory bankAccountFactory = new BankAccountFactory();
        CategoryFactory categoryFactory = new CategoryFactory();
        OperationFactory operationFactory = new OperationFactory();

        BankAccountService bankAccountService = new BankAccountServiceImpl(bankAccountFactory);
        CategoryService categoryService = new CategoryServiceImpl(categoryFactory);
        OperationService operationService = new OperationServiceImpl(operationFactory);

        BankingGateway gateway = new BankingGateway(bankAccountService, categoryService, operationService);
        
        BankFormatter formatter = new BankFormatter();
        */

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Приложение начало работу!");

        while (running) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Создать банковский счет");
            System.out.println("2 - Показать все счета");
            System.out.println("3 - Создать категорию");
            System.out.println("4 - Показать все категории");
            System.out.println("5 - Создать операцию");
            System.out.println("6 - Показать все операции");
            System.out.println("0 - Выход");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Введите имя счета: ");
                        String name = scanner.nextLine();
                        System.out.print("Введите начальный баланс (можно оставить пустым): ");
                        String balanceInput = scanner.nextLine();
                        
                        BankAccount account;
                        if (balanceInput.isEmpty()) {
                            account = gateway.createBankAccount(name);
                        }
                        else {
                            double balance = Double.parseDouble(balanceInput);
                            account = gateway.createBankAccount(name, balance);
                        }
                        
                        System.out.println("Счет создан: " + formatter.format(account));
                    }
                    case "2" -> {
                        List<BankAccount> accounts = gateway.getAllBankAccounts();
                        accounts.forEach(acc -> System.out.println(formatter.format(acc)));
                    }
                    case "3" -> {
                        System.out.print("Введите тип категории (INCOME или EXPENSE): ");
                        OperationType type = OperationType.valueOf(scanner.nextLine().toUpperCase());
                        System.out.print("Введите название категории: ");
                        String categoryName = scanner.nextLine();
                        Category category = gateway.createCategory(type, categoryName);
                        System.out.println("Категория создана: " + formatter.format(category));
                    }
                    case "4" -> {
                        List<Category> categories = gateway.getAllCategories();
                        categories.forEach(cat -> System.out.println(formatter.format(cat)));
                    }
                    case "5" -> {
                        System.out.print("Введите ID счета: ");
                        String accId = scanner.nextLine();
                        System.out.print("Введите сумму: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        System.out.print("Введите описание (можно оставить пустым): ");
                        String desc = scanner.nextLine();
                        System.out.print("Введите ID категории: ");
                        String catId = scanner.nextLine();

                        Operation operation;
                        if (desc.isEmpty()) {
                            operation = gateway.createOperation(accId, amount, LocalDate.now(), catId);
                        }
                        else {
                            operation = gateway.createOperation(accId, amount, LocalDate.now(), desc, catId);
                        }
                        
                        System.out.println("Операция создана: " + formatter.format(operation));
                    }
                    case "6" -> {
                        List<Operation> operations = gateway.getAllOperations();
                        operations.forEach(op -> System.out.println(formatter.format(op)));
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Неверная команда");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        System.out.println("Работа завершена.");
    }
}

