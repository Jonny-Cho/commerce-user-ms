package com.commerce.controller;

import com.commerce.dto.UserDto;
import com.commerce.jpa.UserEntity;
import com.commerce.service.UserService;
import com.commerce.vo.Greeting;
import com.commerce.vo.RequestUser;
import com.commerce.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid final RequestUser requestUser) {
        final UserDto userDto = new UserDto(requestUser);
        final UserDto user = userService.createUser(userDto);
        final ResponseUser responseUser = new ResponseUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        final List<UserEntity> userEntityList = userService.getUserByAll();
        final List<ResponseUser> result = ResponseUser.from(userEntityList);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") final String userId) {
        final UserDto userDto = userService.getUserByUserId(userId);
        final ResponseUser result = new ResponseUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
