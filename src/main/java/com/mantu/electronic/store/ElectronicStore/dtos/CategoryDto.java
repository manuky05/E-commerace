package com.mantu.electronic.store.ElectronicStore.dtos;

import com.mantu.electronic.store.ElectronicStore.entities.Product;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;
    @NotBlank

    @Size(min=3, max=15,message="tittle must be minimum 4 characters!!")
    private String tittle;
    @NotBlank(message="Description required !!")
    private String description;
    @NotBlank(message="cover image required")
    private String  coverImage;



}
