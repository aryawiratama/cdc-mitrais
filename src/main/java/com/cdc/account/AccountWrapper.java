package com.cdc.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountWrapper {

    @Length(max = 6, min = 6, message = "Account Number should have 6 digits length")
    @Digits(integer = 6, fraction = 0)
    private String accountNumber;

    @Length(max = 6, min = 6, message = "PIN should have 6 digits length")
    @Digits(integer = 6, fraction = 0)
    private String pin;
}
