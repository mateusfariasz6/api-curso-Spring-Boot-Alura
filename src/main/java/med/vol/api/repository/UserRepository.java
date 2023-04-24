package med.vol.api.repository;

import med.vol.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
   public UserDetails findByLogin(String login);
}
