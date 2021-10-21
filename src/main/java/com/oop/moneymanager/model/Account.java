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
    private Integer balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name;
    }
}
