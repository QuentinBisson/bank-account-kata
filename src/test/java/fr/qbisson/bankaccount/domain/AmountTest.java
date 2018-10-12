package fr.qbisson.bankaccount.domain;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test
    void zeroAmountIsCached() {
        assertSame(Amount.of(0), Amount.empty());
    }

    @Test
    void amountIsAlwaysPositiveCached() {
        assertThrows(IllegalArgumentException.class, () -> Amount.of(-1));
    }

    @Test
    void plusAddsAmount() {
        assertEquals(Amount.of(3), Amount.of(1).plus(Amount.of(2)));
    }

    @Test
    void minusRemovesAmount() {
        assertEquals(Amount.of(2), Amount.of(3).minus(Amount.of(1)));
    }

    @Test
    void minusRemovesAmountThrowsExceptionIfBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> Amount.of(3).minus(Amount.of(4)));
    }

    @Test
    void isLesserThan() {
        assertTrue(Amount.of(2).isLesserThan(Amount.of(3)));
        assertFalse(Amount.of(2).isLesserThan(Amount.of(2)));
        assertFalse(Amount.of(2).isLesserThan(Amount.of(1)));
    }

    @Test
    void toStringPrintsCurrencyFormattedValue() {
        Locale.setDefault(Locale.FRANCE);
        assertEquals("20,00 €", Amount.of(20).toString());
        assertEquals("10,50 €", Amount.of(10.5).toString());
    }


}