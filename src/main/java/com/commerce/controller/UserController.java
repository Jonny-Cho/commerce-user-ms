package com.commerce.controller;

import com.commerce.dto.UserDto;
import com.commerce.service.UserService;
import com.commerce.vo.Greeting;
import com.commerce.vo.RequestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {

    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody @Valid final RequestUser requestUser) {
        final UserDto userDto = new UserDto(requestUser);
        userService.createUser(userDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
