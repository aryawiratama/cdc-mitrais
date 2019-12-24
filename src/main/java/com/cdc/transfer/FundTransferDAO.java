package com.cdc.transfer;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FundTransferDAO extends PagingAndSortingRepository<FundTransfer, String> {
}
