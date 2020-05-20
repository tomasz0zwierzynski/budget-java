package pl.tomzwi.budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tomzwi.budget.entity.Token;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    @Query( "SELECT t FROM Token t WHERE t.expires < :date" )
    List<Token> findExpiredBeforeDate(@Param( "date" ) LocalDateTime date);

}
