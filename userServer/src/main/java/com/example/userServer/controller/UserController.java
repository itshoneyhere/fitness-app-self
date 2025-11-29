package com.example.userServer.controller;

import com.example.userServer.dtos.RequestUser;
import com.example.userServer.dtos.ResponseUsers;
import com.example.userServer.model.Users;
import com.example.userServer.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(name = "User Apis", description = "Create, Get one or multiple users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(description = "Get All Users")
    public ResponseEntity<List<Users>> getAllUsers(
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "id") String sortBy)
    {


        return ResponseEntity.ok(  userService.getAllUsers(page,size,sortBy).getContent());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsers> getOneUser(@PathVariable String id){

        return ResponseEntity.ok(userService.getOneUser(id));

    }

    @PostMapping("/create")
    public ResponseEntity<ResponseUsers> createUser(@RequestBody @Valid RequestUser requestUser,
                                                    @AuthenticationPrincipal Jwt jwt)
    {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(userService.createOneUser(userId,requestUser));
    }

    @PostMapping("/create/bulk")
    public ResponseEntity<List<ResponseUsers>> createUser(@RequestBody @Valid List<RequestUser> requestUsers)
    {
        return ResponseEntity.ok(userService.createUsersBulk(requestUsers));
    }
}
