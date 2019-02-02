package ua.kiev.gossips.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.gossips.forms.UserForm;
import ua.kiev.gossips.services.UserService;

import javax.validation.Valid;

@RestController
@Api
public class SignUpController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ApiOperation(value = "signup")
    public ResponseEntity<HttpStatus> signUp(@Valid @RequestBody UserForm userForm) {
        userService.createUser(userForm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
