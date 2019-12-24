package com.cdc.validator;

import com.cdc.account.Account;
import com.cdc.account.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Optional;

@Component
public class AccountExistValidator implements ConstraintValidator<IsAccountExist, String> {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null){
            return true;
        }
        Optional<Account> account = accountDAO.findAccountByAccountNumber(s);
        if(account.isPresent()){
            return true;
        }
        return false;
    }
}
