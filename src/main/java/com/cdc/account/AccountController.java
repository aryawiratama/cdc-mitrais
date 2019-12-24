package com.cdc.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AccountController {

    private AccountDAO accountDAO;

    @Autowired
    public AccountController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GetMapping("/")
    public String showWelcomeScreen(AccountWrapper wrapper) {
        return "welcome-screen";
    }

    @PostMapping("/validate-account")
    public String validateAccount(@Valid AccountWrapper wrapper, BindingResult bindingResult, Model model,
                                  RedirectAttributes ra) {
        Optional<Account> account = accountDAO.findAccountByAccountNumberAndPin(wrapper.getAccountNumber(), wrapper.getPin());
        if (account.isPresent()) {
            ra.addFlashAttribute("name", account.get().getName());
            ra.addFlashAttribute("id_account", account.get().getId());
            return "redirect:/transaction-screen";
        }
        model.addAttribute("errorMessage", "Invalid Account Number/PIN");
        return "welcome-screen";
    }

    @GetMapping("/transaction-screen")
    public String transactionScreen(){
        return "transaction-screen";
    }
}
