package com.cdc.withdraw;

import com.cdc.InsufficientFundException;
import com.cdc.account.Account;
import com.cdc.account.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WithdrawServiceImpl implements WithdrawService {

    private AccountDAO accountDAO;
    private WithdrawDAO withdrawDAO;

    @Autowired
    public WithdrawServiceImpl(AccountDAO accountDAO, WithdrawDAO withdrawDAO) {
        this.accountDAO = accountDAO;
        this.withdrawDAO = withdrawDAO;
    }

    @Override
    @Transactional(readOnly = false)
    public Withdraw saveWithdraw(String idAccount, BigDecimal amount)throws InsufficientFundException {
        Optional<Account> account = accountDAO.findById(idAccount);
        Withdraw withdraw = Withdraw.builder().transactionDate(new Date())
                .account(account.get())
                .amount(amount)
                .build();
        if(account.isPresent()){
            if(account.get().getBalance().compareTo(amount) >=0) {
                withdrawDAO.save(withdraw);
                BigDecimal balance = account.get().getBalance().subtract(amount);
                Account acc = account.get();
                acc.setBalance(balance);
                accountDAO.save(acc);
            }else {
                throw new InsufficientFundException("Insufficent Balance $"+amount );
            }
        }
        return withdraw;
    }

    @Override
    public Withdraw findById(String idWitdraw) {
        return withdrawDAO.findById(idWitdraw).get();
    }

    @Override
    public Account findAccountById(String idAccount) {
        return accountDAO.findById(idAccount).get();
    }
}
