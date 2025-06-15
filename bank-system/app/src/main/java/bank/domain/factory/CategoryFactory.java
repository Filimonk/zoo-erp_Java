package bank.domain.factory;

import java.util.*;

import bank.domain.OperationType;
import bank.domain.model.Category;

public class CategoryFactory {
    public Category create(OperationType type, String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя категории не может быть пустым");
        }
        return new Category(UUID.randomUUID().toString(), type, name);
    }
}

