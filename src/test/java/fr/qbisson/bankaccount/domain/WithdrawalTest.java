package fr.qbisson.bankaccount.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawalTest {

    @Test
    void ofAcceptsNonNullAmountOnly() {
        assertThrows(IllegalArgumentException.class, () -> Withdrawal.of(null, null));
    }

    @Test
    void ofAcceptsNonNullTimestampOnly() {
        assertThrows(IllegalArgumentException.class, () -> Withdrawal.of(Amount.of(1), null));
    }

    @Test
    void balanceAddAmountToBalance() {
        Withdrawal withdrawal = Withdrawal.of(Amount.of(1.5), LocalDateTime.now());

        assertEquals(Amount.of(7.0), withdrawal.balance(Amount.of(8.5)));
    }

    @Test
    void toStringFormatsAsExpected() {
        var now = LocalDateTime.now();
        var withdrawal = Withdrawal.of(Amount.of(BigDecimal.valueOf(7.5)), now);

        assertEquals(
                Transaction.DATE_TIME_FORMATTER.format(now) + " | Withdrawal | 7,50 €",
                withdrawal.toString());
    }
}