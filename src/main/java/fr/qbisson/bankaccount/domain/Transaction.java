package fr.qbisson.bankaccount.domain;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Interface defining what an account transaction is (Deposit, Withdrawal).
 */
@FunctionalInterface
public interface Transaction {
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

    /**
     * Apply the transaction to the balance
     * @return The new balance when the transaction is applied
     */
    Amount balance(Amount balance);
}
