package vn.smartshop.server.infra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.smartshop.server.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
