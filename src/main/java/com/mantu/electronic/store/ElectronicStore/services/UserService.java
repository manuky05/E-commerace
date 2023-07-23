package com.mantu.electronic.store.ElectronicStore.services;

import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.UserDto;
import com.mantu.electronic.store.ElectronicStore.entities.User;

import java.util.List;
import java.util.Optional;

public interface  UserService {
    //create
    UserDto createUSer(UserDto userDto);
    //update
    UserDto UpdateUser(UserDto userDto,String userId);
    //delete
      void deleteUser(String userId);
      //get all user
       PageableResponse<UserDto> getAllUser(int pageNumber,int pageSize,String sortDir,String sortBy);



    //get single user by id
    UserDto findUser(String userId);


    UserDto findUserById(String userId);

    //search user
    List<UserDto> searchUser(String keyword);



    UserDto getUSerByEmail(String email);


    Optional<User> findByEmail(String email);
}
