package fr.qbisson.bankaccount;

import fr.qbisson.bankaccount.domain.Amount;
import fr.qbisson.bankaccount.domain.BankAccount;

import java.time.LocalDateTime;

public class BankAccountApplication {
    public static void main(String[] args) {
        var account = BankAccount.of();

        account.deposit(Amount.of(22.4), LocalDateTime.now());
        account.deposit(Amount.of(10.4), LocalDateTime.now());
        account.withdraw(Amount.of(20), LocalDateTime.now());
        account.deposit(Amount.of(5.1), LocalDateTime.now());

        System.out.println(account.formatHistory());
    }
}
