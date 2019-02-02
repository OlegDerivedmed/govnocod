package ua.kiev.gossips.security.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.kiev.gossips.entities.State;
import ua.kiev.gossips.entities.GossipUser;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private GossipUser gossipUser;

    public UserDetailsImpl(GossipUser gossipUser) {
        this.gossipUser = gossipUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return gossipUser.getPassword();
    }

    @Override
    public String getUsername() {
        return gossipUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !gossipUser.getState().equals(State.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return gossipUser.getState().equals(State.ACTIVE);
    }
}
