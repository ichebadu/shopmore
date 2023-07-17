package com.sm.shopmore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class shoppingController {
    @GetMapping
    public ResponseEntity<String> shop(){
        return ResponseEntity.ok("welcome to shop");
    }
}
