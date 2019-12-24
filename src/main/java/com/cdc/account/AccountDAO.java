package com.cdc.account;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AccountDAO extends PagingAndSortingRepository<Account, String> {

    Optional<Account> findAccountByAccountNumberAndPin(String accountNumber, String pin);

    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
