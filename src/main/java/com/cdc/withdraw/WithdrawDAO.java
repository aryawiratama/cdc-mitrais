package com.cdc.withdraw;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WithdrawDAO extends PagingAndSortingRepository<Withdraw, String> {
}
