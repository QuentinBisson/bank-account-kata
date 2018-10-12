package fr.qbisson.bankaccount.domain;

import java.time.LocalDateTime;

public interface Account {

    /**
     * Deposit money to this account.
     *
     * @param amountToDeposit The amount to deposit in this account
     * @param depositDate The date of the deposit
     * @throws IllegalArgumentException if the amount to deposit is null
     * @throws IllegalArgumentException if deposit date is null
     */
    void deposit(Amount amountToDeposit, LocalDateTime depositDate);

    /**
     * Withdraw money from this account.
     *
     * @param amountToWithdraw The amount to withdraw from the account
     * @param withdrawalDate The date of the withdrawal
     * @throws IllegalArgumentException if the amount to withdraw is null
     * @throws IllegalArgumentException if withdrawal date is null
     * @throws IllegalArgumentException if the amount to withdraw is greater than the current balance
     */
    void withdraw(Amount amountToWithdraw, LocalDateTime withdrawalDate);

    /**
     * Print the account history
     */
    String formatHistory();
}
