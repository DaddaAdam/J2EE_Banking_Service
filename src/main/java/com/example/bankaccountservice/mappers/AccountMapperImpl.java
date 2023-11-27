package com.example.bankaccountservice.mappers;

import com.example.bankaccountservice.dto.BankAccountRequestDTO;
import com.example.bankaccountservice.dto.BankAccountResponseDTO;
import com.example.bankaccountservice.entities.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@AllArgsConstructor
@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);

        return bankAccountResponseDTO;
    }

    @Override
    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO bankAccountResponseDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountResponseDTO, bankAccount);

        return bankAccount;
    }


}

