package com.mantu.electronic.store.ElectronicStore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")

public class Category {
    @Id
    @Column(name="id")
    private String categoryId;
    @Column(name="category_tittle", length=60)
    private String tittle;
    @Column(name="category_description", length=500)
    private String description;
    @Column(name="category_coverImage")
    private String coverImage;

}
