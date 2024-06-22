package modules.repositories;

import modules.entities.UsernameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsernameRepository extends JpaRepository<UsernameEntity, Long> {
}
