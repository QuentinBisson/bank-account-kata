package fr.qbisson.bankaccount.domain;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class models a BankAccount.
 *
 * It contains a balance and a list of ordered transactions.
 */
public final class BankAccount implements Account {

    private final List<Statement> statements;
    private Amount balance;

    private BankAccount(Amount balance) {
        this.balance = balance;
        this.statements = new LinkedList<>();
    }

    /**
     * Create an account with an empty amount
     * @return The empty account
     */
    public static Account of() {
        return new BankAccount(Amount.empty());
    }

    @Override
    public void deposit(Amount deposit, LocalDateTime depositDate) {
        var transaction = Deposit.of(deposit, depositDate);
        balance = transaction.balance(balance);

        var statement = Statement.of(transaction, balance);
        this.statements.add(statement);
    }

    @Override
    public void withdraw(Amount withdrawal, LocalDateTime withdrawalDate) {
        if (hasInsufficientFunds(withdrawal)) {
            throw new IllegalArgumentException("The account does not have sufficient funds !");
        }
        var transaction = Withdrawal.of(withdrawal, withdrawalDate);
        balance = transaction.balance(balance);

        var statement = Statement.of(transaction, balance);
        this.statements.add(statement);
    }

    /**
     * Verify the accounts has sufficient funds before withdrawing money
     * @param withdrawal The amount to withdraw
     * @return True if this balance is lesser than the amount to withdraw
     */
    private boolean hasInsufficientFunds(Amount withdrawal) {
        return balance.isLesserThan(withdrawal);
    }

    @Override
    public String formatHistory() {
        String lines = statements.stream()
                .map(Statement::toString).collect(Collectors.joining("\n"));
        return "Date | Operation | Amount | Balance\n"+ lines;
    }
}
