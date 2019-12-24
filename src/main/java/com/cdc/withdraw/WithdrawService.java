package com.cdc.withdraw;

import com.cdc.InsufficientFundException;
import com.cdc.account.Account;

import java.math.BigDecimal;

public interface WithdrawService {

    Withdraw saveWithdraw(String idAccount, BigDecimal amount) throws InsufficientFundException;

    Withdraw findById(String idWitdraw);

    Account findAccountById(String idAccount);
}