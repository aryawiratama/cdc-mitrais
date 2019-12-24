package com.cdc.transfer;

import com.cdc.AccountNotFoundException;
import com.cdc.InsufficientFundException;
import com.cdc.account.Account;
import com.cdc.account.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FundTransferServiceImpl implements FundTransferService{

    private AccountDAO accountDAO;
    private FundTransferDAO fundTransferDAO;

    @Autowired
    public FundTransferServiceImpl(AccountDAO accountDAO, FundTransferDAO fundTransferDAO) {
        this.accountDAO = accountDAO;
        this.fundTransferDAO = fundTransferDAO;
    }

    @Override
    @Transactional(readOnly = false)
    public FundTransfer saveTransfer(FundTransferWrapper wrapper) throws Exception {
        Optional<Account> accountFrom = accountDAO.findById(wrapper.getAccountFrom());
        Optional<Account> accountTo = accountDAO.findAccountByAccountNumber(wrapper.getAccountTo());
        if(!accountFrom.get().getAccountNumber().equalsIgnoreCase(accountTo.get().getAccountNumber())){
            if(accountFrom.get().getBalance().compareTo(wrapper.getAmount()) >=0){
                FundTransfer fundTransfer = FundTransfer.builder()
                        .accountFrom(accountFrom.get())
                        .accountTo(accountTo.get())
                        .amount(wrapper.getAmount())
                        .referenceNumber(wrapper.getReferenceNumber())
                        .transactionDate(new Date())
                        .build();
                BigDecimal accountFromBalance = accountFrom.get().getBalance().subtract(wrapper.getAmount());
                BigDecimal accountToBalance = accountFrom.get().getBalance().add(wrapper.getAmount());
                Account accFrom = accountFrom.get();
                Account accTo = accountTo.get();
                accFrom.setBalance(accountFromBalance);
                accTo.setBalance(accountToBalance);
                accountDAO.save(accFrom);
                accountDAO.save(accTo);
                fundTransfer = fundTransferDAO.save(fundTransfer);
                return fundTransfer;
            }else {
                throw new InsufficientFundException("Insufficient Balance $"+wrapper.getAmount());
            }
        }else{
            throw new AccountNotFoundException("Invalid Account");
        }
    }

    @Override
    public FundTransfer findById(String idTransfer) {
        return fundTransferDAO.findById(idTransfer).get();
    }
}