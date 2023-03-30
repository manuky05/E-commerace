package com.mantu.electronic.store.ElectronicStore.serviceImpl;

import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.UserDto;
import com.mantu.electronic.store.ElectronicStore.entities.User;
import com.mantu.electronic.store.ElectronicStore.exceptions.ResourceNotFoundException;
import com.mantu.electronic.store.ElectronicStore.helper.Helper;
import com.mantu.electronic.store.ElectronicStore.repositories.UserRepository;
import com.mantu.electronic.store.ElectronicStore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUSer(UserDto userDto) {
        String userId=UUID.randomUUID().toString();
        userDto.setUserId(userId);
        User user= dtoToEntity(userDto);
        User saveduser = this.userRepository.save(user);
        UserDto dto = entityToDto(saveduser);
        return dto;
    }

    @Override
    public UserDto UpdateUser(UserDto userDto, String userId) {

       User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));

       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());
       user.setGender(userDto.getGender());
       user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepository.save(user);
        UserDto userDto1 =entityToDto(updatedUser);


        return this.modelMapper.map(userDto1,UserDto.class);


    }

    @Override
    public void deleteUser(String userId) {
//       User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user Id","userId"));
        User user=this.userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
       this.userRepository.delete(user);
    }



    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize,String sortBy,String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber-1, pageSize,sort);//pageNumber-1

        Page<User> page =userRepository.findAll(pageable);
        PageableResponse<UserDto> response= Helper.getPageableResponse(page, UserDto.class);

        return response;

    }

//    @Override
//    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize,String sortBy,String sortDir) {
//        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
//        Pageable pageable= PageRequest.of(pageNumber, pageSize,sort);
//
//        Page<User> page =userRepository.findAll(pageable);
//        List<User> users = page.getContent();
//
////        List<User> users = this.userRepository.findAll();
//
//        List<UserDto> userDtos=users.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());
//        PageableResponse<UserDto> response=new PageableResponse<>();
//        response.setContent(userDtos);
//        response.setPageNumber(page.getNumber());
//        response.setPageSize(page.getSize());
//        response.setTotalElements(page.getTotalElements());
//        response.setTotalPages(page.getTotalPages());
//        response.setLastPage(page.isLast());
//
//
//
//
//        return response;
//    }

    @Override
    public UserDto findUser(String userId) {
        return null;
    }


    @Override
    public UserDto findUserById(String userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found"));
        UserDto userDto1=this.entityToDto(user);

        return userDto1;
    }



    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = (List<UserDto>) users.stream().map(user -> entityToDto(user));
        return  dtoList;


//        User user = this.userRepository.findById(keyword).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", "userId"));
//        return this.entityToDto(userd);


    }

    @Override
    public List<UserDto> searchUserById(String keyword) {
        return null;
    }


    @Override
    public UserDto getUSerByEmail(String email) {
        User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found with email name"));
        return entityToDto(user);
    }




    private UserDto entityToDto(User savedUser){
        UserDto userDto = this.modelMapper.map(savedUser, UserDto.class);
//       UserDto userDto= UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                . password(savedUser.getPassword())
//               .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName())
//                .build();

        return userDto;



    }
    private User dtoToEntity(UserDto userDto ){
        User user = this.modelMapper.map(userDto, User.class);
//        User user=User.builder()
//                . userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();

        return user;
    }
}
