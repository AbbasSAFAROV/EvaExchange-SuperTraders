package com.eva.tradingApp.controller;


import com.eva.tradingApp.domain.dto.StockDto;
import com.eva.tradingApp.domain.dto.UserDto;
import com.eva.tradingApp.domain.entity.StockItem;
import com.eva.tradingApp.domain.request.UserCreateRequest;
import com.eva.tradingApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<String>> getAllUsers(){
        List<String> list = new ArrayList<>();
        list.add("hello users");
        return ResponseEntity.ok(list);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateRequest request){
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/stocks")
    public List<StockItem> getMyStocks(){
        return userService.getMyStocks();
    }

    @GetMapping("/stocks/id")
    public List<StockItem> getAllMyStocks(@PathVariable Long id){
        return userService.getAllMyStock(id);
    }


}
