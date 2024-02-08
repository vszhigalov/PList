package com.plist.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish extends AbstractEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "chatId")
    private long chatId;
    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY)
    private List<Product> productList;


    public Dish(String name) {
        this.name = name;
    }

    public Dish(long chatId, String name) {
        this.name = name;
        this.chatId = chatId;
    }
}
