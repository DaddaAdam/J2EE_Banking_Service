package com.example.bankaccountservice;

import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.entities.Customer;
import com.example.bankaccountservice.enums.AccountType;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import com.example.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {
            Stream.of("C1", "C2", "C3", "C4").forEach(c -> {
                customerRepository.save(Customer.builder()
                        .name(c)
                        .build());
            });

            customerRepository.findAll().forEach(
                    c -> {
                        Stream.of("A1", "A2", "A3", "A4").forEach(a -> {
                            bankAccountRepository.save(BankAccount.builder()
                                    .id(UUID.randomUUID().toString())
                                    .balance(1000.0 + Math.random()*9000)
                                    .dateCreated(new Date())
                                    .devise("USD")
                                    .type(AccountType.SAVINGS_ACCOUNT)
                                    .customer(c)
                                    .build());
                        });
                    }
            );
        };


    }

}
