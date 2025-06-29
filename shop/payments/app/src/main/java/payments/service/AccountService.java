package payments.service;

import java.sql.*;
import java.util.UUID;
import java.math.BigDecimal;
import payments.domain.Account;

public class AccountService {
    private final Connection connection;

    public AccountService() {
        try {
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            if (dbHost == null || dbPort == null || dbName == null || dbUser == null || dbPassword == null)
                throw new RuntimeException("Не указаны параметры БД");

            String url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
            this.connection = DriverManager.getConnection(url, dbUser, dbPassword);
            createAccountsTable();
        } catch (SQLException | RuntimeException e) {
            System.err.println("Ошибка инициализации AccountService: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void createAccountsTable() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "\"account_id\" UUID PRIMARY KEY, " +
                    "\"username\" VARCHAR(50) NOT NULL UNIQUE, " +
                    "\"balance\" NUMERIC(20, 2) NOT NULL DEFAULT 0" +
                    ")";
            stmt.execute(sql);
            System.out.println("Таблица accounts готова");
        } catch (SQLException e) {
            System.err.println("Ошибка создания таблицы: " + e.getMessage());
        }
    }

    public Account createAccount(String username) {
        try {
            if (usernameExists(username))
                return null;

            UUID accountId = UUID.randomUUID();
            String sql = "INSERT INTO accounts (\"account_id\", \"username\", \"balance\") VALUES (?, ?, 0)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setObject(1, accountId);
                stmt.setString(2, username);
                stmt.executeUpdate();
            }
            return getAccountByUsername(username); // Вернем уже с балансом
        } catch (SQLException e) {
            System.err.println("Ошибка при создании аккаунта: " + e.getMessage());
            return null;
        }
    }

    private boolean usernameExists(String username) throws SQLException {
        String sql = "SELECT 1 FROM accounts WHERE \"username\" = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Account getAccountByUsername(String username) {
        String sql = "SELECT \"account_id\", \"username\", \"balance\" FROM accounts WHERE \"username\" = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UUID accountId = (UUID) rs.getObject("account_id");
                    String uname = rs.getString("username");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    return new Account(accountId, uname, balance);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка getAccountByUsername: " + e.getMessage());
        }
        return null;
    }
}

