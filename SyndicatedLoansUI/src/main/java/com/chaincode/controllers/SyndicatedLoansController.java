package com.chaincode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chaincode.domain.Loan;
import com.chaincode.domain.Product;
import com.chaincode.services.SyndicatedLoansService;

@Controller
public class SyndicatedLoansController {

    private SyndicatedLoansService syndicatedLoansService;

    @Autowired
    public void setProductService(SyndicatedLoansService syndicatedLoanService) {
        this.syndicatedLoansService = syndicatedLoanService;
    }

    @RequestMapping(value = "/loans", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("loans", syndicatedLoansService.getAllSyndicatedLoans());
        System.out.println("Returning loans:");
        return "loans";
    }

  @RequestMapping("loan/{id}")
    public String showProduct(@PathVariable String id, Model model){
        model.addAttribute("loan", syndicatedLoansService.getLoanById(id));
        return "loanshow";
    }

    @RequestMapping("loan/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("loan", syndicatedLoansService.getLoanById(id));
        return "loanSettle";
    }
    

    @RequestMapping("loan/new")
    public String newLoan(Model model){
        model.addAttribute("loan", new Loan("LN00001","NY PLAZA 1","LAND001","PR0001","BY0001",1000,0));
        return "loanform";
    }

    @RequestMapping(value = "loan", method = RequestMethod.POST)
    public String saveProduct(Loan loan){

        syndicatedLoansService.saveProduct(loan);

        return "redirect:/loan/" + loan.getId();
    }

    
    @RequestMapping(value = "loanSettle", method = RequestMethod.POST)
    public String settleLoan(String loanId,int settlementAmount) throws Exception{
    	
    	if(loanId!=null){

        syndicatedLoansService.settleLoan(loanId,settlementAmount);

        return "redirect:/loan/" + loanId;
    	}
    	else throw new Exception("LoanID cannot be null");
    }
}
