package ua.kiev.gossips.services;

import ua.kiev.gossips.entities.GossipUser;
import ua.kiev.gossips.forms.UserForm;

import java.util.Optional;

public interface UserService {
    void createUser(UserForm userForm);
    Optional<GossipUser> findByEmail(String login);
}
