package com.oop.moneymanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Statistic> statistics;


    public Account(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account() {}

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

    public List<Statistic> getStatistics() {
        return this.statistics;
    }

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
