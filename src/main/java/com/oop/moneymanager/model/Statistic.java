package com.oop.moneymanager.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table
@Entity
public class Statistic {
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
    private Integer numberOfMoney;

    @Column
    private Date time;

    @Column
    private String notes;

    public Statistic(Integer numberOfMoney, Date time, String notes) {
        this.numberOfMoney = numberOfMoney;
        this.time = time;
        this.notes = notes;
    }
    public Statistic(){}

    public Integer getId() {
        return id;
    }


    public Integer getIdAccount() {
        return this.account.getId();
    }

    public Integer getIdCategory() {
        return this.category.getId();
    }



    public Integer getNumberOfMoney() {
        return numberOfMoney;
    }

    public void setNumberOfMoney(Integer numberOfMoney) {
        this.numberOfMoney = numberOfMoney;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
