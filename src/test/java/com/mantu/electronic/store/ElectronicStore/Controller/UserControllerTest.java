package com.mantu.electronic.store.ElectronicStore.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantu.electronic.store.ElectronicStore.dtos.ImageResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.UserDto;
import com.mantu.electronic.store.ElectronicStore.entities.User;
import com.mantu.electronic.store.ElectronicStore.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest

@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;
    private User user;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;
    ImageResponse imageResponse;

    @BeforeEach
    public void init() {
       user=User.builder()
               .name("pawan")
               .email("mantu@gmail.com")
               .gender("male")
               .imageName("abc.png")
               .password("123")
               .about("this is user tset case")
               .build();
//        user = User.builder()
//                .name("faizan")
//                .email("abc@gmail.com")
//                .password("12345")
//                .gender("male")
//                .about("this is test practise")
//                .imageName("abc.png")
//                .build();
    }

    @Test
    public void createUserTest() throws Exception {


        UserDto dto = modelMapper.map(user, UserDto.class);
        Mockito.when(userService.createUSer(Mockito.any())).thenReturn(dto);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertObjectJsonString(user))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").exists());
    }
    private String convertObjectJsonString(Object user) {
        try {
            return new ObjectMapper().writeValueAsString(user);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}

