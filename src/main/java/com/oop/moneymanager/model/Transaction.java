package com.oop.moneymanager.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;

    @Column
    private Integer amount;

    @Column
    private Date time;

    @Column
    private String notes;

    public Transaction(Integer numberOfMoney, Date time, String notes) {
        this.amount = numberOfMoney;
        this.time = time;
        this.notes = notes;
    }
    public Transaction(){}

    public Integer getIdAccount() {
        return this.account.getId();
    }

    public Integer getIdCategory() {
        return this.category.getId();
    }

}
