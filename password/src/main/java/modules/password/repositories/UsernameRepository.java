package modules.password.repositories;

import modules.password.entities.UsernameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsernameRepository extends JpaRepository<UsernameEntity, Long> {
}
