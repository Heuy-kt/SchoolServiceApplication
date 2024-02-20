package com.heuy_kt.AdminApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/admin-controller")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("")
    public ResponseEntity sayHello(){
        return ResponseEntity.ok("Hello everyone");
    }

    @GetMapping("sayBye")
    public ResponseEntity saygoodBye(){
        return ResponseEntity.ok("Goodbye everyone");
    }

}
