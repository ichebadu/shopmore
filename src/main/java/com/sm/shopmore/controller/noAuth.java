package com.sm.shopmore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signUp")
public class noAuth {
    @GetMapping
    public ResponseEntity<String> reg(){
        return ResponseEntity.ok("am good");
    }
}
