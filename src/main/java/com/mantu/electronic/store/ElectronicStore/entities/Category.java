package com.mantu.electronic.store.ElectronicStore.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
@Builder
public class Category {
    @Id

    private String categoryId;
    @Column(name="category_tittle", length=60 ,nullable=false)
    private String tittle;
    @Column(name="category_description", length=500)
    private String description;
    @Column(name="category_coverImage")
    private String coverImage;
    @OneToMany(mappedBy ="category" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products=new ArrayList<>();

}
