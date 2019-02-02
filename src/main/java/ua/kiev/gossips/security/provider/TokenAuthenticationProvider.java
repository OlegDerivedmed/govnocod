package ua.kiev.gossips.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ua.kiev.gossips.entities.Token;
import ua.kiev.gossips.entities.TokenState;
import ua.kiev.gossips.repository.TokenRepository;
import ua.kiev.gossips.security.toketauth.TokenAuthentication;
import ua.kiev.gossips.utils.TokenGenerator;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        Optional<Token> tokenCandidate = tokenRepository.findOneByValue(tokenAuthentication.getName());
        if (tokenCandidate.isPresent() && tokenCandidate.get().getState().equals(TokenState.ACTIVE)) {
            tokenCandidate = getValidToken(tokenCandidate.get());
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenCandidate.get().getGossipUser().getEmail());
            tokenAuthentication.setAuthenticated(true);
            tokenAuthentication.setUserDetails(userDetails);
            return tokenAuthentication;
        }
        throw new IllegalArgumentException("Wrong token or expired");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }

    private Optional<Token> getValidToken(Token candidate) {
        if (candidate.getExpires().isAfter(LocalDateTime.now())) {
            candidate.setState(TokenState.EXPIRED);
            tokenRepository.save(candidate);
            Token newToken = tokenGenerator.generateToken(candidate.getGossipUser());
            tokenRepository.save(newToken);
            return Optional.of(newToken);
        }
        return Optional.of(candidate);
    }
}
