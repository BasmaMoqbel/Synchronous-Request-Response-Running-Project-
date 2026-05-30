package com.basma.identity_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @GetMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
    public String sayHello() {
        return "Identity Service ???? ????? ????! ????? ??? ?? ????! ?";
    }

    // ???? ??????? ?????????? ???????? JSON ? POST
    @PostMapping(value = "/register", produces = "text/plain;charset=UTF-8")
    public String registerUser(@RequestBody AuthRequest request) {
        return service.saveUser(request);
    }

    // ???? ????? ?????? ?????? ?????? ??????? ???????? JSON ? POST
    @PostMapping(value = "/login", produces = "text/plain;charset=UTF-8")
    public String loginAndGetToken(@RequestBody AuthRequest request) {
        try {
            return service.login(request);
        } catch (RuntimeException e) {
            return "??? ????? ??????: " + e.getMessage();
        }
    }
}