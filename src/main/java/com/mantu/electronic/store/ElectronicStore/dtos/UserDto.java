package com.mantu.electronic.store.ElectronicStore.dtos;

import com.mantu.electronic.store.ElectronicStore.validate.ImageNameValid;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    //@GeneratorType(Strategy=GeneratorType.IDENTITY)

    private String userId;
   @Size(min=3, max=15,message="invalid name!!")
    private String name;
   @Pattern(regexp ="^[A-Za-z][A-Za-z0-9_]+@([a-z0-9]+\\.)+[a-z]{2,10}$", message = "invalid email")
    @NotBlank(message="email is required!!")

    private String email;
    @NotBlank(message ="password requires!!")
    //@Pattern(regexp = )
    private String password;
   @Size(min=3 ,max=6,message="invalid gender!!")
    private String gender;
   @Size(max=1000)
   @NotBlank(message="Write somethings about yourself")
    private String about;
   @ImageNameValid
   @Column(name="user_image_name")

    private String imageName;


}
