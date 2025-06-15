package bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bank.service.api.*;
import bank.service.impl.*;
import bank.domain.factory.*;
import bank.gateway.*;
import bank.formatter.*;

@Configuration
public class AppConfig {

    @Bean
    public BankAccountFactory bankAccountFactory() {
        return new BankAccountFactory();
    }

    @Bean
    public CategoryFactory categoryFactory() {
        return new CategoryFactory();
    }

    @Bean
    public OperationFactory operationFactory() {
        return new OperationFactory();
    }

    @Bean
    public BankAccountService bankAccountService() {
        return new BankAccountServiceImpl(bankAccountFactory());
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryFactory());
    }

    @Bean
    public OperationService operationService() {
        return new OperationServiceImpl(operationFactory());
    }

    @Bean
    public BankingGateway bankingGateway() {
        return new BankingGateway(bankAccountService(), categoryService(), operationService());
    }

    @Bean
    public BankFormatter bankFormatter() {
        return new BankFormatter();
    }
}

