package com.mantu.electronic.store.ElectronicStore.services;

import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.UserDto;
import com.mantu.electronic.store.ElectronicStore.entities.User;
import com.mantu.electronic.store.ElectronicStore.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringBootTest
//@ContextConfiguration(classes = { UserServiceTest.class })
public class UserServiceTest {
    @Autowired
    private ModelMapper modelMapper;
//    @Autowired
//    private RoleRepository roleRepository;
  @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    User user;
    //Role role;
 //roleId="abc";
    @BeforeEach
    public void init(){
//le=Role.builder().rileId("abc").rolename("normal").build();
//        String userId="123";
      user = User.builder()
                .name("Mantu Pandit")
                .about("I am programmer")
                .email("mantu@gmail.com")
                .imageName("man.png")
                .gender("male")
                .password("mantu")
//                .roles(Set.of(role))
                .build();
    }

    @Test
    public  void createUserTest(){
        //arrenge
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        //Mockito.when(roleRepository.findById(roleId)).thenReturn();
       //act
        UserDto userDto=userService.createUSer(modelMapper.map(user, UserDto.class));
        System.out.println(user.getName());
        Assertions.assertNotNull(userDto);
        Assertions.assertEquals("Mantu Pandit",user.getName());
   }
   @Test
   public void updateUserTest(){
        String userId="123";
       UserDto userDto = UserDto.builder()
               .name("Mantu ")
               .about("I am programmer")
               .gender("male")
               .password("mantu")
               .imageName("mantu.png")
//                .roles(Set.of(role))
               .build();

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        UserDto updatedUser=userService.UpdateUser(userDto,userId);
       // UserDto updatedUser = modelMapper.map(user, UserDto.class);
       System.out.println(updatedUser.getName());

        Assertions.assertNotNull(userDto);
       Assertions.assertEquals(userDto.getName(),updatedUser.getName());


   }
   @Test
   public void deleteUserTest(){
        String userId="userIdac";
        user=User.builder().name("pawan").email("pawan@gmail.com").gender("male")
                .about("this is for delete user command").imageName("pawan.pag").build();
        //Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById("userIdac")).thenReturn(Optional.of(user));
        userService.deleteUser(userId);
       // Mockito.verify(roleRepository,Mockito.times(1)).delete(user);
        //Mockito.when(userRepository.delete(Mockito.any())).thenReturn(userId);
       Assertions.assertNotNull(user);



   }
   @Test
   public void getAllUserTest(){
      User user1 = User.builder()
               .name("Amit Pandit")
               .about("I am programmer")
               .email("Amit@gmail.com")
               .imageName("man.png")
               .gender("male")
               .password("Amit")
//                .roles(Set.of(role))
               .build();
      User user2 = User.builder()
               .name("Sonu Pandit")
               .about("I am programmer")
               .email("mantu@gmail.com")
               .imageName("man.png")
               .gender("male")
               .password("Sonu")
//                .roles(Set.of(role))
               .build();
        List<User> userList= Arrays.asList(user,user1,user2);

        Page<User> page =new PageImpl<>(userList);

        Mockito.when(userRepository.findAll((Pageable) Mockito.any())).thenReturn(page);

        PageableResponse<UserDto> allUser = userService.getAllUser(1,2,"name","asc");
       System.out.println(allUser.getContent().size());

        Assertions.assertEquals(3,allUser.getContent().size());
   }
   @Test
   public void findUserByIdTest(){
       String userId="userIdac";
       user=User.builder().name("pawan").email("pawan@gmail.com").gender("male")
               .about("this is for delete user command").imageName("pawan.pag").build();
       Mockito.when(userRepository.findById("userIdac")).thenReturn(Optional.of(user));
       userService.findUserById(userId);
       System.out.println(user.getName());
       Assertions.assertEquals("pawan",user.getName());

   }
   @Test
   public void getUSerByEmailTest() {
        String email="manuky04@gmail.com";

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
       Optional<User> byEmail = userService.findByEmail(email);
       Assertions.assertTrue( byEmail.isPresent());
       User user1 = byEmail.get();
       Assertions.assertEquals(user.getEmail(),user1.getEmail(),"email dose not match !!" );


   }
    @Test
   public void searchUserTest(){

        User user = User.builder()
                .name("Aman Pandit")
                .about("I am programmer")
                .email("Aman@gmail.com")
                .imageName("man.png")
                .gender("male")
                .password("Aman")
//                .roles(Set.of(role))
                .build();
        User user1 = User.builder()
                .name("Amit Pandit")
                .about("I am programmer")
                .email("Amit@gmail.com")
                .imageName("man.png")
                .gender("male")
                .password("Amit")
//                .roles(Set.of(role))
                .build();
        User user2 = User.builder()
                .name("Sonu Pandit")
                .about("I am programmer")
                .email("mantu@gmail.com")
                .imageName("man.png")
                .gender("male")
                .password("Sonu")
//                .roles(Set.of(role))
                .build();
        User user3 = User.builder()
                .name("Vinay Pandit")
                .about("I am programmer")
                .email("mantu@gmail.com")
                .imageName("man.png")
                .gender("male")
                .password("Vinay")
//                .roles(Set.of(role))
                .build();
        String keywords="kumar";
        Mockito.when(userRepository.findByNameContaining(keywords)).thenReturn(Arrays.asList(user,user1,user2,user3));
        List<UserDto> userDtos = userService.searchUser(keywords);

        Assertions.assertEquals(4,userDtos.size(),"size not matched !!");

    }
}
