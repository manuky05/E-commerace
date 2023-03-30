package com.mantu.electronic.store.ElectronicStore.controlles;

import com.mantu.electronic.store.ElectronicStore.dtos.ImageResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.UserDto;
import com.mantu.electronic.store.ElectronicStore.exceptions.ApiResponseMessage;
import com.mantu.electronic.store.ElectronicStore.services.FileService;
import com.mantu.electronic.store.ElectronicStore.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    private String userId;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    public UserController() {
    }

    @PostMapping("/")
    public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1 = this.userService.createUSer(userDto);
        return new ResponseEntity<UserDto>(userDto1, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@PathVariable("userId") String userId
            ,@Valid @RequestBody UserDto userDto){
      UserDto updateduserDto =  this.userService.UpdateUser(userDto,userId);
      return new ResponseEntity<>(updateduserDto,HttpStatus.OK);

    }

    @GetMapping("/allUser")
    public ResponseEntity<PageableResponse<UserDto>> getAllUser(
            @RequestParam(value = "pageNumber",defaultValue="1",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue="10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue="name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue="asc",required = false) String sortDir){
        List<UserDto> allUser = this.userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<PageableResponse<UserDto>>((PageableResponse<UserDto>) allUser,HttpStatus.OK);
//        return new ResponseEntity<PageableResponse<UserDto>>((PageableResponse<UserDto>) userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);

    }
    @DeleteMapping("/{userid}")
//    public ResponseEntity<?>DeleteUser(@PathVariable("/userid") String userId){
//        userService.deleteUser(userId);
//                return new ResponseEntity(Map.of("Message","User Delete Successful"),HttpStatus.OK);
  public ResponseEntity<ApiResponseMessage>DeleteUser(@PathVariable("/userid") String userId){
            userService.deleteUser(userId);
        ApiResponseMessage
                message=ApiResponseMessage
                .builder()
                .message("User Delete Successfu")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message,HttpStatus.OK);




    }
//    @GetMapping("/{userId}")
//    public ResponseEntity<UserDto>findUser(@PathVariable String userId){
//        return new ResponseEntity<>(userService.findUserById(userId),HttpStatus.OK);
//
//    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto>getUserByEmail(@PathVariable String email){
       return new ResponseEntity<>(userService.getUSerByEmail(email),HttpStatus.OK) ;

    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>>searchUser(@PathVariable String keywords){
        return new ResponseEntity <>(userService.searchUser(keywords),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>findUserById(@Valid  @PathVariable String userId){

//        UserDto userById = this.userService.findUserById(userId);
//        return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
        return  ResponseEntity.ok(this.userService.findUserById(userId));

    }
    //upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse>uploadUserImage( @RequestParam("userImage")MultipartFile image,@PathVariable String userId) throws IOException {

      String imageName= fileService.uploadFile(image,imageUploadPath);
        UserDto user = userService.findUserById(userId);
        user.setImageName(imageName);
        UserDto userDto = userService.UpdateUser(user, userId);
        ImageResponse imageResponse=ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
      return  new ResponseEntity<>(imageResponse,HttpStatus.CREATED);

    }

    //serve user image
    @GetMapping("/image/{userId}")
    public void searchImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto user = userService.findUserById(userId);
        logger.info("User image name:{}",user.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }


}
