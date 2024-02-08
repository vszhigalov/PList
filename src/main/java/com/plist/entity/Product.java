package com.plist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private double weight;
    @Column(name = "count")
    private double count;
    @Column(name = "dishid")
    private long dishId;
    @ManyToOne
    @JoinColumn(name = "dishId", nullable = false,
            updatable = false,
            insertable = false)
    private Dish dish;

    public Product(String name, double weight, double count, long dishId) {
        this.name = name;
        this.weight = weight;
        this.count = count;
        this.dishId = dishId;
    }


    @Override
    public String toString() {
        return name + ", amount: "+count+", weight: "+weight;
    }
}
