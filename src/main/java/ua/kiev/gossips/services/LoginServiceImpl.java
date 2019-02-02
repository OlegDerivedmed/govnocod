package ua.kiev.gossips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kiev.gossips.dto.TokenDto;
import ua.kiev.gossips.entities.GossipUser;
import ua.kiev.gossips.entities.Token;
import ua.kiev.gossips.forms.LoginForm;
import ua.kiev.gossips.repository.TokenRepository;
import ua.kiev.gossips.repository.UserRepository;
import ua.kiev.gossips.utils.TokenGenerator;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<GossipUser> userCandidate = userRepository.findOneByEmail(loginForm.getEmail());
        if (userCandidate.isPresent()){
            GossipUser gossipUser = userCandidate.get();
            if (passwordEncoder.matches(loginForm.getPassword(), gossipUser.getPassword())){
                Token token = tokenGenerator.generateToken(gossipUser);
                tokenRepository.save(token);
                return TokenDto.from(token);
            }
        }
        throw new IllegalArgumentException("wrong login or password");
    }
}
