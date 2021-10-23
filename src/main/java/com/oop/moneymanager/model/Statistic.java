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

    @Column
    private Integer idAccount;

    @Column
    private Integer idCategory;

    @Column
    private Integer numberOfMoney;

    @Column
    private Date time;

    @Column
    private String notes;
}
