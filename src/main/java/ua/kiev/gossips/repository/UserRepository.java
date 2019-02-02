package ua.kiev.gossips.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.gossips.entities.GossipUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<GossipUser,Long> {

    Optional<GossipUser> findOneByEmail(String email);
}
