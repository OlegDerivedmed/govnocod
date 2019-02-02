package ua.kiev.gossips.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.gossips.entities.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findOneByValue(String value);
}
