package payments.domain;

import java.util.UUID;
import java.math.BigDecimal;

public class Account {
    private final UUID accountId;
    private final String username;
    private final BigDecimal balance;

    public Account(UUID accountId, String username, BigDecimal balance) {
        this.accountId = accountId;
        this.username = username;
        this.balance = balance;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}

