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

    @Override
    public String toString() {
        return name;
    }
}
