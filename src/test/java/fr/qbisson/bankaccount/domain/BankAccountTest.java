package fr.qbisson.bankaccount.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private static final String HISTORY_HEADER = "Date | Operation | Amount | Balance\n";

    @Test
    void depositAddsToTheBalance() {
        LocalDateTime depositDate = LocalDateTime.now();
        var account = BankAccount.of();

        account.deposit(Amount.of(22.4), depositDate);
        String expected = HISTORY_HEADER
                + Transaction.DATE_TIME_FORMATTER.format(depositDate) + " | Deposit | 22,40 € | 22,40 €";

        assertEquals(expected, account.formatHistory());
    }

    @Test
    void withdrawalRemovesFromBalance() {
        var depositDate = LocalDateTime.now();
        var withdrawalDate = LocalDateTime.now();
        var account = BankAccount.of();

        account.deposit(Amount.of(22.4), depositDate);
        account.withdraw(Amount.of(21.4), withdrawalDate);

        String expected = HISTORY_HEADER
                + Transaction.DATE_TIME_FORMATTER.format(depositDate) + " | Deposit | 22,40 € | 22,40 €\n"
                + Transaction.DATE_TIME_FORMATTER.format(withdrawalDate) + " | Withdrawal | 21,40 € | 1,00 €";

        assertEquals(expected, account.formatHistory());
    }

    @Test
    void withdrawalRejectOnInsufficientFunds() {
        var account = BankAccount.of();

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(Amount.of(1), LocalDateTime.now()));
    }


}