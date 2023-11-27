package com.example.bankaccountservice.web;

import com.example.bankaccountservice.dto.BankAccountRequestDTO;
import com.example.bankaccountservice.dto.BankAccountResponseDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import com.example.bankaccountservice.service.AccountServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountRestController {


    private BankAccountRepository repository;
    private AccountServiceImpl accountService;

    @Autowired
    AccountRestController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    public AccountRestController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return repository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount bankAccount1 = repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        bankAccount1.setBalance(bankAccount.getBalance());
        bankAccount1.setDevise(bankAccount.getDevise());
        bankAccount1.setType(bankAccount.getType());
        bankAccount1.setDateCreated(bankAccount.getDateCreated());
        return repository.save(bankAccount);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
