package bank.domain.model;

import bank.domain.OperationType;

public class Category {
    private final String id;
    private final OperationType type;
    private final String name;

    public Category(String id, OperationType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public OperationType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

