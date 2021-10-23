package com.oop.moneymanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    private String type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Statistic> statistics;

    public Category(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Category() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Statistic> getStatistics() {
        return this.statistics;
    }

    @Override
    public String toString() {
        return name;
    }
}
