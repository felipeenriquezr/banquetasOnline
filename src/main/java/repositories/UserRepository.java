package repositories;

import com.BaquetasOnline.Model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
    // You can add custom query methods here if needed
}

