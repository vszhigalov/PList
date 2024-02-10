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
import java.util.Arrays;
import java.util.List;

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
    private Double weight;
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

    public Product(Double weight, String name){
        this.name = name;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return name + ", weight: " + weight;
    }

    public Product(String productData, long dishId) {
        List<String> data = Arrays.asList(productData.split(","));
        this.name = data.get(0);
        this.weight = data.size() > 1 ? Double.parseDouble(data.get(1)) : 0;
        this.dishId = dishId;
    }
}
