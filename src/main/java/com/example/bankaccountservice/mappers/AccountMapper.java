package com.example.bankaccountservice.mappers;

import com.example.bankaccountservice.dto.BankAccountRequestDTO;
import com.example.bankaccountservice.dto.BankAccountResponseDTO;
import com.example.bankaccountservice.entities.BankAccount;
import org.springframework.stereotype.Component;

public interface AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount);
    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO bankAccountResponseDTO);
}
