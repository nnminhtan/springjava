package dingus.co.nnminhtan.repositories;

import dingus.co.nnminhtan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
