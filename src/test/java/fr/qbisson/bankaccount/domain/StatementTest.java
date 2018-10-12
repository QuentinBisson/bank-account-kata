package fr.qbisson.bankaccount.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StatementTest {

    @Test
    void toStringReturnsExpected() {
        var localDateTime = LocalDateTime.now();
        var statement = Statement.of(Deposit.of(Amount.of(7), localDateTime), Amount.of(12));

        assertEquals(statement.toString(), Transaction.DATE_TIME_FORMATTER.format(localDateTime) + " | Deposit | 7,00 € | 12,00 €");
    }
}