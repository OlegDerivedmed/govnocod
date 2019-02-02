package ua.kiev.gossips.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.gossips.dto.TokenDto;
import ua.kiev.gossips.forms.LoginForm;
import ua.kiev.gossips.services.LoginService;

import javax.validation.Valid;

@RestController
@Api
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginForm loginForm){
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}
