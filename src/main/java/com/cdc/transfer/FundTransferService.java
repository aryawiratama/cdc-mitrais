package com.cdc.transfer;

public interface FundTransferService {

    FundTransfer saveTransfer(FundTransferWrapper wrapper)throws Exception;

    FundTransfer findById(String idTransfer);
}