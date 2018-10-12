package fr.qbisson.bankaccount.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DepositTest {

    @Test
    void ofAcceptsNonNullAmountOnly() {
        assertThrows(IllegalArgumentException.class, () -> Deposit.of(null, null));
    }

    @Test
    void ofAcceptsNonNullTimestampOnly() {
        assertThrows(IllegalArgumentException.class, () -> Deposit.of(Amount.of(1), null));
    }

    @Test
    void balanceAddAmountToBalance() {
        Deposit deposit = Deposit.of(Amount.of(1.5), LocalDateTime.now());

        assertEquals(Amount.of(10.0), deposit.balance(Amount.of(8.5)));
    }

    @Test
    void toStringFormatsAsExpected() {
        LocalDateTime now = LocalDateTime.now();
        Deposit deposit = Deposit.of(Amount.of(BigDecimal.valueOf(7.5)), now);

        assertEquals(Transaction.DATE_TIME_FORMATTER.format(now) + " | Deposit | 7,50 €",
                deposit.toString());
    }
}