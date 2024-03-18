package vn.smartshop.server.infra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.smartshop.server.model.entity.MasterData;
import vn.smartshop.server.model.entity.User;

import java.util.Optional;

@Repository
public interface MasterDataRepository extends JpaRepository<MasterData, Long> {
    Optional<MasterData> findByCode(String code);
}
