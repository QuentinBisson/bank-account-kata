package fr.qbisson.bankaccount.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

public final class Amount {
    private static final Amount EMPTY = new Amount(BigDecimal.ZERO);

    private final BigDecimal value;
    private Amount(BigDecimal value) {
        this.value = value;
    }

    public static Amount of(double value) {
        return of(new BigDecimal(value));
    }

    public static Amount of(BigDecimal value) {
        // Optimisation for empty amount objects
        if (BigDecimal.ZERO.compareTo(value) == 0) {
            return EMPTY;
        }

        if (BigDecimal.ZERO.compareTo(value) > 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        return new Amount(value);
    }

    static Amount empty() {
        return EMPTY;
    }

    Amount plus(Amount amount) {
        return new Amount(this.value.add(amount.value));
    }

    Amount minus(Amount amount) {
        BigDecimal newAmount = this.value.subtract(amount.value);
        if (BigDecimal.ZERO.compareTo(newAmount) > 0) {
            throw new IllegalArgumentException("Amount cannot be below zero");
        }
        return new Amount(newAmount);
    }

    boolean isLesserThan(Amount amountToWithdraw) {
        return this.value.compareTo(amountToWithdraw.value) < 0;
    }

    @Override
    public String toString() {
        return NumberFormat.getCurrencyInstance().format(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Amount amount = (Amount) o;
        return value.compareTo(amount.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
