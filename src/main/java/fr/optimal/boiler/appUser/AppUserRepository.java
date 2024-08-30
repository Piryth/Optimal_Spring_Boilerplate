package fr.optimal.optimalshop.appUser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AppUserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findAppUserByUsername(String username);
    Optional<AppUser> findAppUserByEmail(String email);
}
