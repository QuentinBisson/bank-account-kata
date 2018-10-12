package fr.qbisson.bankaccount.domain;

public final class Statement {
    private final Transaction transaction;
    private final Amount balance;

    private Statement(Transaction transaction, Amount balance) {
        this.transaction = transaction;
        this.balance = balance;
    }

    public static Statement of(Transaction transaction, Amount balance) {
        return new Statement(transaction, balance);
    }

    public String toString() {
        return transaction.toString() + " | " + balance.toString();
    }
}
