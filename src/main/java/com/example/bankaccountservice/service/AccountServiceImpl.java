package com.example.bankaccountservice.service;

import com.example.bankaccountservice.dto.BankAccountRequestDTO;
import com.example.bankaccountservice.dto.BankAccountResponseDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.mappers.AccountMapperImpl;
import com.example.bankaccountservice.repositories.BankAccountRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@Data
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private BankAccountRepository bankAccountRepository;
    private AccountMapperImpl accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(bankAccountDTO);

        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setDateCreated(new Date());

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);

        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not found")
        );

        // Update the account from the request dto
        bankAccount.setDevise(bankAccountDTO.getDevise());
        bankAccount.setType(bankAccountDTO.getType());
        bankAccount.setBalance(bankAccountDTO.getBalance());

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);

        return bankAccountResponseDTO;
    }
}
