package com.cdc.withdraw;

import com.cdc.InsufficientFundException;
import com.cdc.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
public class WithdrawController {

    private WithdrawService withdrawService;

    @Autowired
    public WithdrawController(WithdrawService withdrawService){
        this.withdrawService = withdrawService;
    }

    @GetMapping("/withdraw-screen")
    public String withdrawScreen(@RequestParam("id") String idAccount, Model model){
        model.addAttribute("idAccount", idAccount);
        return "withdraw-screen";
    }

    @GetMapping("/withdraw")
    public String withdraw(@RequestParam("id") String idAccount, @RequestParam("amount") BigDecimal amount,
                           Model model){
        String errorMessage = "";
        try{
            Withdraw withdraw = withdrawService.saveWithdraw(idAccount, amount);
            return "redirect:/summary?idWithdraw="+withdraw.getId();
        }catch (InsufficientFundException ins){
            errorMessage = ins.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("idAccount", idAccount);
        return "withdraw-screen";
    }

    @GetMapping("/summary")
    public String summary(@RequestParam("idWithdraw") String idWithdraw, Model model){
        Withdraw withdraw = withdrawService.findById(idWithdraw);
        model.addAttribute("withdraw", withdraw);
        return "summary-screen";
    }

    @GetMapping("/back-to-transaction")
    public String backToTransaction(@RequestParam("id") String idAccount, RedirectAttributes ra){
        Account account = withdrawService.findAccountById(idAccount);
        ra.addFlashAttribute("name", account.getName());
        ra.addFlashAttribute("id_account", account.getId());
        return "redirect:/transaction-screen";

    }
}
