package com.cdc.transfer;

import com.cdc.AccountNotFoundException;
import com.cdc.InsufficientFundException;
import com.cdc.account.AccountWrapper;
import com.cdc.withdraw.Withdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FundTransferController {

    private FundTransferService fundTransferService;

    @Autowired
    public FundTransferController(FundTransferService fundTransferService) {
        this.fundTransferService = fundTransferService;
    }

    @GetMapping("/fund-transfer")
    public String fundTransferScreen(@RequestParam("id") String idAccount, Model model,
                                     FundTransferWrapper fundTransferWrapper){
        fundTransferWrapper.setAccountFrom(idAccount);
        model.addAttribute("idAccount",idAccount);
        return "fund-transfer";
    }

    @PostMapping("/fund-transfer")
    public String fundTransfer(@Valid FundTransferWrapper fundTransferWrapper, BindingResult bindingResult, Model model,
                               RedirectAttributes ra){
        String errorMessage = "";
        try{
            FundTransfer fundTransfer = fundTransferService.saveTransfer(fundTransferWrapper);
            return "redirect:/summary-transfer?idTransfer="+fundTransfer.getId();
        }catch (InsufficientFundException | AccountNotFoundException ex){
            errorMessage = ex.getMessage();
        }catch (Exception e){
            errorMessage = e.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("idAccount", fundTransferWrapper.getAccountFrom());
        return "fund-transfer";
    }

    @GetMapping("/summary-transfer")
    public String summary(@RequestParam("idTransfer") String idTransfer, Model model){
        FundTransfer transfer = fundTransferService.findById(idTransfer);
        model.addAttribute("transfer", transfer);
        return "summary-transfer";
    }
}
