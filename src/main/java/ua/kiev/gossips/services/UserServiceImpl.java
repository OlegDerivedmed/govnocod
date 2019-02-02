package ua.kiev.gossips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kiev.gossips.entities.GossipUser;
import ua.kiev.gossips.entities.State;
import ua.kiev.gossips.forms.UserForm;
import ua.kiev.gossips.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserForm userForm) {
        GossipUser gossipUser = GossipUser.builder()
                .email(userForm.getEmail())
                .name(userForm.getName())
                .surname(userForm.getSurname())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .state(State.ACTIVE)
                .build();
        userRepository.save(gossipUser);

    }

    @Override
    public Optional<GossipUser> findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
}
