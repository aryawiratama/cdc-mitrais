package com.cdc.transfer;

import com.cdc.validator.IsAccountExist;
import com.cdc.validator.IsNumeric;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferWrapper {
    private String accountFrom;

    @IsAccountExist
    @IsNumeric(message = "Invalid Account")
    private String accountTo;

    @Digits(integer = 7, fraction = 2, message = "Invalid amount")
    @Min(value = 1, message = "Minimum amount to withdraw is $1")
    @Max(value = 1000, message = "Maximum amount to withdraw is $1000")
    private BigDecimal amount;

    @NotNull(message = "Invalid Reference Number")
    @IsNumeric(message = "Invalid Reference Number")
    private String referenceNumber;
}
