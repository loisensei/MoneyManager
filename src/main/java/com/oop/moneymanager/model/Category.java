package com.oop.moneymanager.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Table
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer type;

    @Column(columnDefinition = "int default 1")
    public Boolean isVisible;

    public Category(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public Category() {

    }

    @Override
    public String toString() {
        return name;
    }
}
