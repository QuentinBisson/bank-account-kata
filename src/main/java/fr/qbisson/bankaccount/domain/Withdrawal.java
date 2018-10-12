package fr.qbisson.bankaccount.domain;

import java.time.LocalDateTime;

/**
 * This class models a Withdrawal transaction in an account.
 */
public final class Withdrawal implements Transaction {
    private final Amount amount;
    private final LocalDateTime timestamp;

    private Withdrawal(Amount amount, LocalDateTime timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    /**
     * Create a withdrawal transaction.
     * @param amount The amount to withdraw
     * @param timestamp The withdrawal date
     * @return The withdrawal transaction
     */
    static Withdrawal of(Amount amount, LocalDateTime timestamp) {
        if (amount == null || timestamp == null) {
            throw new IllegalArgumentException("Amount or timestamp is null");
        }
        return new Withdrawal(amount, timestamp);
    }

    @Override
    public Amount balance(Amount balance) {
        return balance.minus(amount);
    }

    @Override
    public String toString() {
        return DATE_TIME_FORMATTER.format(timestamp) + " | Withdrawal | " + amount.toString();
    }
}
