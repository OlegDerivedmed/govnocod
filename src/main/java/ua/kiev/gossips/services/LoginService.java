package ua.kiev.gossips.services;

import ua.kiev.gossips.dto.TokenDto;
import ua.kiev.gossips.forms.LoginForm;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
