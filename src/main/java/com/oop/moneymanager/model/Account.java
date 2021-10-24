package com.oop.moneymanager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer initialBalance;

    public Account(String name, Integer balance) {
        this.name = name;
        this.initialBalance = balance;
    }

    public Account() {}

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(Object o) {
        if (o instanceof Account account){
            return account.getId().equals(this.getId());
        }
        return false;
    }
}
