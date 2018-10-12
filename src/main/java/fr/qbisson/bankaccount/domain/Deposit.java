package fr.qbisson.bankaccount.domain;

import java.time.LocalDateTime;

/**
 * This class models a Deposit transaction in an account.
 */
public final class Deposit implements Transaction {
    private final Amount amount;
    private final LocalDateTime timestamp;

    private Deposit(Amount amount, LocalDateTime timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    /**
     * Create a deposit transaction.
     * @param amount The amount to deposit
     * @param timestamp The deposit date
     * @return The deposit transaction
     */
    static Deposit of(Amount amount, LocalDateTime timestamp) {
        if (amount == null || timestamp == null) {
            throw new IllegalArgumentException("Amount or timestamp is null");
        }
        return new Deposit(amount, timestamp);
    }

    @Override
    public Amount balance(Amount balance) {
        return balance.plus(this.amount);
    }

    public String toString() {
        return DATE_TIME_FORMATTER.format(timestamp) + " | Deposit | " + amount.toString();
    }
}
